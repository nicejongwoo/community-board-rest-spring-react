package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Test;
import com.example.communityboardrestspringreact.repository.custom.TestRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long>, TestRepositoryCustom {
}
