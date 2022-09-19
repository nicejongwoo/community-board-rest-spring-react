package com.example.communityboardrestspringreact.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

    public void update(String content) {
        this.content = content;
    }

    public void updateCommunity(Community community) {
        this.community = community;
    }
}
