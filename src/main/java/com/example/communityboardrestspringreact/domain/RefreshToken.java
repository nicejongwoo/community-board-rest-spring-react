package com.example.communityboardrestspringreact.domain;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Comment;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "refresh_token")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("사용자아이디")
    @OneToOne
    @JoinColumn(name = "account_token", referencedColumnName = "account_token")
    private Account account;

    @Column(nullable = false, unique = true)
    private String token;

    @Nullable
    private LocalDateTime expiryDate;

    @Builder
    public RefreshToken(Account account, String token, LocalDateTime expiryDate) {
        this.account = account;
        this.token = token;
        this.expiryDate = expiryDate;
    }

    public RefreshToken() {
    }

}
