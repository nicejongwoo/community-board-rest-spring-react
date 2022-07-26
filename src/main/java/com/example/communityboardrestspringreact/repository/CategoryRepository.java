package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Category;
import com.example.communityboardrestspringreact.repository.custom.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {
}
