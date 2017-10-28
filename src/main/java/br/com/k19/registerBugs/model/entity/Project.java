package br.com.k19.registerBugs.model.entity;

import javax.persistence.*;
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

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
    public List<Bug> getBugs() {
        return bugs;
    }
    public void setBugs(List<Bug> bugs) {
        this.bugs = bugs;
    }
}
