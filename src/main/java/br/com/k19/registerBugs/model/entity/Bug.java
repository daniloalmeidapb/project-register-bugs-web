package br.com.k19.registerBugs.model.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Bug implements Serializable {

    private Long id;
    private String description;
    private String severity;
    private Project project;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
