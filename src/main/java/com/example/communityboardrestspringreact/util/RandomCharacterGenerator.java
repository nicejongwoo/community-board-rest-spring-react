package com.example.communityboardrestspringreact.util;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCharacterGenerator {

    public static String randomCharacterWithPrefix(String prefix, int length) {
        return prefix + randomCharacter(length - prefix.length());
    }

    public static String randomCharacter(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

}
