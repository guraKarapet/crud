package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDaoImpl;
import web.model.User;

import java.util.List;

@Service
@ComponentScan("web")
public class UserServiceImpl implements UserService {

        @Autowired
        private UserDaoImpl userDao;

        @Override
        public List<User> listAllPeople() {
            return userDao.listAllPeople();
        }

        @Override
        public User getUserById(long id) {
            return userDao.getUserById(id);
        }

        @Override
        public void save(User user) {
            userDao.save(user);
        }

        @Override
        public void update(User user, Long id) {
            userDao.update(id, user);
        }

        @Override
        public void delete(long id) {
            userDao.delete(id);
        }
    }

