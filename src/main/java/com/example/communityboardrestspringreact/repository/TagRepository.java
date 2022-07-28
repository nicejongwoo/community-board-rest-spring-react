package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Long countByCommunityId(Long id);

    void deleteAllByCommunityId(Long id);
}
