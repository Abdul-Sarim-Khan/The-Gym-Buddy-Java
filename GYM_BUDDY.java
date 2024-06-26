package application;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/*Username: admin
Password: admin1*/

public class GYM_BUDDY extends Application {
	private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Login");

        showLoginForm();
    }

    private void showLoginForm() {
        // Create the heading
        Label heading = new Label("WELCOME TO GYM BUDDY");
        heading.setFont(new Font("Arial", 24));
        heading.setTextFill(Color.RED);
        heading.setStyle("-fx-text-fill: red; -fx-font-size: 24px; -fx-font-weight: bold; -fx-text-alignment: center;");
        heading.setAlignment(Pos.CENTER);

        // Create the grid pane for login
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setAlignment(Pos.CENTER); // Center the grid pane

        // Create the login components
        Label userLabel = new Label("Username:");
        userLabel.setTextFill(Color.WHITE);
        TextField userTextField = new TextField();
        userTextField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        Label pwLabel = new Label("Password:");
        pwLabel.setTextFill(Color.WHITE);
        PasswordField pwField = new PasswordField();
        pwField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        Hyperlink signUpLink = new Hyperlink("New user?");
        signUpLink.setStyle("-fx-text-fill: red;");

        // Add components to the login grid
        loginGrid.add(userLabel, 0, 0);
        loginGrid.add(userTextField, 1, 0);
        loginGrid.add(pwLabel, 0, 1);
        loginGrid.add(pwField, 1, 1);
        loginGrid.add(loginButton, 1, 2);
        loginGrid.add(signUpLink, 1, 3);

        // Center the components within their cells
        GridPane.setHalignment(userLabel, HPos.CENTER);
        GridPane.setHalignment(userTextField, HPos.CENTER);
        GridPane.setHalignment(pwLabel, HPos.CENTER);
        GridPane.setHalignment(pwField, HPos.CENTER);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setHalignment(signUpLink, HPos.CENTER);

        // Create a VBox to center the grid and add the heading at the top
        VBox vbox = new VBox(20); // 20 is the spacing between elements
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(heading, loginGrid);
        vbox.setStyle("-fx-background-color: black;");

        // Set up the scene and stage
        Scene loginScene = new Scene(vbox, 600, 500);
        primaryStage.setScene(loginScene);
        primaryStage.show();

        // Handle the sign-up link action
        signUpLink.setOnAction(e -> showRegistrationForm());

        // Handle the login action
        loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwField.getText();

            if (authenticate(username, password)) {
                showDashboard();
                showBMIandCalorie();
                showLeaderboard();
                showCommentBox();
            } else {
                System.out.println("Login failed.");
            }
        });
    }

    private void showRegistrationForm() {
        // Create the heading
        Label heading = new Label("REGISTER");
        heading.setFont(new Font("Arial", 24));
        heading.setTextFill(Color.RED);
        heading.setStyle("-fx-text-fill: red; -fx-font-size: 24px; -fx-font-weight: bold; -fx-text-alignment: center;");
        heading.setAlignment(Pos.CENTER);

        // Create the grid pane for registration
        GridPane regGrid = new GridPane();
        regGrid.setHgap(10);
        regGrid.setVgap(10);
        regGrid.setAlignment(Pos.CENTER); // Center the grid pane

        // Create the registration components
        Label firstNameLabel = new Label("First Name:");
        firstNameLabel.setTextFill(Color.WHITE);
        TextField firstNameField = new TextField();
        firstNameField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        Label lastNameLabel = new Label("Last Name:");
        lastNameLabel.setTextFill(Color.WHITE);
        TextField lastNameField = new TextField();
        lastNameField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        Label emailLabel = new Label("Email:");
        emailLabel.setTextFill(Color.WHITE);
        TextField emailField = new TextField();
        emailField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        Label dobLabel = new Label("Date of Birth:");
        dobLabel.setTextFill(Color.WHITE);
        TextField dobField = new TextField();
        dobField.setStyle("-fx-background-color: white; -fx-text-fill: black;");
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        Hyperlink oldUserLink = new Hyperlink("Old user?");
        oldUserLink.setStyle("-fx-text-fill: red;");

        // Add components to the registration grid
        regGrid.add(firstNameLabel, 0, 0);
        regGrid.add(firstNameField, 1, 0);
        regGrid.add(lastNameLabel, 0, 1);
        regGrid.add(lastNameField, 1, 1);
        regGrid.add(emailLabel, 0, 2);
        regGrid.add(emailField, 1, 2);
        regGrid.add(dobLabel, 0, 3);
        regGrid.add(dobField, 1, 3);
        regGrid.add(registerButton, 1, 4);
        regGrid.add(oldUserLink, 1, 5);

        // Center the components within their cells
        GridPane.setHalignment(firstNameLabel, HPos.CENTER);
        GridPane.setHalignment(firstNameField, HPos.CENTER);
        GridPane.setHalignment(lastNameLabel, HPos.CENTER);
        GridPane.setHalignment(lastNameField, HPos.CENTER);
        GridPane.setHalignment(emailLabel, HPos.CENTER);
        GridPane.setHalignment(emailField, HPos.CENTER);
        GridPane.setHalignment(dobLabel, HPos.CENTER);
        GridPane.setHalignment(dobField, HPos.CENTER);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setHalignment(oldUserLink, HPos.CENTER);

        // Create a VBox to center the grid and add the heading at the top
        VBox vbox = new VBox(20); // 20 is the spacing between elements
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(heading, regGrid);
        vbox.setStyle("-fx-background-color: black;");

        // Set up the scene and stage
        Scene regScene = new Scene(vbox, 600, 500);
        primaryStage.setScene(regScene);
        primaryStage.show();

        // Handle the register button action
        registerButton.setOnAction(e -> {
            // Registration logic goes here (e.g., saving the user's data)
            // For now, we'll just print the entered data to the console
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String dob = dobField.getText();

            System.out.println("Registered successfully with the following details:");
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Email: " + email);
            System.out.println("Date of Birth: " + dob);

            // After registration, show the login form again
            showLoginForm();
        });

        // Handle the old user link action
        oldUserLink.setOnAction(e -> showLoginForm());
    }

    private boolean authenticate(String username, String password) {
        // This is a simple example, in a real application you should check the credentials against a database or another secure source
        return "admin".equals(username) && "admin1".equals(password);
    }

    private void showDashboard() {
        try {
            new Dashboard().start(new Stage());
            primaryStage.close(); // Close the login stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showBMIandCalorie() {
        try {
            new BMIandCalorie().start(new Stage());
            primaryStage.close(); // Close the login stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showLeaderboard() {
        try {
            new Leaderboard().start(new Stage());
            primaryStage.close(); // Close the login stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void showCommentBox() {
        try {
            new CommentBoxApp().start(new Stage());
            primaryStage.close(); // Close the login stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
}