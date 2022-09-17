package com.example.communityboardrestspringreact.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles({"test"})
@DataJpaTest
class TestRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    void saveTest() {
        //given
        String title = "test title";
        String content = "test content";
        Boolean notice = false;
        Boolean delete = false;
        String createdBy = "user";

        com.example.communityboardrestspringreact.domain.Test test =
                com.example.communityboardrestspringreact.domain.Test.builder()
                        .title(title)
                        .content(content)
                        .notice(notice)
                        .deleted(delete)
                        .createdBy(createdBy)
                        .build();

        //when
        com.example.communityboardrestspringreact.domain.Test savedTest =
                testRepository.save(test);

        //then
        assertThat(savedTest).isNotNull();
        assertThat(savedTest.getId()).isGreaterThan(0);
        assertThat(savedTest.getTitle()).isEqualTo(title);
    }

}