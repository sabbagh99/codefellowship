package com.codefellowship.codefellowship;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class ApplicationUserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String username;
    private String password;
    private String bio;
    private String lastName;
    private String firstName;
    private String dateOfBirth;
    @OneToMany (mappedBy = "applicationUserModel", cascade = CascadeType.ALL)
    private List<PostModel> postModel;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="Following",
            joinColumns=@JoinColumn(name="user_Id"),
            inverseJoinColumns=@JoinColumn(name="follow_Id")
    )
    private List<ApplicationUserModel> user_Id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="Following",
            joinColumns=@JoinColumn(name="follow_Id"),
            inverseJoinColumns=@JoinColumn(name="user_Id")
    )
    private List<ApplicationUserModel> follow_Id;



    public ApplicationUserModel() {
    }
    public ApplicationUserModel(String username, String password, String bio, String dateOfBirth, String lastName, String firstName) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
        this.lastName = lastName;
        this.firstName = firstName;
    }
    public ApplicationUserModel(String username, String password, String bio, String dateOfBirth, String lastName, String firstName,List<PostModel> postModel) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
        this.lastName = lastName;
        this.firstName = firstName;
        this.postModel= postModel;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ApplicationUserModel> getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(List<ApplicationUserModel> user_Id) {
        this.user_Id = user_Id;
    }

    public List<ApplicationUserModel> getFollow_Id() {
        return follow_Id;
    }

    public void setFollow_Id(List<ApplicationUserModel> follow_Id) {
        this.follow_Id = follow_Id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public Integer getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<PostModel> getPostModel() {
        return postModel;
    }

    @Override
    public String toString() {
        return "ApplicationUserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
