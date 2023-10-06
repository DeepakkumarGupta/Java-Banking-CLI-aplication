package Bank;

import java.util.List;

public class Admin {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void getAllAccountDetails(List<User> userList) {
        System.out.println("All Account Details:");
        for (User user : userList) {
            System.out.println("Username: " + user.getUsername());
            System.out.println("Full Name: " + user.getFullName());
            System.out.println("Age: " + user.getAge());
            System.out.println("Phone Number: " + user.getPhoneNumber());
            System.out.println("Address: " + user.getAddress());
            System.out.println("Account Number: " + user.getAccountNumber());
            System.out.println("Balance: " + user.getBalance());
            System.out.println("------------");
        }
    }

    public void deleteAccount(List<User> userList, String username) {
        User userToDelete = findUserByUsername(userList, username);
        if (userToDelete != null) {
            userList.remove(userToDelete);
            System.out.println("Account for user '" + username + "' deleted successfully.");
        } else {
            System.out.println("User not found. Account deletion failed.");
        }
    }

    public void createAccount(List<User> userList, String username, String password, String fullName, int age,
                              String phoneNumber, String address) {
        // Generate a unique account number (you can implement your logic)
        int newAccountNumber = generateUniqueAccountNumber();

        // Create a new User object with additional details and add it to the user list
        User newUser = new User(username, password, newAccountNumber, 0.0, fullName, age, phoneNumber, address); // Starting balance is 0
        userList.add(newUser);

        System.out.println("Account created successfully for user '" + username + "'. Account number is: " + newAccountNumber);
    }

    public double getTotalBankBalance(List<User> userList) {
        double totalBalance = 0;
        for (User user : userList) {
            totalBalance += user.getBalance();
        }
        return totalBalance;
    }
    
    private User findUserByUsername(List<User> userList, String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // User not found
    }

    private int generateUniqueAccountNumber() {
        // Implement your logic to generate a unique account number
        // For simplicity, you can use a counter or a random number generator
        return 1000 + (int) (Math.random() * 9000);
    }
}
