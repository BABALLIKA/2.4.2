package web.service;
import web.model.User;

import java.util.List;

public interface UserService{
    User findByUsername(String username);

    void add(User user);
    List<User> listUsers();
    void update(User user);
    void delete(long id);
    User getById(long id);

    boolean existsByUsername(String name);
}
