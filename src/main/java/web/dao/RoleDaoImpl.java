package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Repository
public class RoleDaoImpl implements RoleDao{
    private final Logger LOGGER = Logger.getLogger("LOG: RoleDaoImpl");
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleById(long id) {
        LOGGER.info("getRoleById");
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        return entityManager.find(Role.class, name);
    }

    @Override
    public boolean existsByName(String name) {
        Role role = entityManager.find(Role.class, name);
        return role.getName().equals(name);
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }
}
