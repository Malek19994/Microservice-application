package com.electronicarmory.armory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.electronicarmory.armory.domain.enumeration.Language;

/**
 * A Lesson.
 */
@Entity
@Table(name = "lesson")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "lesson_title", nullable = false)
    private String lessonTitle;

    @Column(name = "lesson_description")
    private String lessonDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private Language language;

    @OneToMany(mappedBy = "lesson")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Resource> resources = new HashSet<>();

    @ManyToMany(mappedBy = "lessons")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Course> courses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public Lesson lessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
        return this;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getLessonDescription() {
        return lessonDescription;
    }

    public Lesson lessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
        return this;
    }

    public void setLessonDescription(String lessonDescription) {
        this.lessonDescription = lessonDescription;
    }

    public Language getLanguage() {
        return language;
    }

    public Lesson language(Language language) {
        this.language = language;
        return this;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public Lesson resources(Set<Resource> resources) {
        this.resources = resources;
        return this;
    }

    public Lesson addResources(Resource resource) {
        this.resources.add(resource);
        resource.setLesson(this);
        return this;
    }

    public Lesson removeResources(Resource resource) {
        this.resources.remove(resource);
        resource.setLesson(null);
        return this;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Lesson courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Lesson addCourses(Course course) {
        this.courses.add(course);
        course.getLessons().add(this);
        return this;
    }

    public Lesson removeCourses(Course course) {
        this.courses.remove(course);
        course.getLessons().remove(this);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lesson)) {
            return false;
        }
        return id != null && id.equals(((Lesson) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lesson{" +
            "id=" + getId() +
            ", lessonTitle='" + getLessonTitle() + "'" +
            ", lessonDescription='" + getLessonDescription() + "'" +
            ", language='" + getLanguage() + "'" +
            "}";
    }
}
