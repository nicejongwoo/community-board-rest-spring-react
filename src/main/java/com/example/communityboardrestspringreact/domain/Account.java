package com.example.communityboardrestspringreact.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(
        name = "account",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"email"})
        }
)
public class Account {

    @Comment("일련번호")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("계정토큰(랜덤 20자)")
    @Column(name = "account_token", length = 20, unique = true)
    private String accountToken;

    @Comment("이름")
    private String name;

    @Comment("이메일")
    @Column(unique = true)
    private String email;

    @Comment("비밀번호")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Comment("휴대폰번호")
    private String phone;

    @Comment("가입일")
    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Comment("프로필이미지")
    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage;

    @Setter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;


    public void encryptPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

}
