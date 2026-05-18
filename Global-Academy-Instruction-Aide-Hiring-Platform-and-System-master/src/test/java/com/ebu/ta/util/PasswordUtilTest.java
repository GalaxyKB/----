package com.ebu.ta.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordUtilTest {
    @Test
    void hashIsDeterministic() {
        assertEquals(PasswordUtil.hash("Secure1!"), PasswordUtil.hash("Secure1!"));
    }

    @Test
    void verifyMatchesCorrectPasswordOnly() {
        String hash = PasswordUtil.hash("Secure1!");

        assertTrue(PasswordUtil.verify("Secure1!", hash));
        assertFalse(PasswordUtil.verify("Wrong1!", hash));
    }

    @Test
    void verifyRejectsNullAndMalformedHash() {
        String hash = PasswordUtil.hash("Secure1!");

        assertFalse(PasswordUtil.verify(null, hash));
        assertFalse(PasswordUtil.verify("Secure1!", null));
        assertFalse(PasswordUtil.verify("Secure1!", "abc"));
    }

    @Test
    void hashRejectsNullPassword() {
        assertThrows(NullPointerException.class, () -> PasswordUtil.hash(null));
    }
}
