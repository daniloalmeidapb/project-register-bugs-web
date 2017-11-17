package br.com.ellos.registerbugs.model.repository;

import br.com.ellos.registerbugs.model.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class UserRepository implements Serializable, Repository<User> {

    @Inject
    private EntityManager manager;

    @Override
    public void add(User user) {
        this.manager.persist(user);
    }

    @Override
    public void update(User user) {
        this.manager.merge(user);
    }

    @Override
    public void delete(Long id) {
        User user = this.find(id);
        this.manager.remove(user);
    }

    @Override
    public User find(Long id) {
        return this.manager.find(User.class, id);
    }

    @Override
    public List<User> getList() {
        Query query = this.manager.createQuery("select x from User x");
        return query.getResultList();
    }

    public boolean validateLogin(User user) {
        Query query = this.manager.createQuery(
                "select u from User u where u.username = :username and u.password = :password");
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword());

        return !query.getResultList().isEmpty();
    }
}
