package br.com.ellos.registerbugs.controller;

import br.com.ellos.registerbugs.model.entity.Project;
import br.com.ellos.registerbugs.model.repository.ProjectRepository;
import br.com.ellos.registerbugs.util.jpa.Transactional;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProjectMB implements Serializable {

    private Project project = new Project();
    private List<Project> projects;

    @Inject
    private ProjectRepository projectRepository;

    @Transactional
    public void add() {
        if (this.project.getId() == null) {
            this.projectRepository.add(this.project);
        } else {
            this.projectRepository.update(this.project);
        }

        this.project = new Project();
        this.projects = null;
    }

    public void prepareUpdate(Long id) {
        this.project = this.projectRepository.find(id);
    }

    @Transactional
    public void delete(Long id) {
        this.projectRepository.delete(id);
        this.projects = null;
    }

    public List<Project> getProjects() {
        if (this.projects == null) {
            this.projects = this.projectRepository.getList();
        }
        return this.projects;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
