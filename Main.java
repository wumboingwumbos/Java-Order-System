import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Main class for the application that manages customer accounts and orders.
 */
public class Main {
    static boolean loggedIn = false;
    static Customer activeCustomer;
    static ArrayList<Customer> createdCustomers = new ArrayList<>();
    static Scanner in = new Scanner(System.in);
    static int shop = 1;

    /**
     * The main method is the entry point of the application.
     * It initializes demo customers and handles the main loop for user interaction.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Initialize demo customers
        Customer DemoCustomer1 = new Customer("Demo Alpha",
                                              "123 Creek St",
                                              "1234567890",
                                              "000000",
                                              "DemoPass1@",
                                              0,
                                              "At Home");

        Customer DemoCustomer2 = new Customer("Demo Beta",
                                              "321 River Rd",
                                              "0987654321",
                                              "111111",
                                              "DemoPass2#",
                                              1,
                                              "Balrog");

        createdCustomers.add(DemoCustomer1);
        createdCustomers.add(DemoCustomer2);
        int q1;

        // Main interaction loop
        while (shop == 1) {
            if (!loggedIn) {
                System.out.println("------- \"Welcome weary traveler please enter:\" ------");
                System.out.println("1: Put it on my tab");
                System.out.println("2: Open new tab");
                System.out.println("3: Leave");
                q1 = in.nextInt();
                switch (q1) {
                    case 1 -> logOn();
                    case 2 -> createAccount();
                    case 3 -> {
                        System.out.println("\n------\"Don't waste my time! Begone!\"------");
                        shop = 0;
                    }
                    case 4 -> listCustomers();
                    default -> System.out.println("You fool that's not an option!");
                }
            }

            if (loggedIn) {
                System.out.println("------\"What can I help you with?\"------");
                System.out.println("1: View Catalog");
                System.out.println("2: Choose Items and Make Order");
                System.out.println("3: View Order");
                System.out.println("4: Log Out");
                try {
                    q1 = in.nextInt();
                    switch (q1) {
                        case 1 -> {
                            Catalog.printDescriptions();
                            break;
                        }
                        case 2 -> selectItems();
                        case 3 -> {
                            Order orderView = activeCustomer.myOrders.getLast();
                            viewOrder(orderView);
                        }
                        case 4 -> {
                            System.out.println("Goodbye!");
                            loggedIn = false;
                        }
                        default -> System.out.println("You fool that's not an option!");
                    }
                } catch (Exception e) {
                    System.out.println("------\"You're speaking nonsense! Leave at once!\"------");
                    shop = 0;
                }
            }
        }
    }

    /**
     * Logs in a customer by verifying their ID, password, and security question.
     */
    public static void logOn() {
        loggedIn = false;
        Scanner in3 = new Scanner(System.in);
        String enteredID;
        String enteredPass;

        System.out.println("Please enter your identification number");
        enteredID = in3.nextLine();
        System.out.println("And what is your super secret code?");
        enteredPass = in3.nextLine();
        int runCustomer = 1;
        while (runCustomer == 1) {
            for (Customer customer : createdCustomers) {
                if (customer.iD.equals(enteredID)) {
                    for (int i = 2; i > 0; i--) {
                        if (customer.pass.equals(enteredPass)) {
                            System.out.println(SecurityQuestions.securityQuestions[customer.securityQ]);
                            String enteredAns = in3.nextLine();
                            if (enteredAns.equals(customer.ans)) {
                                System.out.println("------\"Welcome " + customer.name + "\"------");
                                runCustomer = 0;
                                activeCustomer = customer;
                                loggedIn = true;
                                break;
                            } else {
                                System.out.println("Imposter!");
                                System.exit(0);
                            }
                        } else {
                            System.out.println("------\"Incorrect! " + i + " attempts left\"------");
                            System.out.println("Password: ");
                            enteredPass = in3.nextLine();
                            if (i == 0) {
                                System.out.println("------\"Login Failed!\"------");
                            }
                            System.exit(0);
                        }
                    }
                }
            }
            if (!loggedIn) {
                System.out.println("------\"Hmmm you're not on my list\"------");
                System.out.println("1: Enter different ID number");
                System.out.println("2: Create New Account");
                String q3 = in3.nextLine();
                try {
                    switch (q3) {
                        case "1" -> {
                            System.out.println("Enter Different ID: ");
                            enteredID = in3.nextLine();
                            break;
                        }
                        case "2" -> {
                            runCustomer = 0;
                            createAccount();
                            break;
                        }
                        default -> System.out.println("------\"Not a Choice\"------");
                    }
                } catch (Exception e) {
                    System.out.println("------\"Speaking gibberish!\"------");
                    break;
                }
                loggedIn = true;
                in3.close();
            }
        }
    }

