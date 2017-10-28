package br.com.k19.registerBugs.model.repository;

import br.com.k19.registerBugs.model.entity.Project;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

public class ProjectRepository implements Serializable, Repository<Project> {

    @Inject
    private EntityManager manager;

    @Override
    public void add(Project project) {
        this.manager.persist(project);
    }

    @Override
    public void update(Project project) {
        this.manager.merge(project);
    }

    @Override
    public void delete(Long id) {
        Project project = this.find(id);
        this.manager.remove(project);
    }

    @Override
    public Project find(Long id) {
        return this.manager.find(Project.class, id);
    }

    @Override
    public List<Project> getList() {
        Query query = this.manager.createQuery("select x from Project x");
        return query.getResultList();
    }
}
