package com.cfgs.appvetspring.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import javax.persistence.*;

@Entity
@Table(name="mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    private String description;
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    @JsonIdentityReference(alwaysAsId = true)
//    private User user;
    @ManyToOne
    @JoinColumn(name="task_id")
    @JsonIdentityReference(alwaysAsId = true)
    private Task task;

    public Mensaje() {
    }



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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }


    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
