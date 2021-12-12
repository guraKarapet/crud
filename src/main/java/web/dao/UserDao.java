package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> listAllPeople();

    User getUserById(long id);

    void save(User user);

    void update(long id, User user);

    void delete(long id);
}

