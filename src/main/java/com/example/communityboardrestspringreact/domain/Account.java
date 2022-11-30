package com.example.communityboardrestspringreact.domain;

import com.example.communityboardrestspringreact.util.RandomCharacterGenerator;
import com.example.communityboardrestspringreact.web.dto.request.account.AccountEditRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
public class Account implements Serializable {

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
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "account_roles",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();


    public void updateEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    @PrePersist
    void prePersist() {
        this.joinedAt = LocalDateTime.now();
        this.accountToken = RandomCharacterGenerator.randomCharacterWithPrefix("user", 20);
    }

    public void edit(AccountEditRequest request) {
        this.name = request.getName();
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.profileImage = request.getProfileImage();
    }
}
