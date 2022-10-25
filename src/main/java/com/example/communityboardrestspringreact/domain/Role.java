package com.example.communityboardrestspringreact.domain;


import lombok.Getter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "roles")
public class Role {

    @Comment("일련번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("권한명")
    @Column(length = 60)
    private String name;

    /**
     * RandomCharacterGenerator
     * admin: regaXL
     * manager: rGrAql
     * user: rwCtSm
     */
    @Comment("권한코드값")
    @Column(length = 10)
    private String code;

}
