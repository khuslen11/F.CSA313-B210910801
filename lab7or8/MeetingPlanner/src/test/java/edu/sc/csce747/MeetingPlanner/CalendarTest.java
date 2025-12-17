package edu.sc.csce747.MeetingPlanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalendarTest {

    private Calendar calendar;

    @BeforeEach
    void setUp() {
        calendar = new Calendar();
    }

    @Test
    void testAddMeeting_holiday() {
        try {
            Meeting midsommar = new Meeting(6, 26, "Midsommar");
            calendar.addMeeting(midsommar);
            boolean added = calendar.isBusy(6, 26, 0, 23);
            assertTrue(added, "Midsommar should be marked as busy on the calendar");
        } catch (TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    void testAddMeeting_conflictThrowsException_forAllDay() throws TimeConflictException {
        Meeting m1 = new Meeting(5, 10, "All day A");
        calendar.addMeeting(m1);
        Meeting m2 = new Meeting(5, 10, "All day B");
        assertThrows(TimeConflictException.class, () -> calendar.addMeeting(m2));
    }

    @Test
    void testClearScheduleRemovesMeetings_allDay() throws TimeConflictException {
        Meeting m = new Meeting(3, 5, "Short meeting");
        calendar.addMeeting(m);
        calendar.clearSchedule(3, 5);
        assertFalse(calendar.isBusy(3, 5, 0, 23));
    }

    @Test
    void testRemoveMeetingActuallyRemoves_allDay() throws TimeConflictException {
        Meeting m = new Meeting(4, 20, "Team meeting");
        calendar.addMeeting(m);
        assertTrue(calendar.isBusy(4, 20, 0, 23));
        calendar.removeMeeting(4, 20, 0);
        assertFalse(calendar.isBusy(4, 20, 0, 23));
    }

    @Test
    void testPrintAgendaContainsMeetingDescription_allDay() throws TimeConflictException {
        Meeting m = new Meeting(7, 15, "Status update");
        calendar.addMeeting(m);
        String agenda = calendar.printAgenda(7);
        assertTrue(agenda.contains("Status update"));
    }

    @Test
    void testGetMeetingReturnsCorrectMeeting_allDay() throws TimeConflictException {
        Meeting m = new Meeting(8, 1, "Morning sync");
        calendar.addMeeting(m);
        Meeting result = calendar.getMeeting(8, 1, 0);
        assertEquals("Morning sync", result.getDescription());
    }

    @Test
    void testCheckTimes_invalidMonth() {
        assertThrows(TimeConflictException.class,
                () -> Calendar.checkTimes(13, 10, 0, 23));
    }

    @Test
    void testCheckTimes_invalidDay() {
        assertThrows(TimeConflictException.class,
                () -> Calendar.checkTimes(5, 0, 0, 23));
    }

    @Test
    void testCheckTimes_invalidStartEnd() {
        assertThrows(TimeConflictException.class,
                () -> Calendar.checkTimes(5, 10, 12, 8));
    }

    // New tests below to increase coverage
    @Test
    void testIsBusy_falseWhenNoMeetings() throws TimeConflictException {
        assertFalse(calendar.isBusy(1, 2, 9, 10));
    }

    @Test
    void testIsBusy_trueWhenStartInsideExistingMeeting() throws TimeConflictException {
        Meeting m = new Meeting(2, 3, 9, 11);
        calendar.addMeeting(m);
        assertTrue(calendar.isBusy(2, 3, 10, 12)); // start (10) inside 9..11
    }

    @Test
    void testIsBusy_trueWhenEndInsideExistingMeeting() throws TimeConflictException {
        Meeting m = new Meeting(2, 4, 14, 16);
        calendar.addMeeting(m);
        assertTrue(calendar.isBusy(2, 4, 13, 15)); // end (15) inside 14..16
    }

    @Test
    void testIsBusy_falseWhenQueryWrapsExistingMeetingEndpointsOutside() throws TimeConflictException {
        // According to current implementation, only start or end inside counts as busy
        Meeting m = new Meeting(2, 5, 10, 12);
        calendar.addMeeting(m);
        assertFalse(calendar.isBusy(2, 5, 9, 13));
    }

    @Test
    void testCheckTimes_boundaryHoursValid() {
        assertDoesNotThrow(() -> Calendar.checkTimes(12, 31, 0, 1));
        assertDoesNotThrow(() -> Calendar.checkTimes(1, 1, 22, 23));
    }

    @Test
    void testPrintAgendaForSpecificDayHeaderAndContent() throws TimeConflictException {
        Meeting m = new Meeting(10, 6, 8, 9);
        calendar.addMeeting(m);
        String agenda = calendar.printAgenda(10, 6);
        assertTrue(agenda.startsWith("Agenda for 10/6:"));
        assertTrue(agenda.contains("10/6"));
    }

    @Test
    void testRemoveMeeting_invalidIndexThrows() throws TimeConflictException {
        Meeting m = new Meeting(11, 11, 9, 10);
        calendar.addMeeting(m);
        assertThrows(IndexOutOfBoundsException.class, () -> calendar.removeMeeting(11, 11, 5));
    }

    @Test
    void testClearScheduleWithMultipleMeetings() throws TimeConflictException {
        calendar.addMeeting(new Meeting(12, 24, 9, 10));
        calendar.addMeeting(new Meeting(12, 24, 11, 12));
        calendar.clearSchedule(12, 24);
        assertFalse(calendar.isBusy(12, 24, 9, 10));
        assertFalse(calendar.isBusy(12, 24, 11, 12));
    }
}
