package edu.course.eventplanner;
import edu.course.eventplanner.model.Guest;
import edu.course.eventplanner.model.Venue;
import edu.course.eventplanner.service.GuestListManager;
import edu.course.eventplanner.service.VenueSelector;
import edu.course.eventplanner.util.Generators;

import java.util.*;
public class Main {
    public static void main(String[] args) {

        List<Venue> sampleVenues= new ArrayList<>();
        List<Guest> sampleGuests= new ArrayList<>();
        Generators g = new Generators();
        GuestListManager guestListManager = new GuestListManager();
        VenueSelector venueSelector;
        Venue currentSelectedVenue=null;
        Scanner input = new Scanner(System.in);
        boolean proceed =true;
        menu();
        int choice= input.nextInt();
        while(choice!=10) {
            switch (choice){
                case 1:
                    sampleVenues = g.generateVenues();
                    System.out.println("How many guests are you inviting?");
                    int numOfGuests = input.nextInt();
                    sampleGuests = g.GenerateGuests(numOfGuests);
                    for(Guest sg : sampleGuests)
                        guestListManager.addGuest(sg);
                    break;
                case 2:
                    input.nextLine();
                    System.out.println("Whats the name of the guest?");
                    String name = input.nextLine();
                    System.out.println("Whats the group tag of the guest?");
                    String groupTag = input.nextLine();
                    Guest guest = new Guest(name,groupTag);
                    guestListManager.addGuest(guest);
                    System.out.println(name+ " was successfully added");
                    break;
                case 3:
                    input.nextLine();
                    System.out.println("Whats the name of the guest you want to remove?");
                    name = input.nextLine();
                    guestListManager.removeGuest(name);
                    break;
                case 4:
                    //SELECT A VENUE FROM THE GENERATED LIST? OR CAN THEY ALSO CREATE A VENUE?
                    if (sampleVenues.isEmpty()) {
                        System.out.println("Error: Load sample data first (Option 1).");
                    } else {
                        System.out.println("What is your total budget?");
                        double budget = input.nextDouble();
                        // We use the count from the manager
                        int totalGuests = guestListManager.getGuestCount();
                        venueSelector = new VenueSelector(sampleVenues);
                        Venue chosen = venueSelector.selectVenue(budget, totalGuests);

                        if (chosen != null) {
                            System.out.println("The best venue for you is: " + chosen.getName());
                            // Store this chosen venue in a variable so that case 5 can use it for seating
                            currentSelectedVenue = chosen;
                        } else {
                            System.out.println("No venue fits your budget and guest count.");
                        }
                    }
                     break;
                case 5:
                    if (currentSelectedVenue == null) {
                        System.out.println("Error: You must select a venue in Option 4 first!");
                    }
                    else{

                    }
                     break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
            }

        }

        System.out.println("Event Planner Mini — see README for instructions.");
    }
    public static void menu(){
        System.out.println(" Enter the number of the option you want to do\n"+
                        "1- Load sample data\n" +
                "2- Add guest\n" +
                "3- Remove guest\n" +
                "4- Select venue\n" +
                "5- Generate seating chart\n" +
                "6- Add preparation task\n" +
                "7- Execute next task\n" +
                "8- Undo last task\n" +
                "9- Print event summar\n"+
                "10- Exit");
    }
}
