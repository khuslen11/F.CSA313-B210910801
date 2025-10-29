package edu.sc.csce747.MeetingPlanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoomTest {

    @Test
    void testDefaultConstructor_initializesEmptyIDAndCalendar() {
        Room r = new Room();
        assertEquals("", r.getID());
        assertDoesNotThrow(() -> r.printAgenda(1));
    }

    @Test
    void testConstructor_setsID() {
        Room r = new Room("2A01");
        assertEquals("2A01", r.getID());
    }

    @Test
    void testAddMeeting_addsSuccessfully() throws TimeConflictException {
        Room r = new Room("2A02");
        Meeting meeting = new Meeting(4, 12, "Planning");
        r.addMeeting(meeting);
        assertTrue(r.isBusy(4, 12, 0, 23));
    }

    @Test
    void testAddMeeting_conflictThrowsException() throws TimeConflictException {
        Room r = new Room("2A03");
        Meeting m1 = new Meeting(5, 10, "Morning");
        r.addMeeting(m1);
        Meeting m2 = new Meeting(5, 10, "Overlap");
        TimeConflictException e = assertThrows(TimeConflictException.class, () -> r.addMeeting(m2));
        assertTrue(e.getMessage().contains("Conflict for room 2A03"));
    }

    @Test
    void testPrintAgenda_containsMeetingDescription() throws TimeConflictException {
        Room r = new Room("2A04");
        Meeting m = new Meeting(6, 22, "Status Update");
        r.addMeeting(m);
        String agenda = r.printAgenda(6);
        assertTrue(agenda.contains("Status Update"));
    }

    @Test
    void testRemoveMeeting_removesSuccessfully() throws TimeConflictException {
        Room r = new Room("2A05");
        Meeting m = new Meeting(7, 5, "Discussion");
        r.addMeeting(m);
        assertTrue(r.isBusy(7, 5, 0, 23));
        r.removeMeeting(7, 5, 0);
        assertFalse(r.isBusy(7, 5, 0, 23));
    }

    @Test
    void testGetMeeting_returnsCorrectMeeting() throws TimeConflictException {
        Room r = new Room("3A01");
        Meeting m = new Meeting(8, 10, "Review Session");
        r.addMeeting(m);
        Meeting retrieved = r.getMeeting(8, 10, 0);
        assertEquals("Review Session", retrieved.getDescription());
    }
}