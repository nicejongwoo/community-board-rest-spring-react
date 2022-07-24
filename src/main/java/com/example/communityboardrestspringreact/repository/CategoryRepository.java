package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
