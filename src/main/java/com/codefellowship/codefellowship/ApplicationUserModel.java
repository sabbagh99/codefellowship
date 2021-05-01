package com.codefellowship.codefellowship;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.List;
import java.util.Set;


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
    @JsonManagedReference
    @OneToMany(mappedBy = "applicationUserModel", cascade = CascadeType.ALL)
    private List<PostModel> postModel;

    @GeneratedValue(strategy= GenerationType.TABLE)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Following",
            joinColumns = @JoinColumn(name = "user_Id"),
            inverseJoinColumns = @JoinColumn(name = "follow_Id"))

    private List<ApplicationUserModel> following;

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

    public List<ApplicationUserModel> getFollowing() {
        return following;
    }

    public void setFollowing(List<ApplicationUserModel> following) {
        this.following = following;
    }

//    public Set<ApplicationUserModel> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(java.util.Set<ApplicationUserModel> following) {
//        this.following = following;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void ListPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void ListUsername(String username) {
        this.username = username;
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

    public void ListId(Integer id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void ListBio(String bio) {
        this.bio = bio;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void ListDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLastName() {
        return lastName;
    }

    public void ListLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void ListFirstName(String firstName) {
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
