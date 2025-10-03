package xyz.ccdescipline.Util;

import java.security.SecureRandom;

public class TokenGenerator {
    // 单例 SecureRandom
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generateToken(int byteLength) {
        byte[] bytes = new byte[byteLength];
        RANDOM.nextBytes(bytes);
        return java.util.Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }
}
