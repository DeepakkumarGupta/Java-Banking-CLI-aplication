package Bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestBank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin("admin", "admin123");

        List<User> userList = new ArrayList<>();

        while (true) {
            // Display the main menu
            System.out.println("Welcome to the Banking Application!");
            System.out.println("1. User Registration");
            System.out.println("2. User Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");

            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // User Registration
                    System.out.println("User Registration");
                    System.out.print("Enter a username: ");
                    String newUserUsername = scanner.nextLine();
                    System.out.print("Enter a password: ");
                    String newUserPassword = scanner.nextLine();
                    System.out.print("Enter your full name: ");
                    String fullName = scanner.nextLine();
                    System.out.print("Enter your age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter your phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter your address: ");
                    String address = scanner.nextLine();

                    // Generate a unique account number (you can implement your logic)
                    int newAccountNumber = generateUniqueAccountNumber();

                    // Create a new User object with additional details and add it to the user list
                    User newUser = new User(newUserUsername, newUserPassword, newAccountNumber, 0.0, fullName, age, phoneNumber, address);
                    userList.add(newUser);

                    // Display the generated account number to the user
                    System.out.println("User registered successfully! Your account number is: " + newAccountNumber);
                    break;

                case 2:
                    // User Login
                    System.out.println("User Login");
                    System.out.print("Enter your username: ");
                    String inputUsername = scanner.nextLine();
                    System.out.print("Enter your password: ");
                    String inputPassword = scanner.nextLine();

                    // Find a matching user in the user list
                    User loggedInUser = null;
                    for (User user : userList) {
                        if (user.getUsername().equals(inputUsername) && user.getPassword().equals(inputPassword)) {
                            loggedInUser = user;
                            break; // Found a matching user, exit the loop
                        }
                    }

                    if (loggedInUser != null) {
                        // User successfully logged in
                        System.out.println("Welcome, " + loggedInUser.getUsername() + "!");

                        while (true) {
                            // Display user menu options
                            System.out.println("User Menu:");
                            System.out.println("1. Deposit Money");
                            System.out.println("2. Withdraw Money");
                            System.out.println("3. Check Account Balance");
                            System.out.println("4. Transfer Money");
                            System.out.println("5. Logout");

                            // Read user choice for banking operations
                            int userChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            switch (userChoice) {
                                case 1:
                                    // Deposit Money
                                    System.out.print("Enter the amount to deposit: ");
                                    double depositAmount = scanner.nextDouble();
                                    loggedInUser.deposit(depositAmount);
                                    break;
                                case 2:
                                    // Withdraw Money
                                    System.out.print("Enter the amount to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    loggedInUser.withdraw(withdrawAmount);
                                    break;
                                case 3:
                                    // Check Account Balance
                                    loggedInUser.checkBalance();
                                    break;

                                case 4:
                                    // Transfer Money
                                    System.out.print("Enter the recipient's account number: ");
                                    int recipientAccountNumber = scanner.nextInt();

                                    // Find the recipient user based on the account number
                                    User recipientUser = null;
                                    for (User user : userList) {
                                        if (user.getAccountNumber() == recipientAccountNumber) {
                                            recipientUser = user;
                                            break; // Found the recipient, exit the loop
                                        }
                                    }

                                    if (recipientUser != null) {
                                        System.out.print("Enter the amount to transfer: ");
                                        double transferAmount = scanner.nextDouble();

                                        if (transferAmount > 0 && transferAmount <= loggedInUser.getBalance()) {
                                            // Transfer the money
                                            loggedInUser.transfer(recipientUser, transferAmount);
                                        } else {
                                            System.out.println("Invalid transfer amount or insufficient balance.");
                                        }
                                    } else {
                                        System.out.println("Recipient account not found. Please check the account number.");
                                    }
                                    break;
                                case 5:
                                    System.out.println("Logging out.");
                                    return; // Return to the main menu
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    break;

                case 3:
                    System.out.println("Admin Login");
                    System.out.print("Enter admin username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Enter admin password: ");
                    String adminPassword = scanner.nextLine();

                    if (admin.getUsername().equals(adminUsername) && admin.getPassword().equals(adminPassword)) {
                        // Admin successfully logged in
                        System.out.println("Welcome, Admin!");

                        while (true) {
                            // Display admin menu options
                            System.out.println("Admin Menu:");
                            System.out.println("1. Delete User Account");
                            System.out.println("2. Create User Account");
                            System.out.println("3. Check Total Bank Balance");
                            System.out.println("4. Get All Account Details");
                            System.out.println("5. Logout");

                            int adminChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            switch (adminChoice) {
                                case 1:
                                    // Delete User Account
                                    System.out.print("Enter the username of the account to delete: ");
                                    String deleteUsername = scanner.nextLine();
                                    admin.deleteAccount(userList, deleteUsername);
                                    break;
                                case 2:
                                    // Create User Account
                                    System.out.print("Enter a new username: ");
                                    String newUsername = scanner.nextLine();
                                    System.out.print("Enter a new password: ");
                                    String newPassword = scanner.nextLine();
                                    System.out.print("Enter full name: ");
                                    String newFullName = scanner.nextLine();
                                    System.out.print("Enter age: ");
                                    int newAge = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline character
                                    System.out.print("Enter phone number: ");
                                    String newPhoneNumber = scanner.nextLine();
                                    System.out.print("Enter address: ");
                                    String newAddress = scanner.nextLine();
                                    admin.createAccount(userList, newUsername, newPassword, newFullName, newAge, newPhoneNumber, newAddress);
                                    break;
                                case 3:
                                    // Check Total Bank Balance
                                    double totalBalance = admin.getTotalBankBalance(userList);
                                    System.out.println("Total Bank Balance: " + totalBalance);
                                    break;
                                case 4:
                                    // Get All Account Details
                                    admin.getAllAccountDetails(userList);
                                    break;
                                case 5:
                                    System.out.println("Logging out.");
                                    return; // Return to the main menu
                                default:
                                    System.out.println("Invalid choice. Please try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid admin username or password. Please try again.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting the application.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static int generateUniqueAccountNumber() {
        // Implement your logic to generate a unique account number
        // For simplicity, you can use a counter or a random number generator
        return 1000 + (int) (Math.random() * 9000);
    }
}
