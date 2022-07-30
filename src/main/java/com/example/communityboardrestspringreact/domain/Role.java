package com.example.communityboardrestspringreact.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String name;

}
