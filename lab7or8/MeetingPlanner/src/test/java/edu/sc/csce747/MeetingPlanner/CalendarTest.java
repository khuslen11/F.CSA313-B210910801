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
}