    /**
     * Creates a new customer account by collecting personal information and setting up a password.
     */
    public static void createAccount() {
        Scanner in2 = new Scanner(System.in);
        String name, addr, credCard, pass, ans;
        int securityQ;
        System.out.println("------\"Welcome to My Store! Tell me about yourself: \"------");
        System.out.println("Name: ");
        name = in2.nextLine();
        System.out.println("Address: ");
        addr = in2.nextLine();
        System.out.println("Credit Card: ");
        credCard = in2.nextLine();
        System.out.println("Desired Password: ");
        pass = in2.nextLine();
        while (!isValidPassword(pass)) {
            System.out.println("Password must contain at least a digit, a special character such as @, #, $, %, &, and *, and an upper case letter.");
            System.out.println("Try again: ");
            pass = in2.nextLine();
        }
        System.out.println("Select a security question: ");
        SecurityQuestions.list();
        securityQ = in2.nextInt();
        in2.nextLine();
        System.out.println("Answer to question: ");
        ans = in2.nextLine();
        Customer newCustomer = new Customer(name, addr, credCard, generateID(), pass, securityQ, ans);
        System.out.println("------\"This is your new ID number, don't forget it\" : " + newCustomer.iD + " ------");
        System.out.println();
        System.out.println(newCustomer.toString());
        createdCustomers.add(newCustomer);
    }

    /**
     * Checks if the provided password meets the required criteria.
     *
     * @param password the password to validate
     * @return true if the password is valid, false otherwise
     */
    public static boolean isValidPassword(String password) {
        // Check for at least one digit
        boolean hasDigit = password.matches(".*\\d.*");

        // Check for at least one special character
        boolean hasSpecialChar = password.matches(".*[@#$%&*].*");

        // Check for at least one uppercase letter
        boolean hasUpperCase = password.matches(".*[A-Z].*");

        // Password is valid only if all conditions are met
        return hasDigit && hasSpecialChar && hasUpperCase;
    }

    /**
     * Allows the user to select items from the catalog and place an order.
     */
    public static void selectItems() {
        Scanner in4 = new Scanner(System.in);
        Order newOrder = new Order("7/9/24");
        int i = 0;
        int quant;
        String newCard;
        double finalTotal = 0;
        for (String items : Catalog.item) {
            System.out.println("How many " + items + " would you like?");
            quant = in4.nextInt();
            newOrder.addItemToOrder(i, quant);
        }
        newOrder.setAuthCode(generateID());
        activeCustomer.myOrders.add(newOrder);
        int q4;
        viewOrder(newOrder);
        System.out.println("How would you like to receive your order?");
        System.out.println("1: By Mail ($3.00)");
        System.out.println("2: In-Store Pickup (Free)");
        q4 = in4.nextInt();
        in4.nextLine();
        switch (q4) {
            case 1 -> finalTotal = newOrder.getTotal() + 3;
            case 2 -> finalTotal = newOrder.getTotal();
            default -> System.out.println("Not a choice");
        }
        System.out.println("Your Final Total is: " + finalTotal);
        System.out.println("Charging to card: " + activeCustomer.getCredCard());
        System.out.println(".\n.\n.\n.");
        while (activeCustomer.credCard.contains("9")) {
            System.out.println("Card Declined. Please enter a new card (with no 9s)");
            newCard = in4.nextLine();
            activeCustomer.setCredCard(newCard);
        }
        System.out.println("----------------------------");
        System.out.println("Purchase successful. Your Authorization code is: " + generateAuthCode());
    }

    /**
     * Displays the details of an order.
     *
     * @param orderView the order to be viewed
     */
    public static void viewOrder(Order orderView) {
        for (int i = 0; i < 9; i++) {
            System.out.println(Catalog.item[i] + "--------" + orderView.quant.get(i));
            System.out.println("    Price: " + Catalog.getPrice(i) * orderView.quant.get(i));
        }
        System.out.println("---------------------Total--------------------------");
        System.out.println("Total # of Items: " + orderView.getQuant());
        System.out.println("Total Price: " + orderView.getTotal());
        System.out.println("Order Date: " + orderView.getDate());
        System.out.println();
    }

    /**
     * Generates a random 6-digit ID for a customer.
     *
     * @return a 6-digit ID as a string
     */
    public static String generateID() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        // This will convert any number sequence into 6 characters.
        return String.format("%06d", number);
    }

    /**
     * Generates a random 4-digit authorization code.
     *
     * @return a 4-digit authorization code
     */
    public static int generateAuthCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return number;
    }

    /**
     * Lists all created customers with their details.
     */
    public static void listCustomers() {
        for (Customer customer : createdCustomers) {
            System.out.println(customer.toString());
        }
    }
}
