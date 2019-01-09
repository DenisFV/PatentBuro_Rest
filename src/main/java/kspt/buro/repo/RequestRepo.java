package kspt.buro.repo;

import kspt.buro.domain.Request;
import kspt.buro.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface RequestRepo extends JpaRepository<Request, Long> {

    default List<Request> findAllByAuthor(User user) {
        List<Request> requests = new ArrayList<>();
        for (Request request : findAll())
            if (request.getAuthor().getName().equals(user.getName()))
                requests.add(request);
        return requests;
    }

}
