package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager manager;



    public User getUserById(long id) {
        return manager.find(User.class, id);
    }


    public List<User> listAllPeople() {
        return manager.createQuery("from User")
                .getResultList();
    }


    public void save(User user) {
        manager.persist(user);
    }


    public void delete(long id) {
        User user = manager.find(User.class, id);
        manager.remove(user);
    }

    public void update(long id, User user) {
        manager.merge(user);
    }
}