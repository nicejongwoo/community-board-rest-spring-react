package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Role;
import com.example.communityboardrestspringreact.web.api.AccountController;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role_user);

    Optional<Role> findByRole(String role);
}
