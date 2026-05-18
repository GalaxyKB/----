package com.ebu.ta.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;

public final class PasswordUtil {
    private PasswordUtil() {
    }

    public static String hash(String password) {
        Objects.requireNonNull(password, "password must not be null");
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Failed to hash password", e);
        }
    }

    public static boolean verify(String plainPassword, String hash) {
        if (plainPassword == null || hash == null || hash.length() != 64) {
            return false;
        }
        String recomputed = hash(plainPassword);
        return MessageDigest.isEqual(
            recomputed.getBytes(StandardCharsets.UTF_8),
            hash.getBytes(StandardCharsets.UTF_8)
        );
    }
}
