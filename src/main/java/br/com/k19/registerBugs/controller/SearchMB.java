package br.com.k19.registerBugs.controller;

import br.com.k19.registerBugs.model.entity.Bug;
import br.com.k19.registerBugs.model.repository.BugRepository;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class SearchMB implements Serializable {

    private Long projectId;
    private String filter;
    private List<Bug> bugs;

    @Inject
    private BugRepository bugRepository;

    public void filterList() {
        System.out.println("getFilterList");

        this.bugs = this.bugRepository.getFilterList(this.projectId, this.filter);
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Bug> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}
