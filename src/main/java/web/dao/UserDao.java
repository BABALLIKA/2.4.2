package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getUserById(long id);
    User getUserByName(String username);
    void addUser(User user);
    void updateUser(User user);
    void removeUserById(long id);
    boolean existsByUsername(String username);
}
