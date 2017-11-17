package br.com.ellos.registerbugs.controller;

import br.com.ellos.registerbugs.model.entity.Bug;
import br.com.ellos.registerbugs.model.entity.Project;
import br.com.ellos.registerbugs.model.repository.BugRepository;
import br.com.ellos.registerbugs.model.repository.ProjectRepository;
import br.com.ellos.registerbugs.util.jpa.Transactional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
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
        if (this.projectId != null) {
            Project project = this.projectRepository.find(this.projectId);
            this.bug.setProject(project);
        }

        if (this.bug.getId() == null) {
            this.bugRepository.add(this.bug);
        } else {
            this.bugRepository.update(this.bug);
        }

        this.bug = new Bug();
        this.projectId = null;
        this.bugs = null;
    }

    public void prepareUpdate(Long id) {
        this.bug = this.bugRepository.find(id);
        this.projectId = this.bug.getId();
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
