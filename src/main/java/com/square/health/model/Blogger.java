package com.square.health.model;

import com.square.health.util.enumutil.StatusEnum;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Blogger extends BaseEntity {

    private String userName;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Role> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public void grantRole(Role role) {
        this.roles = new LinkedList<>();
        this.roles.add(role);
    }
}
