package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserDaoImpl implements UserDao {
    private final Logger LOGGER = Logger.getLogger("LOG: UserDaoImpl");
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        LOGGER.info("getAllUsers");
        return entityManager.createNativeQuery("select * from users", User.class).getResultList();
    }

    @Override
    public User getUserById(long id) {
        LOGGER.info("getUserById");
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String username) {
        LOGGER.info("getUserByName");
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.username =: username",
                User.class).setParameter("username", username);
        return query.getSingleResult();
    }

    @Override
    public void addUser(User user) {
        LOGGER.info("addUser");
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        LOGGER.info("addUser");
        entityManager.merge(user);
    }

    @Override
    public void removeUserById(long id) {
        LOGGER.info("addUser");
        entityManager.remove(getUserById(id));
    }

    @Override
    public boolean existsByUsername(String username) {
        return getUserByName(username).getUsername().equals(username);
    }
}
