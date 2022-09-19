package com.example.communityboardrestspringreact.domain;

import com.example.communityboardrestspringreact.util.BooleanToStringConverter;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@SuperBuilder
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Test extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 200)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Type(type = "yes_no") //Y or N 으로 값 저장
    private Boolean notice;

    @Column(name = "use_enabled")
    @Convert(converter = BooleanToStringConverter.class) // true or false 로 값 저장
    private Boolean deleted;

    @PrePersist
    void prePersist() {
        this.deleted = false;
    }

    public void edit(TestRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.notice = request.getNotice();
    }
}


