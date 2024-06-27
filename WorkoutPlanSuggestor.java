package application;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WorkoutPlanSuggestor{

    private Scene mainScene;

    public void start(Stage WorkoutStage) {
        // Create an HBox layout for the top bar
        HBox topBar = new HBox();
        topBar.setAlignment(Pos.TOP_LEFT);
        
        // Create home button
        Button homeButton = new Button("HOME");
        homeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        // Mouse entered style
        homeButton.setOnMouseEntered(e -> {
            homeButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 14px; -fx-font-weight: bold;");
        });

        // Mouse exited style
        homeButton.setOnMouseExited(e -> {
            homeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        });
        
        topBar.getChildren().add(homeButton);

        // Create heading label
        Label headingLabel = new Label("Choose Your Customized Workout Plan");
        headingLabel.setFont(new Font("Arial", 30));
        headingLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        // Create three plans with descriptions
        VBox plan1 = createPlan("Ideal for newbies or those returning to exercise. Focuses on building a foundation and improving overall fitness.", "BEGINNER PLAN");
        VBox plan2 = createPlan("For consistent exercisers who want to level up. Offers variety, intensity, and targeted muscle development.", "INTERMEDIATE PLAN");
        VBox plan3 = createPlan("Designed for seasoned fitness enthusiasts. Aims to build muscle, strength, and enhance athletic performance.", "ADVANCED PLAN");
        
        // Create HBox to hold the three plans
        HBox hbox = new HBox(20, plan1, plan2, plan3);
        hbox.setAlignment(Pos.CENTER);

        // Create a gray box to contain the plans
        VBox grayBox = new VBox(hbox);
        grayBox.setStyle("-fx-background-color: black; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-pref-height: 500px; -fx-max-width: 900px");
        grayBox.setAlignment(Pos.CENTER);

        // Create a VBox to hold the top bar, heading, and the gray box
        VBox outerVBox = new VBox(20, topBar, headingLabel, grayBox);
        outerVBox.setAlignment(Pos.TOP_CENTER);

        // Create the main scene with the outer VBox layout
        mainScene = new Scene(outerVBox, 1020, 600);
        
        outerVBox.setStyle("-fx-background-color: black;");
        
        // Event handlers for buttons to open new pages
        homeButton.setOnAction(event -> WorkoutStage.setScene(mainScene));

        // Set the title of the stage (window) and add the main scene to it
        WorkoutStage.setTitle("Workout Plan Suggestor");
        WorkoutStage.setScene(mainScene);
        WorkoutStage.show();
    }

    private VBox createPlan(String description, String buttonText) {
        // Create description label
        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(200);
        descriptionLabel.setFont(new Font("Arial", 18));
        descriptionLabel.setStyle("-fx-text-fill: white; -fx-padding: 10px; -fx-font-weight: bold;");

        // Create a green box for description
        VBox descriptionBox = new VBox(descriptionLabel);
        descriptionBox.setStyle("-fx-background-color: #00C958; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 20px;");

        // Create button
        Button planButton = new Button(buttonText);
        planButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Mouse entered style
        planButton.setOnMouseEntered(e -> {
            planButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 18px; -fx-font-weight: bold;");
        });

        // Mouse exited style
        planButton.setOnMouseExited(e -> {
            planButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        });

        planButton.setOnAction(event -> openNewPage((Stage) planButton.getScene().getWindow(), buttonText + " Selected"));

        // Create VBox to hold description box and button
        VBox vbox = new VBox(20, descriptionBox, planButton);
        vbox.setStyle("-fx-background-color: #333333; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-pref-height: 400px; -fx-min-width: 250px");
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    private void openNewPage(Stage primaryStage, String message) {
        // Create a label with the given message
        Label label = new Label(message);
        label.setStyle("-fx-text-fill: black; -fx-font-size: 18px; -fx-font-weight: bold;");

        // Create a button to go back to the main scene
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: black; -fx-font-size: 18px; -fx-font-weight: bold;");
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));

        // Create a layout for the new page
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(label, backButton);

        // Create a new scene with the layout
        Scene newScene = new Scene(layout, 350, 300);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
    }

   
}
