package com.ibee.authorization.model.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "tb_permissions")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Permissions {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

}
