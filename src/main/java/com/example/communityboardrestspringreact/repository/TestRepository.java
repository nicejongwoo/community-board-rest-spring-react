package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {
}
