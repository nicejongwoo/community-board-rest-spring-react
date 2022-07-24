package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
