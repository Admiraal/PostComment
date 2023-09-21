package com.hsleiden.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @Column(length = 2084)
    private String body;
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JsonManagedReference
    private Set<Comment> comments;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setTasks(Set<Comment> comments) {
        this.comments = comments;
    }
}
