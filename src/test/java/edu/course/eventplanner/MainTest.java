package edu.course.eventplanner;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testLoadDataFeature() {
        // arrange
        int guestsToInvite = 3;

        // act & assert
        assertDoesNotThrow(() -> Main.loadData(guestsToInvite));
    }

    @Test
    void testAddGuestFeature() {
        // arrange
        String name = "Aviva";
        String tag = "Host";

        // act & assert
        assertDoesNotThrow(() -> Main.addGuest(name, tag));
    }

    @Test
    void testRemoveGuestSuccessFeature() {
        // arrange
        String name = "Aviva";
        String tag = "Host";
        Main.addGuest(name, tag); // Ensure guest exists first

        //act and assert
        assertDoesNotThrow(() -> Main.removeGuest(name, tag));
    }

    @Test
    void testRemoveGuestNotFoundBranch() {
        // arrange
        String name = "Aviva";
        String tag = "Family";

        // act and assert
        // we are testing the else branch when a guest is not found
        assertDoesNotThrow(() -> Main.removeGuest(name, tag));
    }

    @Test
    void testSelectVenueSuccessFeature() {
        // arrange
        Main.loadData(5); // we need to load the venues first
        double budget = 10000.0;

        // act & assert
        // we test the venue selection with a valid budget
        assertDoesNotThrow(() -> Main.selectVenue(budget));
    }

    @Test
    void testSelectVenueLowBudgetBranch() {
        // arrange
        Main.loadData(5);
        double budget = 1.0; // Budget too low to find a venue

        // act & assert
        // we are testing the else branch where no venue fits the budget
        assertDoesNotThrow(() -> Main.selectVenue(budget));
    }

    @Test
    void testGenerateSeatingFeature() {
        // arrange
        Main.loadData(5);
        Main.selectVenue(5000.0); // we need to select a venue before seating

        // act & assert
        assertDoesNotThrow(() -> Main.generateSeating());
    }

    @Test
    void testSeatingNoVenueErrorBranch() {
        // arrange
        // We do NOT select a venue here bc that is exactly what we are testing!!

        // act & assert
        // Testing the 'if (currentSelectedVenue == null)' error branch
        assertDoesNotThrow(() -> Main.generateSeating());
    }

    @Test
    void testAddTaskFeature() {
        // arrange
        String task = "Aviva's Party Planning";

        // act & assert
        // Testing task addition
        assertDoesNotThrow(() -> Main.addTask(task));
    }

    @Test
    void testTaskExecutionAndUndo() {
        // arrange
        Main.addTask("Check catering");

        // act & assert
        assertDoesNotThrow(() -> Main.executeTask());
        assertDoesNotThrow(() -> Main.undoTask());
    }

    @Test
    void testPrintSummaryFeature() {
        // act & assert
        assertDoesNotThrow(() -> Main.printSummary());
    }

    @Test
    void testMenuMethod() {
        // act & assert
        // we are testing that when we hit the menu() method it will work.
        assertDoesNotThrow(() -> Main.menu());
    }
}