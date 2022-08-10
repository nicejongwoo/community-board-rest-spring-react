package com.example.communityboardrestspringreact.domain;

import com.example.communityboardrestspringreact.repository.TestRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestTest {

    @Autowired
    private TestRepository testRepository;

    @Test
    void test() {
        com.example.communityboardrestspringreact.domain.Test build = com.example.communityboardrestspringreact.domain.Test.builder()
                .useEnabled(false)
                .useYn(true)
                .build();

        com.example.communityboardrestspringreact.domain.Test save = testRepository.save(build);


        Assertions.assertThat(save.getUseYn()).isEqualTo("Y");
    }

}