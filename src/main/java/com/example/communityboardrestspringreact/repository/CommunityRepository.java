package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Community;
import com.example.communityboardrestspringreact.repository.custom.CommunityRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long>, CommunityRepositoryCustom {
}
