package edu.sc.csce747.MeetingPlanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

class MeetingTest {

    @Test
    void testConstructor_allDayWithDescription() {
        Meeting m = new Meeting(6, 26, "Midsommar");
        assertEquals(6, m.getMonth());
        assertEquals(26, m.getDay());
        assertEquals(0, m.getStartTime());
        assertEquals(23, m.getEndTime());
        assertEquals("Midsommar", m.getDescription());
    }

    @Test
    void testConstructor_detailedWithoutAttendees() {
        Meeting m = new Meeting(5, 10, 9, 11);
        assertEquals(5, m.getMonth());
        assertEquals(10, m.getDay());
        assertEquals(9, m.getStartTime());
        assertEquals(11, m.getEndTime());
    }

    @Test
    void testConstructor_fullDetailedSetsAllFields() {
        ArrayList<Person> people = new ArrayList<>();
        Room room = new Room("101");
        Meeting m = new Meeting(8, 15, 13, 15, people, room, "Status update");

        assertEquals(8, m.getMonth());
        assertEquals(15, m.getDay());
        assertEquals(13, m.getStartTime());
        assertEquals(15, m.getEndTime());
        assertEquals(people, m.getAttendees());
        assertEquals(room, m.getRoom());
        assertEquals("Status update", m.getDescription());
    }

    @Test
    void testAddAndRemoveAttendee() {
        ArrayList<Person> attendees = new ArrayList<>();
        Room room = new Room("B2");
        Meeting m = new Meeting(4, 5, 10, 12, attendees, room, "Team meeting");

        Person alice = new Person("Alice");
        Person bob = new Person("Bob");

        m.addAttendee(alice);
        m.addAttendee(bob);
        assertTrue(m.getAttendees().contains(alice));
        assertTrue(m.getAttendees().contains(bob));

        m.removeAttendee(alice);
        assertFalse(m.getAttendees().contains(alice));
        assertTrue(m.getAttendees().contains(bob));
    }

    @Test
    void testToStringContainsAllInfo() {
        ArrayList<Person> attendees = new ArrayList<>();
        attendees.add(new Person("Alice"));
        attendees.add(new Person("Bob"));
        Room room = new Room("C3");
        Meeting m = new Meeting(7, 22, 9, 11, attendees, room, "Weekly sync");

        String info = m.toString();
        assertTrue(info.contains("7/22"));
        assertTrue(info.contains("9 - 11"));
        assertTrue(info.contains("C3"));
        assertTrue(info.contains("Weekly sync"));
        assertTrue(info.contains("Alice"));
        assertTrue(info.contains("Bob"));
    }

    @Test
    void testSettersAndGetters() {
        Meeting m = new Meeting(1, 1, "Temp");
        m.setMonth(12);
        m.setDay(31);
        m.setStartTime(8);
        m.setEndTime(9);
        m.setDescription("Year end");

        assertEquals(12, m.getMonth());
        assertEquals(31, m.getDay());
        assertEquals(8, m.getStartTime());
        assertEquals(9, m.getEndTime());
        assertEquals("Year end", m.getDescription());

        Room r = new Room("Z9");
        m.setRoom(r);
        assertEquals("Z9", m.getRoom().getID());
    }
}