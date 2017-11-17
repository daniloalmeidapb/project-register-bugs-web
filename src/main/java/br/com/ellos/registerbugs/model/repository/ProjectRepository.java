package br.com.ellos.registerbugs.model.repository;

import br.com.ellos.registerbugs.model.entity.Project;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
