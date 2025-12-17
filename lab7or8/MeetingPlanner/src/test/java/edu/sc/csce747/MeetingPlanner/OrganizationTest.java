package edu.sc.csce747.MeetingPlanner;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OrganizationTest {

    @Test
    void testDefaultConstructor_initializesPeopleAndRooms() {
        Organization org = new Organization();

        assertNotNull(org.getEmployees(), "Employees list should not be null");
        assertNotNull(org.getRooms(), "Rooms list should not be null");

        assertFalse(org.getEmployees().isEmpty(), "Employees list should not be empty");
        assertFalse(org.getRooms().isEmpty(), "Rooms list should not be empty");

        assertEquals("Greg Gay", org.getEmployees().get(0).getName());
        assertEquals("2A01", org.getRooms().get(0).getID());
    }

    @Test
    void testGetRoom_returnsCorrectRoom() throws Exception {
        Organization org = new Organization();
        Room room = org.getRoom("2A03");
        assertEquals("2A03", room.getID());
    }

    @Test
    void testGetRoom_throwsExceptionForInvalidID() {
        Organization org = new Organization();
        Exception e = assertThrows(Exception.class, () -> org.getRoom("9999"));
        assertTrue(e.getMessage().contains("does not exist"));
    }

    @Test
    void testGetEmployee_returnsCorrectPerson() throws Exception {
        Organization org = new Organization();
        Person p = org.getEmployee("John Rose");
        assertEquals("John Rose", p.getName());
    }

    @Test
    void testGetEmployee_throwsExceptionForInvalidName() {
        Organization org = new Organization();
        Exception e = assertThrows(Exception.class, () -> org.getEmployee("Unknown Person"));
        assertTrue(e.getMessage().contains("does not exist"));
    }

    // Added test for counts to improve coverage
    @Test
    void testDefaultCounts() {
        Organization org = new Organization();
        assertEquals(5, org.getEmployees().size());
        assertEquals(5, org.getRooms().size());
    }
}