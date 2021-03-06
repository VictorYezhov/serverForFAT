package com.fatserver.entity;

import com.fasterxml.jackson.annotation.*;
import com.fatserver.dto.JobDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class that represents job or education of user
 */
@Entity
public class Job implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToOne
    @JoinColumn(name = "position_id")
    @JsonManagedReference
    private Position position;

    @Enumerated
    private Type type;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "job_user",
            joinColumns = @JoinColumn(name = "job_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"))
    @JsonBackReference
    private Set<User> userListJob;


    public Job(JobDTO j){
        this.name = j.getName();
        this.type = j.getType();
        userListJob = new HashSet<>();
    }

    public Job() {
        userListJob = new HashSet<>();
    }

    public Job(String name) {
        this.name = name;

    }

    public Job(String name, Set<User> userListJob) {
        this.name = name;
        this.userListJob = userListJob;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Job(String name, Type type, Set<User> userListJob) {
        this.name = name;
        this.type = type;
        this.userListJob = userListJob;
    }

    public Job(String name, Position description, Type type, Set<User> userListJob) {
        this.name = name;
        this.type = type;
        this.userListJob = userListJob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Set<User> getUserListJob() {
        return userListJob;
    }

    public void setUserListJob(Set<User> userListJob) {
        this.userListJob = userListJob;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
