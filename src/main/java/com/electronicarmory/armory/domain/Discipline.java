package com.electronicarmory.armory.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Discipline.
 */
@Entity
@Table(name = "discipline")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Discipline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "discipline_name", nullable = false)
    private String disciplineName;

    @Column(name = "discipline_description")
    private String disciplineDescription;

    @Column(name = "discipline_price")
    private Long disciplinePrice;

    @OneToMany(mappedBy = "discipline")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(name = "discipline_programs",
               joinColumns = @JoinColumn(name = "discipline_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "programs_id", referencedColumnName = "id"))
    private Set<Program> programs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisciplineName() {
        return disciplineName;
    }

    public Discipline disciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
        return this;
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName = disciplineName;
    }

    public String getDisciplineDescription() {
        return disciplineDescription;
    }

    public Discipline disciplineDescription(String disciplineDescription) {
        this.disciplineDescription = disciplineDescription;
        return this;
    }

    public void setDisciplineDescription(String disciplineDescription) {
        this.disciplineDescription = disciplineDescription;
    }

    public Long getDisciplinePrice() {
        return disciplinePrice;
    }

    public Discipline disciplinePrice(Long disciplinePrice) {
        this.disciplinePrice = disciplinePrice;
        return this;
    }

    public void setDisciplinePrice(Long disciplinePrice) {
        this.disciplinePrice = disciplinePrice;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public Discipline resources(Set<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public Discipline addResources(Resource resource) {
        this.resources.add(resource);
        resource.setDiscipline(this);
        return this;
    }

    public Discipline removeResources(Resource resource) {
        this.resources.remove(resource);
        resource.setDiscipline(null);
        return this;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Program> getPrograms() {
        return programs;
    }

    public Discipline programs(Set<Program> programs) {
        this.programs = programs;
        return this;
    }

    public Discipline addPrograms(Program program) {
        this.programs.add(program);
        program.getDisciplines().add(this);
        return this;
    }

    public Discipline removePrograms(Program program) {
        this.programs.remove(program);
        program.getDisciplines().remove(this);
        return this;
    }

    public void setPrograms(Set<Program> programs) {
        this.programs = programs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Discipline)) {
            return false;
        }
        return id != null && id.equals(((Discipline) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Discipline{" +
            "id=" + getId() +
            ", disciplineName='" + getDisciplineName() + "'" +
            ", disciplineDescription='" + getDisciplineDescription() + "'" +
            ", disciplinePrice=" + getDisciplinePrice() +
            "}";
    }
}
