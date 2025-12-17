package edu.sc.csce747.MeetingPlanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TimeConflictExceptionTest {

    @Test
    void testDefaultConstructor() {
        new TimeConflictException();
    }

    @Test
    void testMessageConstructor() {
        TimeConflictException e = new TimeConflictException("Conflict");
        assertEquals("Conflict", e.getMessage());
    }

    @Test
    void testCauseConstructor() {
        Exception cause = new Exception("Root");
        TimeConflictException e = new TimeConflictException("Conflict", cause);
        assertEquals(cause, e.getCause());
    }

    @Test
    void testMessageOnly() {
        new TimeConflictException("Only message");
    }

    @Test
    void testMessageAndCause() {
        Exception cause = new Exception();
        new TimeConflictException("msg", cause);
    }
}