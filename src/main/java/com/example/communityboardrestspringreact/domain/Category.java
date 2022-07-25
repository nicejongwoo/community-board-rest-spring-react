package com.example.communityboardrestspringreact.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Community> communities = new ArrayList<>();

    //toEntity
    @Builder
    public Category(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public void update(String name, String type) {
        this.name = name;
        this.type = type;
    }
}
