package edu.lab09;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExtendedGCDTest {

    @Test
    @DisplayName("Test normal GCD computation (covers a,b,steps,temp,result DU pairs)")
    void testNormalCase() {
        int result = ExtendedGCD.gcd(48, 18);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("Test when one argument is zero (covers p-use of b in while)")
    void testZeroInput() {
        int result = ExtendedGCD.gcd(0, 5);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Test when both arguments are equal (minimal loop coverage)")
    void testEqualValues() {
        int result = ExtendedGCD.gcd(12, 12);
        assertEquals(12, result);
    }

    @Test
    @DisplayName("Test with swapped large/small values (loop executes multiple times)")
    void testReversedOrder() {
        int result = ExtendedGCD.gcd(18, 48);
        assertEquals(6, result);
    }
}