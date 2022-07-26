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
public class Community extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community", cascade = CascadeType.REMOVE)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "community", cascade = CascadeType.REMOVE)
    private List<Answer> answers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void updateCategory(Category category) {
        this.category = category;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
