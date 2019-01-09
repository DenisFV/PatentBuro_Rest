package letscode.sarafan.controller;

import letscode.sarafan.domain.Request;
import letscode.sarafan.domain.User;
import letscode.sarafan.repo.RequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private final RequestRepo requestRepo;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public MainController(RequestRepo requestRepo) {
        this.requestRepo = requestRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {

        HashMap<Object, Object> data = new HashMap<>();

        if (user != null) {
            data.put("profile", user);
            data.put("requests", requestRepo.findAll());
//            data.put("requests", user.getName().equals("admin")
//                    ? requestRepo.findAll()
//                    : requestRepo.findAllByAuthor(user));
        }

        model.addAttribute("frontendData", data);//user+requests
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }
}
