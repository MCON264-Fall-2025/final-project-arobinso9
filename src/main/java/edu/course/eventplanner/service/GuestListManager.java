package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Guest;
import java.util.*;

public class GuestListManager {
    private final LinkedList<Guest> guests = new LinkedList<>();
    private final Map<String, Guest> guestByName = new HashMap<>();
    private int size = 0;

    public void addGuest(Guest guest) {
        if (guest == null)
            return;
        String name = guest.getName();

        // Prevent duplicates
        if (guestByName.containsKey(name)) {
            return;
        }

        //add to the linked list and to the map
        guests.addLast(guest);
        guestByName.put(name, guest);
        size++;
    }

    public boolean removeGuest(String guestName) {
        //  O(1) Lookup: Use the map to find the object instantly
        Guest toRemove = guestByName.get(guestName);

        if (toRemove == null) {
            return false;
        }

        //Remove from both to keep them in sync
        guests.remove(toRemove);
        guestByName.remove(guestName);

        size--;
        return true;
    }

    public Guest findGuest(String guestName) {
        return guestByName.get(guestName);
    }

    public int getGuestCount() {
        return size;
    }

    public List<Guest> getAllGuests() {
        return guests;
    }
}