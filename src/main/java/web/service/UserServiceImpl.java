package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.model.Role;
import web.model.User;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final Logger LOGGER = Logger.getLogger("LOG: UserServiceImpl");

    private final RoleDao roleDao;
    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(RoleDao roleDao, UserDao userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    @Override
    public User findByUsername(String username) {
        LOGGER.info("");
        return userDao.getUserByName(username);
    }

    @Transactional
    public void add(User user) {
        LOGGER.info("");
        user.setRoles(Collections.singleton(new Role(2, "ROLE_USER")));
        userDao.addUser(user);
    }

    @Override
    public List<User> listUsers() {
        LOGGER.info("");
        return userDao.getAllUsers();
    }

    @Transactional
    public void update(User user) {
        LOGGER.info("");
        if (user.getPassword()=="") {
            String password = userDao.getUserById(user.getId()).getPassword();
            user.setPassword(password);
        } else {
            String password = user.getPassword();
//            user.setPassword(passwordEncoder.encode(password));
        }
        if (user.getRoles()==null){
            user.setRoles(userDao.getUserById(user.getId()).getRoles());
        }
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        LOGGER.info("");
        userDao.removeUserById(id);
    }

    @Override
    @Transactional
    public User getById(long id) {
        LOGGER.info("");
        return userDao.getUserById(id);
    }

    @Override
    public boolean existsByUsername(String name) {
        return userDao.getUserByName(name).getUsername().equals(name);
    }
}
