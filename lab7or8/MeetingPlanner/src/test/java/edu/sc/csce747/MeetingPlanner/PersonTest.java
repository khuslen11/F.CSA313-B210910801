package edu.sc.csce747.MeetingPlanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    void testDefaultConstructor_initializesEmptyNameAndCalendar() {
        Person p = new Person();
        assertEquals("", p.getName());
        assertDoesNotThrow(() -> p.printAgenda(1));
    }

    @Test
    void testNameConstructor_setsName() {
        Person p = new Person("Alice");
        assertEquals("Alice", p.getName());
    }

    @Test
    void testAddMeeting_addsSuccessfully() throws TimeConflictException {
        Person p = new Person("Bob");
        Meeting meeting = new Meeting(5, 10, "Project Kickoff");
        p.addMeeting(meeting);
        assertTrue(p.isBusy(5, 10, 0, 23));
    }

    @Test
    void testAddMeeting_conflictThrowsException() throws TimeConflictException {
        Person p = new Person("Bob");
        Meeting m1 = new Meeting(6, 15, "Morning");
        p.addMeeting(m1);
        Meeting m2 = new Meeting(6, 15, "Overlap");
        TimeConflictException e = assertThrows(TimeConflictException.class, () -> p.addMeeting(m2));
        assertTrue(e.getMessage().contains("Conflict for attendee Bob"));
    }

    @Test
    void testPrintAgenda_containsMeetingDescription() throws TimeConflictException {
        Person p = new Person("Carol");
        Meeting m = new Meeting(7, 20, "Team Sync");
        p.addMeeting(m);
        String agenda = p.printAgenda(7);
        assertTrue(agenda.contains("Team Sync"));
    }

    @Test
    void testRemoveMeeting_removesFromCalendar() throws TimeConflictException {
        Person p = new Person("Dan");
        Meeting m = new Meeting(8, 5, "Planning");
        p.addMeeting(m);
        assertTrue(p.isBusy(8, 5, 0, 23));
        p.removeMeeting(8, 5, 0);
        assertFalse(p.isBusy(8, 5, 0, 23));
    }

    @Test
    void testGetMeeting_returnsCorrectMeeting() throws TimeConflictException {
        Person p = new Person("Eva");
        Meeting m = new Meeting(9, 12, "Review");
        p.addMeeting(m);
        Meeting retrieved = p.getMeeting(9, 12, 0);
        assertEquals("Review", retrieved.getDescription());
    }
}