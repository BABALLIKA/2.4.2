package web.dao;

import web.model.Role;

public interface RoleDao {
    Role getRoleById(long id);
    Role getRoleByName(String name);
    boolean existsByName(String name);
    void save(Role role);
}
