package edu.course.eventplanner;

import edu.course.eventplanner.util.Generators;
import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    @Test
    void testVenueGeneration() {
        // arrange
        Generators g = new Generators();

        // act
        List<Venue> venues = g.generateVenues();

        // assert - should generate a non-empty list of sample venues
        assertNotNull(venues);
        assertFalse(venues.isEmpty());
    }

    @Test
    void testGuestGeneration() {
        // arrange
        Generators g = new Generators();

        // act
        List<Guest> guests = g.GenerateGuests(10);

        // assert - should create exactly the number of guests requested
        assertNotNull(guests);
        assertEquals(10, guests.size());
    }
}