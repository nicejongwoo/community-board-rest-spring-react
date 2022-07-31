package com.example.communityboardrestspringreact.config;

import com.example.communityboardrestspringreact.security.service.CustomUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new SpringSecurityAuditAwareImpl();
    }

    class SpringSecurityAuditAwareImpl implements AuditorAware<String> {

        @Override
        public Optional<String> getCurrentAuditor() {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null | !authentication.isAuthenticated() | authentication instanceof AnonymousAuthenticationToken) {
                return Optional.empty();
            }
            CustomUser principal = (CustomUser) authentication.getPrincipal();
            return Optional.ofNullable(principal.getAccount().getEmail());
        }
    }

}
