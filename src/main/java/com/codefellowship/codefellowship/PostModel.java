package com.codefellowship.codefellowship;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createdAt;
    @JsonBackReference
    @ManyToOne
private ApplicationUserModel applicationUserModel;

    public PostModel() {
    }

    public PostModel(String body, Date createdAt, ApplicationUserModel applicationUserModel) {
        this.body = body;
        this.createdAt = createdAt;
        this.applicationUserModel = applicationUserModel;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public ApplicationUserModel getApplicationUserModel() {
        return applicationUserModel;
    }

    public void setApplicationUserModel(ApplicationUserModel applicationUserModel) {
        this.applicationUserModel = applicationUserModel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", applicationUserModel=" + applicationUserModel +
                '}';
    }
}
