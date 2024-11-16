package App;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;
import Model.Client;
import Model.Offering;
import Model.Booking;
import DatabaseConnection.DatabaseConnection;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection = DatabaseConnection.getConnection();

        if (connection != null) {
            try {
                boolean running = true;
                while (running) {
                    System.out.println("\nWelcome to the Lesson Booking System!");
                    System.out.println("Please select an operation:");
                    System.out.println("1. Register a new client");
                    System.out.println("2. View available offerings");
                    System.out.println("3. Book a lesson");
                    System.out.println("4. View all clients");
                    System.out.println("5. Update a client");
                    System.out.println("6. Delete a client");
                    System.out.println("0. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = Integer.parseInt(scanner.nextLine());

                    switch (choice) {
                        case 1:
                            System.out.println("Registering a new client...");
                            System.out.print("Enter client ID: ");
                            int clientId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter age: ");
                            int age = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter guardian ID (or press Enter if not applicable): ");
                            String guardianInput = scanner.nextLine();
                            Integer guardianId = guardianInput.isEmpty() ? null : Integer.parseInt(guardianInput);
                            System.out.print("Enter phone number: ");
                            String phoneNumber = scanner.nextLine();

                            Client newClient = new Client(clientId, name, age, guardianId, phoneNumber);
                            if (newClient.createClient(connection)) {
                                System.out.println("Client registered successfully!");
                            } else {
                                System.out.println("Failed to register client.");
                            }
                            break;

                        case 2:
                            System.out.println("Viewing available offerings...");
                            List<Offering> offerings = Offering.getAllOfferings(connection);
                            if (offerings.isEmpty()) {
                                System.out.println("No offerings found in the database.");
                            } else {
                                for (Offering offering : offerings) {
                                    System.out.println("ID: " + offering.getId() +
                                                       ", Lesson Type: " + offering.getLessonType() +
                                                       ", Instructor ID: " + offering.getInstructorId() +
                                                       ", Location ID: " + offering.getLocationId() +
                                                       ", Schedule ID: " + offering.getScheduleId() +
                                                       ", Is Group: " + (offering.isGroup() ? "Yes" : "No"));
                                }
                            }
                            break;




                        case 3:
                            System.out.println("Booking a lesson...");
                            System.out.print("Enter booking ID: ");
                            int bookingId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter client ID: ");
                            int clientId1 = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter offering ID: ");
                            int offeringId = Integer.parseInt(scanner.nextLine()); // Make sure this variable is declared
                            System.out.print("Enter booking date (YYYY-MM-DD): ");
                            String bookingDate = scanner.nextLine();
                            System.out.print("Enter booking status: ");
                            String status = scanner.nextLine();

                            Booking booking = new Booking(bookingId, clientId1, offeringId, bookingDate, status);
                            if (booking.createBooking(connection)) {
                                System.out.println("Lesson booked successfully!");
                            } else {
                                System.out.println("Failed to book the lesson.");
                            }
                            break;


                        case 4:
                            System.out.println("Viewing all clients...");
                            List<Client> clients = Client.getAllClients(connection);
                            if (clients.isEmpty()) {
                                System.out.println("No clients found in the database.");
                            } else {
                                for (Client client : clients) {
                                    System.out.println("ID: " + client.getId() + ", Name: " + client.getName() + ", Age: " + client.getAge() +
                                            ", Guardian ID: " + (client.getGuardianId() != null ? client.getGuardianId() : "N/A") +
                                            ", Phone Number: " + client.getPhoneNumber());
                                }
                            }
                            break;


                        case 5:
                            System.out.println("Updating a client...");
                            System.out.print("Enter client ID to update: ");
                            clientId1 = Integer.parseInt(scanner.nextLine());
                            Client clientToUpdate = Client.readClient(connection, clientId1);
                            if (clientToUpdate != null) {
                                System.out.print("Enter new name (leave blank to keep current: " + clientToUpdate.getName() + "): ");
                                name = scanner.nextLine();
                                if (!name.isEmpty()) {
                                    clientToUpdate.setName(name);
                                }

                                System.out.print("Enter new age (leave blank to keep current: " + clientToUpdate.getAge() + "): ");
                                String ageInput = scanner.nextLine();
                                if (!ageInput.isEmpty()) {
                                    age = Integer.parseInt(ageInput);
                                    clientToUpdate.setAge(age);
                                }

                                System.out.print("Enter new guardian ID (leave blank to keep current: " + clientToUpdate.getGuardianId() + "): ");
                                guardianInput = scanner.nextLine();
                                if (!guardianInput.isEmpty()) {
                                    guardianId = Integer.parseInt(guardianInput);
                                    clientToUpdate.setGuardianId(guardianId);
                                }

                                System.out.print("Enter new phone number (leave blank to keep current: " + clientToUpdate.getPhoneNumber() + "): ");
                                phoneNumber = scanner.nextLine();
                                if (!phoneNumber.isEmpty()) {
                                    clientToUpdate.setPhoneNumber(phoneNumber);
                                }

                                if (clientToUpdate.updateClient(connection)) {
                                    System.out.println("Client updated successfully!");
                                } else {
                                    System.out.println("Failed to update client.");
                                }
                            } else {
                                System.out.println("Client not found.");
                            }
                            break;

                        case 6:
                            System.out.println("Deleting a client...");
                            System.out.print("Enter client ID to delete: ");
                            clientId1 = Integer.parseInt(scanner.nextLine());
                            if (Client.deleteClient(connection, clientId1)) {
                                System.out.println("Client deleted successfully!");
                            } else {
                                System.out.println("Failed to delete client.");
                            }
                            break;

                        case 0:
                            System.out.println("Exiting the system. Goodbye!");
                            running = false;
                            break;

                        default:
                            System.out.println("Invalid choice. Please try again.");
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DatabaseConnection.closeConnection(connection);
                scanner.close();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
    /*
    SELECT * FROM Clients;
	SELECT * FROM Locations;
	SELECT * FROM Instructors;
	SELECT * FROM Schedules;
	SELECT * FROM Offerings;
	SELECT * FROM Bookings;
	SELECT * FROM Admins;
    */
        
}

