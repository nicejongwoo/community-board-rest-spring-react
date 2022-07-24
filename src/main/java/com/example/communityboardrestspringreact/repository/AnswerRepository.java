package com.example.communityboardrestspringreact.repository;

import com.example.communityboardrestspringreact.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
