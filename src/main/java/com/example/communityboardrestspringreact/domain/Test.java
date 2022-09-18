package com.example.communityboardrestspringreact.domain;

import com.example.communityboardrestspringreact.util.BooleanToStringConverter;
import com.example.communityboardrestspringreact.web.dto.request.TestRequest;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Test {

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

    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_by", length = 50)
    private String updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        this.deleted = false;
        this.createdAt = LocalDateTime.now();
    }

    public void edit(TestRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.notice = request.getNotice();
        this.updatedBy = "TEST";
        this.updatedAt = LocalDateTime.now();
    }
}
