package com.ibee.authorization.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "tb_user")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Users {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String user;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
    name = "tb_user_group",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;

    public boolean verifyPasswordCurrent(String passwordCurrent) {
        return password.equals(passwordCurrent);
    }

    public void addGroup(Group group) {
        getGroups().add(group);
    }

    public void removeGroup(Group group) {
        getGroups().remove(group);
    }

}