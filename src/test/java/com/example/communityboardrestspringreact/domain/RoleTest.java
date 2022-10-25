package com.example.communityboardrestspringreact.domain;

import org.junit.jupiter.api.Test;

import static com.example.communityboardrestspringreact.util.RandomCharacterGenerator.randomCharacterWithPrefix;
import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {

    @Test
    void createRandomCode() {
        String randomCharacterWithPrefix = randomCharacterWithPrefix("r", 6);
        assertThat(randomCharacterWithPrefix.length()).isEqualTo(6);
        System.out.println("randomCharacterWithPrefix = " + randomCharacterWithPrefix);
    }

}