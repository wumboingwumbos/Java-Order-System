import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
                                    ////////////CREDIT CARD AND DELIVERY METHOD
import java.util.ArrayList;
import java.util.Random;

public class MainGUI extends Application {
    static boolean loggedIn = false;
    static Customer activeCustomer;
    static ArrayList<Customer> createdCustomers = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize demo customers
        Customer DemoCustomer1 = new Customer("Demo Alpha",
                "123 Creek St",
                "1234567890",
                "000000",
                "DemoPass1@",
                1,
                "At Home");

        Customer DemoCustomer2 = new Customer("Demo Beta",
                "321 River Rd",
                "0987654321",
                "111111",
                "DemoPass2#",
                2,
                "Balrog");

        createdCustomers.add(DemoCustomer1);
        createdCustomers.add(DemoCustomer2);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Final Project GUI");
        showMainMenu(primaryStage);
    }

    private void showMainMenu(Stage primaryStage) {
        VBox mainMenu = new VBox(10);
        mainMenu.setPadding(new Insets(15, 15, 15, 15));
        Label welcomeLabel = new Label("Welcome weary traveler, please enter:");
        Button loginButton = new Button("Returning Customer");
        Button createAccountButton = new Button("Im New Here");
        Button leaveButton = new Button("Leave");

        mainMenu.getChildren().addAll(welcomeLabel, loginButton, createAccountButton, leaveButton);

        // Event Handlers
        loginButton.setOnAction(e -> logOn(primaryStage));
        createAccountButton.setOnAction(e -> createAccount(primaryStage));
        leaveButton.setOnAction(e -> primaryStage.close());

        Scene mainMenuScene = new Scene(mainMenu, 300, 200);
        primaryStage.setScene(mainMenuScene);
        primaryStage.show();
    }

    private void logOn(Stage primaryStage) {
        GridPane loginPane = new GridPane();
        loginPane.setPadding(new Insets(10));
        loginPane.setVgap(8);
        loginPane.setHgap(10);

        Label idLabel = new Label("ID Number:");
        GridPane.setConstraints(idLabel, 0, 0);
        TextField idInput = new TextField();
        GridPane.setConstraints(idInput, 1, 0);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 1);
        PasswordField passInput = new PasswordField();
        GridPane.setConstraints(passInput, 1, 1);

        Button login = new Button("Login");
        GridPane.setConstraints(login, 1, 2);

        loginPane.getChildren().addAll(idLabel, idInput, passLabel, passInput, login);

        login.setOnAction(e -> {
            String enteredID = idInput.getText();
            String enteredPass = passInput.getText();
            boolean success = false;
            for (Customer customer : createdCustomers) {
                if (customer.iD.equals(enteredID) && customer.pass.equals(enteredPass)) {
                    activeCustomer = customer;
                    loggedIn = true;
                    success = true;
                    showCustomerMenu(primaryStage);
                    break;
                }                   //if idfails {ask for different id}
                                    //if pass fails {3 tries pass err}
            }
            if (!success) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid ID or Password. Please try again.");
                alert.showAndWait();
            }
        });

        Scene loginScene = new Scene(loginPane, 300, 150);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    private void createAccount(Stage primaryStage) {
        GridPane accountPane = new GridPane();
        accountPane.setPadding(new Insets(10));
        accountPane.setVgap(8);
        accountPane.setHgap(10);

        Label nameLabel = new Label("Name:");
        GridPane.setConstraints(nameLabel, 0, 0);
        TextField nameInput = new TextField();
        GridPane.setConstraints(nameInput, 1, 0);

        Label addrLabel = new Label("Address:");
        GridPane.setConstraints(addrLabel, 0, 1);
        TextField addrInput = new TextField();
        GridPane.setConstraints(addrInput, 1, 1);

        Label credCardLabel = new Label("Credit Card:");
        GridPane.setConstraints(credCardLabel, 0, 2);
        TextField credCardInput = new TextField();
        GridPane.setConstraints(credCardInput, 1, 2);

        Label passLabel = new Label("Password:");
        GridPane.setConstraints(passLabel, 0, 3);
        PasswordField passInput = new PasswordField();
        GridPane.setConstraints(passInput, 1, 3);

        Label secQLabel = new Label("Security Question:");
        GridPane.setConstraints(secQLabel, 0, 4);
        ChoiceBox<String> secQInput = new ChoiceBox<>();
        for (String question : SecurityQuestions.securityQuestions) {
            secQInput.getItems().add(question);
        }
        GridPane.setConstraints(secQInput, 1, 4);

        Label secAnsLabel = new Label("Answer:");
        GridPane.setConstraints(secAnsLabel, 0, 5);
        TextField secAnsInput = new TextField();
        GridPane.setConstraints(secAnsInput, 1, 5);

        Button createAccount = new Button("Create Account");
        GridPane.setConstraints(createAccount, 1, 6);

        accountPane.getChildren().addAll(nameLabel, nameInput, addrLabel, addrInput, credCardLabel, credCardInput,
                passLabel, passInput, secQLabel, secQInput, secAnsLabel, secAnsInput, createAccount);

        createAccount.setOnAction(e -> {
            String name = nameInput.getText();
            String addr = addrInput.getText();
            String credCard = credCardInput.getText();
            String pass = passInput.getText();
            int secQ = secQInput.getSelectionModel().getSelectedIndex();
            String secAns = secAnsInput.getText();

            if (isValidPassword(pass)) {
                Customer newCustomer = new Customer(name, addr, credCard, generateID(), pass, secQ, secAns);
                createdCustomers.add(newCustomer);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account created. Please Write Down Your ID: " + newCustomer.iD);
                alert.showAndWait();
                showMainMenu(primaryStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Password. Must contain at least a digit, a special character, and an uppercase letter.");
                alert.showAndWait();
            }
        });

        Scene accountScene = new Scene(accountPane, 400, 300);
        primaryStage.setScene(accountScene);
        primaryStage.show();
    }

    private void showCustomerMenu(Stage primaryStage) {
        VBox customerMenu = new VBox(10);
        customerMenu.setPadding(new Insets(15, 15, 15, 15));
        Label menuLabel = new Label("What can I help you with?");
        Button viewCatalogButton = new Button("View Catalog");
        Button selectItemsButton = new Button("Choose Items and Make Order");
        Button viewOrderButton = new Button("View Order");
        Button logoutButton = new Button("Log Out");

        customerMenu.getChildren().addAll(menuLabel, viewCatalogButton, selectItemsButton, viewOrderButton, logoutButton);

        viewCatalogButton.setOnAction(e ->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Catalog Descriptions " + Catalog.getAllDescriptions());
            alert.showAndWait();
            });   
        selectItemsButton.setOnAction(e -> selectItems(primaryStage));
        viewOrderButton.setOnAction(e -> {
            if (activeCustomer.myOrders.size() > 0) {
                Order orderView = activeCustomer.myOrders.get(activeCustomer.myOrders.size() - 1);
                viewOrder(orderView);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No orders to display.");
                alert.showAndWait();
            }
        });
        logoutButton.setOnAction(e -> {
            loggedIn = false;
            showMainMenu(primaryStage);
        });

        Scene customerMenuScene = new Scene(customerMenu, 300, 200);
        primaryStage.setScene(customerMenuScene);
        primaryStage.show();
    }

    private void selectItems(Stage primaryStage) {
        GridPane selectItemsPane = new GridPane();
        selectItemsPane.setPadding(new Insets(10));
        selectItemsPane.setVgap(8);
        selectItemsPane.setHgap(10);

        TextField[] quantityInputs = new TextField[Catalog.item.length];
        for (int row = 0; row < Catalog.item.length; row++) {
            Label itemLabel = new Label(Catalog.item[row]);
            GridPane.setConstraints(itemLabel, 0, row);

            TextField quantityInput = new TextField();
            quantityInput.setPromptText("Quantity");
            quantityInputs[row] = quantityInput;
            GridPane.setConstraints(quantityInput, 1, row);

            selectItemsPane.getChildren().addAll(itemLabel, quantityInput);
        }

        Button orderButton = new Button("Place Order");
        GridPane.setConstraints(orderButton, 1, Catalog.item.length);

        selectItemsPane.getChildren().add(orderButton);

        orderButton.setOnAction(e -> {
            Order newOrder = new Order("7/9/24");
            for (int i = 0; i < Catalog.item.length; i++) {
                int quant = Integer.parseInt(quantityInputs[i].getText());
                newOrder.addItemToOrder(i, quant);
            }
            newOrder.setAuthCode(generateAuthCode());
            activeCustomer.myOrders.add(newOrder);
            showOrderSummary(primaryStage, newOrder);
        });

        Scene selectItemsScene = new Scene(selectItemsPane, 400, 300);
        primaryStage.setScene(selectItemsScene);
        primaryStage.show();
    }

    private void showOrderSummary(Stage primaryStage, Order newOrder) {
        VBox orderSummary = new VBox(10);
        orderSummary.setPadding(new Insets(15, 15, 15, 15));
        Label summaryLabel = new Label("Order Summary:");
        orderSummary.getChildren().add(summaryLabel);

        for (int i = 0; i < newOrder.itemsIndex.size(); i++) {
            int index = newOrder.itemsIndex.get(i);
            int quantity = newOrder.quant.get(i);
            Label itemLabel = new Label(Catalog.item[index] + " - Quantity: " + quantity + ", Total Price: $" + (Catalog.getPrice(index) * quantity));
            orderSummary.getChildren().add(itemLabel);
        }

        Label totalLabel = new Label("Total: $" + newOrder.getTotal());
        orderSummary.getChildren().add(totalLabel);

        Button finalizeOrderButton = new Button("Finalize Order");
        orderSummary.getChildren().add(finalizeOrderButton);

        finalizeOrderButton.setOnAction(e -> {
            processPayment(newOrder);
            showCustomerMenu(primaryStage);
        });

        Scene orderSummaryScene = new Scene(orderSummary, 400, 300);
        primaryStage.setScene(orderSummaryScene);
        primaryStage.show();
    }

    private void processPayment(Order order) {
        String credCard = activeCustomer.getCredCard();
        if (credCard.contains("9")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Card Declined. Please update your card details.");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Purchase successful. Authorization code: " + order.getAuthCode());
            alert.showAndWait();
        }
    }

    private void viewOrder(Order orderView) {
        VBox orderDetails = new VBox(10);
        orderDetails.setPadding(new Insets(15, 15, 15, 15));
        Label orderLabel = new Label("Order Details:");
        orderDetails.getChildren().add(orderLabel);

        for (int i = 0; i < orderView.itemsIndex.size(); i++) {
            int index = orderView.itemsIndex.get(i);
            int quantity = orderView.quant.get(i);
            Label itemLabel = new Label(Catalog.item[index] + " - Quantity: " + quantity + ", Total Price: $" + (Catalog.getPrice(index) * quantity));
            orderDetails.getChildren().add(itemLabel);
        }

        Label totalLabel = new Label("Total: $" + orderView.getTotal());
        orderDetails.getChildren().add(totalLabel);

        Label dateLabel = new Label("Order Date: " + orderView.getDate());
        orderDetails.getChildren().add(dateLabel);

        Scene orderDetailsScene = new Scene(orderDetails, 400, 300);
        Stage orderStage = new Stage();
        orderStage.setScene(orderDetailsScene);
        orderStage.setTitle("Order Details");
        orderStage.show();
    }

    public static boolean isValidPassword(String password) {
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[@#$%&*].*");
        boolean hasUpperCase = password.matches(".*[A-Z].*");
        return hasDigit && hasSpecialChar && hasUpperCase;
    }

    public static String generateID() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public static String generateAuthCode() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%04d", number);
    }
}
