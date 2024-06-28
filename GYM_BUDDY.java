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
import static application.ScreenShiftUtils.*;


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
        primaryStage.setTitle("THE GYM BUDDY");

        showLoginForm();
    }

    private void showLoginForm() {
        // Create the heading
        Label heading = new Label("WELCOME TO GYM BUDDY");
        heading.setFont(new Font("Arial", 24));
       
        heading.setStyle("-fx-text-fill: #00C958; -fx-font-size: 24px; -fx-font-weight: bold;");
        heading.setAlignment(Pos.CENTER);

        // Create the grid pane for login
        GridPane loginGrid = new GridPane();
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setAlignment(Pos.CENTER); // Center the grid pane

        // Create the login components
        Label userLabel = new Label("Username:");
        userLabel.setTextFill(Color.WHITE);
        userLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        TextField userTextField = new TextField();
        userTextField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");
        
        Label pwLabel = new Label("Password:");
        pwLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        
        
        PasswordField pwField = new PasswordField();
        pwField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");
        Button loginButton = new Button("Login");

     // Set initial style
     loginButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

     // Mouse entered style
     loginButton.setOnMouseEntered(e -> {
         loginButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 14px; -fx-font-weight: bold;");
     });

     // Mouse exited style
     loginButton.setOnMouseExited(e -> {
         loginButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
     });
        
        Hyperlink signUpLink = new Hyperlink("New user?");
        signUpLink.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");
        
        signUpLink.setOnMouseEntered(e -> {
        	signUpLink.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;");
            signUpLink.getScene().setCursor(javafx.scene.Cursor.HAND);
        });
        signUpLink.setOnMouseExited(e -> {
        	signUpLink.setStyle("-fx-text-fill: #00C958; -fx-font-size: 12px; -fx-font-weight: bold;");
        	signUpLink.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
        });
        
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
            	showDashboard(primaryStage);
            } else {
                System.out.println("Login failed.");
            }
        });
    }

    	 public void showRegistrationForm() {
    	        // Create the heading
    	        Label heading = new Label("REGISTER");
    	        heading.setFont(new Font("Arial", 24));
    	        heading.setStyle("-fx-text-fill: #00C958; -fx-font-size: 24px; -fx-font-weight: bold;");
    	        heading.setAlignment(Pos.CENTER);

    	        // Create the grid pane for registration
    	        GridPane regGrid = new GridPane();
    	        regGrid.setHgap(10);
    	        regGrid.setVgap(10);
    	        regGrid.setAlignment(Pos.CENTER); // Center the grid pane

    	        // Create the registration components
    	        Label usernameLabel = new Label("Username:");
    	        usernameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

    	        TextField usernameField = new TextField();
    	        usernameField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

    	        Label pwLabel = new Label("Password:");
    	        pwLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

    	        PasswordField pwField = new PasswordField();
    	        pwField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

    	        Label confirmPwLabel = new Label("Confirm Password:");
    	        confirmPwLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

    	        PasswordField confirmPwField = new PasswordField();
    	        confirmPwField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

    	        Label emailLabel = new Label("Email:");
    	        emailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

    	        TextField emailField = new TextField();
    	        emailField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

    	        Button registerButton = new Button("Register");

    	        // Set initial style
    	        registerButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

    	        // Mouse entered style
    	        registerButton.setOnMouseEntered(e -> {
    	            registerButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 14px; -fx-font-weight: bold;");
    	        });

    	        // Mouse exited style
    	        registerButton.setOnMouseExited(e -> {
    	            registerButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
    	        });

    	        Hyperlink oldUserLink = new Hyperlink("Old user?");
    	        oldUserLink.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");

    	        oldUserLink.setOnMouseEntered(e -> {
    	            oldUserLink.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;");
    	            oldUserLink.getScene().setCursor(javafx.scene.Cursor.HAND);
    	        });
    	        oldUserLink.setOnMouseExited(e -> {
    	            oldUserLink.setStyle("-fx-text-fill: #00C958; -fx-font-size: 12px; -fx-font-weight: bold;");
    	            oldUserLink.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
    	        });

        // Add components to the registration grid
        regGrid.add(usernameLabel, 0, 0);
        regGrid.add(usernameField, 1, 0);
        regGrid.add(pwLabel, 0, 1);
        regGrid.add(pwField, 1, 1);
        regGrid.add(confirmPwLabel, 0, 2);
        regGrid.add(confirmPwField, 1, 2);
        regGrid.add(emailLabel, 0, 3);
        regGrid.add(emailField, 1, 3);
        regGrid.add(registerButton, 1, 4);
        regGrid.add(oldUserLink, 1, 5);

        // Center the components within their cells
        GridPane.setHalignment(usernameLabel, HPos.CENTER);
        GridPane.setHalignment(usernameField, HPos.CENTER);
        GridPane.setHalignment(pwLabel, HPos.CENTER);
        GridPane.setHalignment(pwField, HPos.CENTER);
        GridPane.setHalignment(confirmPwLabel, HPos.CENTER);
        GridPane.setHalignment(confirmPwField, HPos.CENTER);
        GridPane.setHalignment(emailLabel, HPos.CENTER);
        GridPane.setHalignment(emailField, HPos.CENTER);
        GridPane.setHalignment(registerButton, HPos.CENTER);
        GridPane.setHalignment(oldUserLink, HPos.CENTER);

        // Create a VBox to center the grid and add the heading at the top
        VBox vbox = new VBox(20); // 20 is the spacing between elements
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(heading, regGrid);
        vbox.setStyle("-fx-background-color: black");

        // Set up the scene and stage
        Scene regScene = new Scene(vbox, 600, 500);
        primaryStage.setScene(regScene);
        primaryStage.show();

        // Handle the register button action
        registerButton.setOnAction(e -> {
            // Registration logic goes here (e.g., saving the user's data)
            // For now, we'll just print the entered data to the console
            String username = usernameField.getText();
            String password = pwField.getText();
            String confirmPassword = confirmPwField.getText();
            String email = emailField.getText();

            System.out.println("Registered successfully with the following details:");
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);
            System.out.println("Confirm Password: " + confirmPassword);
            System.out.println("Email: " + email);

            // After registration, show the login form again
            showLoginForm();
        });

        // Handle the old user link action
        oldUserLink.setOnAction(e -> showLoginForm());
    }

    	 
    	 public void showRegistrationForm(Stage primaryStage) {
    		 
    		 this.primaryStage = primaryStage;
    	        primaryStage.setTitle("THE GYM BUDDY");
    		 
  	        // Create the heading
  	        Label heading = new Label("REGISTER");
  	        heading.setFont(new Font("Arial", 24));
  	        heading.setStyle("-fx-text-fill: #00C958; -fx-font-size: 24px; -fx-font-weight: bold;");
  	        heading.setAlignment(Pos.CENTER);

  	        // Create the grid pane for registration
  	        GridPane regGrid = new GridPane();
  	        regGrid.setHgap(10);
  	        regGrid.setVgap(10);
  	        regGrid.setAlignment(Pos.CENTER); // Center the grid pane

  	        // Create the registration components
  	        Label usernameLabel = new Label("Username:");
  	        usernameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

  	        TextField usernameField = new TextField();
  	        usernameField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

  	        Label pwLabel = new Label("Password:");
  	        pwLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

  	        PasswordField pwField = new PasswordField();
  	        pwField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

  	        Label confirmPwLabel = new Label("Confirm Password:");
  	        confirmPwLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

  	        PasswordField confirmPwField = new PasswordField();
  	        confirmPwField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

  	        Label emailLabel = new Label("Email:");
  	        emailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

  	        TextField emailField = new TextField();
  	        emailField.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #333333; -fx-pref-height: 20px; -fx-border-color: #00C958; -fx-pref-width: 170px;");

  	        Button registerButton = new Button("Register");

  	        // Set initial style
  	        registerButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

  	        // Mouse entered style
  	        registerButton.setOnMouseEntered(e -> {
  	            registerButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 14px; -fx-font-weight: bold;");
  	        });

  	        // Mouse exited style
  	        registerButton.setOnMouseExited(e -> {
  	            registerButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
  	        });

  	        Hyperlink oldUserLink = new Hyperlink("Old user?");
  	        oldUserLink.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");

  	        oldUserLink.setOnMouseEntered(e -> {
  	            oldUserLink.setStyle("-fx-text-fill: white; -fx-font-size: 12px; -fx-font-weight: bold;");
  	            oldUserLink.getScene().setCursor(javafx.scene.Cursor.HAND);
  	        });
  	        oldUserLink.setOnMouseExited(e -> {
  	            oldUserLink.setStyle("-fx-text-fill: #00C958; -fx-font-size: 12px; -fx-font-weight: bold;");
  	            oldUserLink.getScene().setCursor(javafx.scene.Cursor.DEFAULT);
  	        });

   // Add components to the registration grid
   regGrid.add(usernameLabel, 0, 0);
   regGrid.add(usernameField, 1, 0);
   regGrid.add(pwLabel, 0, 1);
   regGrid.add(pwField, 1, 1);
   regGrid.add(confirmPwLabel, 0, 2);
   regGrid.add(confirmPwField, 1, 2);
   regGrid.add(emailLabel, 0, 3);
   regGrid.add(emailField, 1, 3);
   regGrid.add(registerButton, 1, 4);
   regGrid.add(oldUserLink, 1, 5);

   // Center the components within their cells
   GridPane.setHalignment(usernameLabel, HPos.CENTER);
   GridPane.setHalignment(usernameField, HPos.CENTER);
   GridPane.setHalignment(pwLabel, HPos.CENTER);
   GridPane.setHalignment(pwField, HPos.CENTER);
   GridPane.setHalignment(confirmPwLabel, HPos.CENTER);
   GridPane.setHalignment(confirmPwField, HPos.CENTER);
   GridPane.setHalignment(emailLabel, HPos.CENTER);
   GridPane.setHalignment(emailField, HPos.CENTER);
   GridPane.setHalignment(registerButton, HPos.CENTER);
   GridPane.setHalignment(oldUserLink, HPos.CENTER);

   // Create a VBox to center the grid and add the heading at the top
   VBox vbox = new VBox(20); // 20 is the spacing between elements
   vbox.setAlignment(Pos.CENTER);
   vbox.getChildren().addAll(heading, regGrid);
   vbox.setStyle("-fx-background-color: black");

   // Set up the scene and stage
   Scene regScene = new Scene(vbox, 600, 500);
   primaryStage.setScene(regScene);
   primaryStage.show();

   registerButton.setOnAction(e -> {
       // Registration logic goes here (e.g., saving the user's data)
       // For now, we'll just print the entered data to the console
       String username = usernameField.getText();
       String password = pwField.getText();
       String confirmPassword = confirmPwField.getText();
       String email = emailField.getText();

       System.out.println("Registered successfully with the following details:");
       System.out.println("Username: " + username);
       System.out.println("Password: " + password);
       System.out.println("Confirm Password: " + confirmPassword);
       System.out.println("Email: " + email);

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
  
}


/*public static String filePath = "Data.txt"

public static boolean writeToFile(String username, String password, String filePath){
    try{
        String text=username+","+password;
        BufferedWriter f_writer= new BufferedWriter(new FileWriter(filePath));
        f_writer.write(text);
        System.out.println("Data written to a file!");
    }catch(Exception e){
        System.out.println(e);
    }
}
        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = pwField.getText();
            String confirmPassword = confirmPwField.getText();
            String email = emailField.getText();
            writeToFile(username, password, filePath);
            System.out.println("Account Registered successfully");
            showLoginForm();
        });
        
public static boolean verifyUserLogin(String username, String password, String filePath, String delimeter){
    String currentLine;
    String data[];

    try{
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        while((currentLine = br.readLine())!=null){
            data = currentLine.split(delimeter);
            return (data[0].equals(username) &&  data[1].equals(password))?true:false;
        }
    }catch(Exception e){
        System.out.println(e);
    }
}
loginButton.setOnAction(e -> {
            String username = userTextField.getText();
            String password = pwField.getText();

            if (verifyUserLogin(username, password, filePath, ",")) {
                  showDashboard(primaryStage);
            } else {
                System.out.println("Login failed.");
            }
        });*/