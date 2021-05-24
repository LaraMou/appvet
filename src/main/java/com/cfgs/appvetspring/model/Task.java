package com.cfgs.appvetspring.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "finish")
    private Boolean finish;

    private Estado estado;

    private String motivo;
    @CreatedDate
    @Column(name = "created_date", updatable = false)

    private Instant createdDate = Instant.now();




    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;




    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDate deliver_date;


    @OneToMany(mappedBy="task",orphanRemoval = true,cascade = CascadeType.ALL)
    @JsonIgnoreProperties("task")
    private List<Mensaje> mensajes =new ArrayList<>();
    @ManyToMany(mappedBy = "tasks" ,fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("tasks")
    private List<User> users = new ArrayList<>();



    /**
     * Clase Tarea
     *
     */
    public Task() {
    }

    public Task(String title, String description, Boolean finish, Estado estado, String motivo, Instant createdDate, String lastModifiedBy, LocalDate deliver_date) {
        this.title = title;
        this.description = description;
        this.finish = finish;
        this.estado = estado;
        this.motivo = motivo;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifiedBy;
        this.deliver_date = deliver_date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDate getDeliver_date() {
        return deliver_date;
    }

    public void setDeliver_date(LocalDate deliver_date) {
        this.deliver_date = deliver_date;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", finish=" + finish +
                ", estado='" + estado + '\'' +
                ", motivo='" + motivo + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", deliver_date=" + deliver_date +
                '}';
    }
}
