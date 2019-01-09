package kspt.buro.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kspt.buro.domain.Request;
import kspt.buro.domain.User;
import kspt.buro.domain.Views;
import kspt.buro.dto.EventType;
import kspt.buro.dto.ObjectType;
import kspt.buro.repo.RequestRepo;
import kspt.buro.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("request")
public class RequestController {

    private final RequestRepo requestRepo;
    private final BiConsumer<EventType, Request> wsSender;

    @Autowired
    public RequestController(RequestRepo requestRepo, WsSender wsSender) {
        this.requestRepo = requestRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }


    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Request> list(
//            @AuthenticationPrincipal User user
    ) {
//        return user.getName().equals("admin")
//                ? requestRepo.findAll()
//                : requestRepo.findAllByAuthor(user);
                return requestRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullRequest.class)
    public Request getOne(@PathVariable("id") Request request) {
        return request;
    }


    @PostMapping
    public Request create(
            @RequestBody Request request
//            ,
//            @AuthenticationPrincipal User user
    ) {
        request.setCreationDate(LocalDateTime.now().toString());
//        request.setAuthor(user);

        Request updatedRequest = requestRepo.save(request);
        wsSender.accept(EventType.CREATE, updatedRequest);

        return updatedRequest;
    }
//
    @PutMapping("{id}")
    public Request update(@PathVariable("id") Request requestFromDb, @RequestBody Request request) {
        BeanUtils.copyProperties(request, requestFromDb, "id");
        Request updatedRequest = requestRepo.save(requestFromDb);

        wsSender.accept(EventType.UPDATE, updatedRequest);

        return updatedRequest;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Request request) {
        requestRepo.delete(request);
        wsSender.accept(EventType.REMOVE, request);
    }
}
