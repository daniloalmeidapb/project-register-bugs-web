package br.com.ellos.registerbugs.model.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project implements Serializable {

    private Long id;
    private String name;
    private String description;
    private List<Bug> bugs = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true)
    @NotEmpty(message = "nome é obrigatório")
    @Pattern(regexp = "[a-zA-Z]+\\s*[a-zA-Z]*", message = "nome inválido")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @NotEmpty(message = "descrição é obrigatório")
    @Length(min = 5, max = 255)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(orphanRemoval = true, mappedBy = "project")
    public List<Bug> getBugs() {
        return bugs;
    }
    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}
