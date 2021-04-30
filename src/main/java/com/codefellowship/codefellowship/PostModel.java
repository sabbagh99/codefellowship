package com.codefellowship.codefellowship;

import javax.persistence.*;

@Entity
public class PostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String body;
    @ManyToOne
private ApplicationUserModel applicationUserModel;

    public PostModel() {
    }

    public PostModel(String body, ApplicationUserModel applicationUser) {
        this.body = body;
        this.applicationUserModel = applicationUser;
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

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", applicationUserModel=" + applicationUserModel +
                '}';
    }
}
