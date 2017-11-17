package br.com.ellos.registerbugs.model.repository;

import br.com.ellos.registerbugs.model.entity.Bug;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class BugRepository implements Serializable, Repository<Bug> {

    @Inject
    private EntityManager manager;

    @Override
    public void add(Bug bug) {
        this.manager.persist(bug);
    }

    @Override
    public void update(Bug bug) {
        this.manager.merge(bug);
    }

    @Override
    public void delete(Long id) {
        Bug bug = this.find(id);
        this.manager.remove(bug);
    }

    @Override
    public Bug find(Long id) {
        return this.manager.find(Bug.class, id);
    }

    @Override
    public List<Bug> getList() {
        TypedQuery<Bug> query = this.manager.createQuery("select x from Bug x", Bug.class);
        return query.getResultList();
    }

    public List<Bug> getFilterList(Long projectId, String filter) {
        TypedQuery<Bug> query = this.manager.createQuery("select x from Bug x where project.id = :id and problem like :filter", Bug.class);
        query.setParameter("id", projectId);
        query.setParameter("filter", "%" + filter + "%");

        return query.getResultList();
    }
}
