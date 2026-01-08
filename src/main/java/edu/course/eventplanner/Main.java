package edu.course.eventplanner;

import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.model.Task;
import edu.course.eventplanner.service.GuestListManager;
import edu.course.eventplanner.service.SeatingPlanner;
import edu.course.eventplanner.service.TaskManager;
import edu.course.eventplanner.service.VenueSelector;
import edu.course.eventplanner.util.Generators;

import java.util.*;

public class Main {
    private static List<Venue> sampleVenues = new ArrayList<>();
    private static List<Guest> sampleGuests = new ArrayList<>();
    private static Generators g = new Generators();
    private static GuestListManager guestListManager = new GuestListManager();
    private static TaskManager taskManager = new TaskManager();
    private static Venue currentSelectedVenue = null;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        menu();
        int choice = input.nextInt();

        while (choice != 10) {
            switch (choice) {
                case 1:
                    System.out.println("How many guests are you inviting?");
                    int num = input.nextInt();
                    loadData(num);
                    break;
                case 2:
                    input.nextLine(); // clear buffer
                    System.out.println("Whats the name of the guest?");
                    String name = input.nextLine();
                    System.out.println("Whats the group tag of the guest?");
                    String tag = input.nextLine();
                    addGuest(name, tag);
                    break;
                case 3:
                    input.nextLine(); // Clear buffer
                    System.out.println("Whats the name of the guest you want to remove?");
                    String Name = input.nextLine();
                    System.out.println("Whats the group tag of the guest you want to remove?");
                    String Tag = input.nextLine();
                    removeGuest(Name, Tag);
                    break;
                case 4:
                    if (sampleVenues.isEmpty()) {
                        System.out.println("Error: Load sample data first (Option 1).");
                    } else {
                        System.out.println("What is your total budget?");
                        double budget = input.nextDouble();
                        selectVenue(budget);
                    }
                    break;
                case 5:
                    generateSeating();
                    break;
                case 6:
                    input.nextLine(); // clear buffer
                    System.out.print("Enter the task description: ");
                    String taskDesc = input.nextLine();
                    addTask(taskDesc);
                    break;
                case 7:
                    executeTask();
                    break;
                case 8:
                    undoTask();
                    break;
                case 9:
                    printSummary();
                    break;
            }
            menu();
            choice = input.nextInt();
        }
        System.out.println("Happy Planning! Have a great day!");
    }

    public static void loadData(int numOfGuests) {
        sampleVenues = g.generateVenues();
        sampleGuests = g.GenerateGuests(numOfGuests);
        for (Guest sg : sampleGuests)
            guestListManager.addGuest(sg);
        System.out.println("Invite successful :)");
    }

    public static void addGuest(String name, String groupTag) {
        Guest guest = new Guest(name, groupTag);
        guestListManager.addGuest(guest);
        System.out.println(name + " with GroupTag: " + groupTag + " was successfully added");
    }

    public static void removeGuest(String nameToRemove, String tagToRemove) {
        String nameAndGuest = nameToRemove + "-" + tagToRemove;
        boolean isGuestRemoved = guestListManager.removeGuest(nameAndGuest);
        if (isGuestRemoved) {
            System.out.println(nameToRemove + " with tag: " + tagToRemove + " was successfully removed");
        } else {
            System.out.println(nameToRemove + "with tag: " + tagToRemove + " was not removed because it is not in the group of guests");
        }
    }

    public static void selectVenue(double budget) {
        // we use the count from the manager
        int totalGuests = guestListManager.getGuestCount();
        VenueSelector venueSelector = new VenueSelector(sampleVenues);
        Venue chosen = venueSelector.selectVenue(budget, totalGuests);

        if (chosen != null) {
            System.out.println("The best venue for you is: " + chosen.getName());
            // we store this chosen venue in a variable so that case 5 can use it for seating
            currentSelectedVenue = chosen;
        } else {
            System.out.println("No venue fits your budget and guest count.");
        }
    }


    public static void generateSeating() {
        if (currentSelectedVenue == null) {
            System.out.println("Error: You must select a venue in Option 4 first!");
        } else {
            SeatingPlanner planner = new SeatingPlanner(currentSelectedVenue);
            Map<Integer, List<Guest>> chart = planner.generateSeating(guestListManager.getAllGuests());
            System.out.println("SEATING CHART\n");
            for (Integer tableNum : chart.keySet()) {
                System.out.print("Table " + tableNum + ": ");
                for (Guest z : chart.get(tableNum)) {
                    System.out.print("Name: " + z.getName() + "GroupTag: " + z.getGroupTag() + " | ");
                }
                System.out.println();
            }
        }
    }


    public static void addTask(String taskDesc) {
        taskManager.addTask(new Task(taskDesc));
        System.out.println("Task added to queue.");
    }


    public static void executeTask() {
        Task executed = taskManager.executeNextTask();
        if (executed != null) {
            System.out.println("Executed: " + executed.getDescription());
        } else {
            System.out.println("No tasks to execute.");
        }
    }


    public static void undoTask() {
        Task undone = taskManager.undoLastTask();
        if (undone != null) {
            System.out.println("Undone: " + undone.getDescription());
        } else {
            System.out.println("Nothing to undo.");
        }
    }

    public static void printSummary() {
        System.out.println("EVENT SUMMARY\n");
        System.out.println("Venue: " + (currentSelectedVenue != null ? currentSelectedVenue.getName() : "None"));
        System.out.println("Guest Count: " + guestListManager.getGuestCount());
        System.out.println("Pending Tasks: " + taskManager.remainingTaskCount());
        System.out.println("All Guests: ");
        for (Guest w : guestListManager.getAllGuests()) {
            System.out.println("Name: " + w.getName() + " | GroupTag: " + w.getGroupTag());
        }
    }

    public static void menu() {
        System.out.println(" Enter the number of the option you want to do\n" +
                "1- Load sample data\n" +
                "2- Add guest\n" +
                "3- Remove guest\n" +
                "4- Select venue\n" +
                "5- Generate seating chart\n" +
                "6- Add preparation task\n" +
                "7- Execute next task\n" +
                "8- Undo last task\n" +
                "9- Print event summary\n" +
                "10- Exit");
    }
}