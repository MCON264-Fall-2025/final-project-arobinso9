package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.service.GuestListManager;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GuestListMangerTest {

    @Test
    void testAddGuestStoresInMasterList() {
        //arrange
        GuestListManager manager = new GuestListManager();
        Guest guest = new Guest("Aviva", "Family");
        //act
        manager.addGuest(guest);
        // assert- Should be present in the LinkedList
        assertTrue(manager.getAllGuests().contains(guest));
    }

    @Test
    void testAddGuestIsFindableInMap() {
        //arrange
        GuestListManager manager = new GuestListManager();
        Guest guest = new Guest("Aviva", "Family");
        //act
        manager.addGuest(guest);
        // assert- Should be found in Map via findGuest
        assertEquals(guest, manager.findGuest("Aviva"));
    }

    @Test
    void testRemoveGuestClearsFromList() {
        //arrange
        GuestListManager manager = new GuestListManager();
        Guest guest = new Guest("Aviva", "Friends");
        //act
        manager.addGuest(guest);
        manager.removeGuest("Aviva");
        // assert- Guest should no longer be in the LinkedList
        assertFalse(manager.getAllGuests().contains(guest));
    }

    @Test
    void testRemoveGuestClearsFromMap() {
        //arrange
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Aviva", "Friends"));
        //act
        manager.removeGuest("Aviva");
        // assert- Guest should no longer be in the Map
        assertNull(manager.findGuest("Aviva"));
    }

    @Test
    void testDuplicateNameIsNotAdded() {
        //arrange
        GuestListManager manager = new GuestListManager();
        manager.addGuest(new Guest("Aviva", "Family"));
        manager.addGuest(new Guest("Aviva", "Work"));

        //assert- Should ignore duplicates based on the Name key in Map
        assertEquals(1, manager.getGuestCount());
    }
    @Test
    void testLookingUpGuestByName() {
        GuestListManager manager = new GuestListManager();
        Guest guest = new Guest("Aviva", "Co-Worker");
        manager.addGuest(guest);

        // Behavior: Required lookup functionality (checks the HashMap efficiency)
        Guest found = manager.findGuest("Aviva");
        assertNotNull(found, "Lookup should find the guest by name");
        assertEquals("Co-Worker", found.getGroupTag(), "Lookup should return the correct guest data");
    }
}