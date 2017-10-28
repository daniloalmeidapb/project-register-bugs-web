package br.com.k19.registerBugs.controller;

import br.com.k19.registerBugs.model.entity.Project;
import br.com.k19.registerBugs.model.repository.ProjectRepository;
import br.com.k19.registerBugs.util.Transactional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
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

    public List<Project> complete(String query) {
        List<Project> allProjects = this.projectRepository.getList();
        List<Project> filterProjects = new ArrayList<>();

        for (int i = 0; i < allProjects.size(); i++) {
            Project project = allProjects.get(i);

            if (project.getName().toLowerCase().endsWith(query)) {
                filterProjects.add(project);
            }
        }
        return filterProjects;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
