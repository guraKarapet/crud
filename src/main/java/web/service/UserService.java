package web.service;

import org.springframework.stereotype.Service;
import web.model.User;
import java.util.List;

public interface UserService {
    List<User> listAllPeople();

    User getUserById(long id);

    void save(User user);

    void update(User user, Long id);

    public void delete(long id);
}