package com.ibee.authorization.model.entities;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "tb_users")
@Getter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

}
