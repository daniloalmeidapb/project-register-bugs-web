package br.com.k19.registerBugs.model.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Bug implements Serializable {

    private Long id;
    private String problem;
    private String solution;
    private Project project;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @NotEmpty(message = "problema é obrigatório")
    public String getProblem() {
        return problem;
    }
    public void setProblem(String description) {
        this.problem = description;
    }

    @NotEmpty(message = "solução é obrigatório")
    public String getSolution() {
        return solution;
    }
    public void setSolution(String severity) {
        this.solution = severity;
    }

    @ManyToOne
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
}
