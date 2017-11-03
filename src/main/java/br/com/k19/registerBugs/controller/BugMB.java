package br.com.k19.registerBugs.controller;

import br.com.k19.registerBugs.model.entity.Bug;
import br.com.k19.registerBugs.model.entity.Project;
import br.com.k19.registerBugs.model.repository.BugRepository;
import br.com.k19.registerBugs.model.repository.ProjectRepository;
import br.com.k19.registerBugs.util.jpa.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class BugMB implements Serializable {

    private Bug bug = new Bug();
    private Long projectId;
    private List<Bug> bugs;

    @Inject
    private BugRepository bugRepository;

    @Inject
    private ProjectRepository projectRepository;

    @Transactional
    public void add() {
        Project project = this.projectRepository.find(this.projectId);
        project.getBugs().add(this.bug);

        if (this.bug.getId() == null) {
            this.projectRepository.add(project);
        } else {
            this.projectRepository.update(project);
        }

        this.bug = new Bug();
        this.bugs = null;
    }

    public void prepareUpdate(Long id) {
        this.bug = this.bugRepository.find(id);
    }

    @Transactional
    public void delete(Long id) {
        this.bugRepository.delete(id);
        this.bugs = null;
    }

    public List<Bug> getBugs() {
        if (this.bugs == null) {
            this.bugs = this.bugRepository.getList();
        }
        return this.bugs;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
