package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Random;
import static application.ScreenShiftUtils.*;

public class FitnessChallenge {
    private Stage ChallengeStage;
    private Scene mainScene;

    public void start(Stage ChallengeStage) {
        this.ChallengeStage = ChallengeStage;

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

        homeButton.setOnAction(event -> {
            showDashboard(ChallengeStage); // Pass the current stage to close it
        });

        topBar.getChildren().add(homeButton);
        topBar.setStyle("-fx-padding: 20px;");

        // Create heading label
        Label headingLabel = new Label("Your Fitness Challenge For Today");
        headingLabel.setFont(new Font("Arial", 30));
        headingLabel.setStyle("-fx-text-fill: white;");

        // Create an array of challenges
        VBox[] challenges = new VBox[] {
            createChallenge("10-minute brisk walk - 5 POINTS"),
            createChallenge("20-minute yoga session - 10 POINTS"),
            createChallenge("15-minute bodyweight circuit (push-ups, squats, lunges) - 15 POINTS"),
            createChallenge("5-minute meditation - 5 POINTS"),
            createChallenge("30-minute bike ride - 15 POINTS"),
            createChallenge("20-minute jog - 15 POINTS"),
            createChallenge("10 push-ups - 5 POINTS"),
            createChallenge("15 sit-ups - 5 POINTS"),
            createChallenge("20 squats - 5 POINTS"),
            createChallenge("1-minute plank - 5 POINTS"),
            createChallenge("15-minute stretching session - 10 POINTS"),
            createChallenge("20 burpees - 10 POINTS"),
            createChallenge("30 jumping jacks - 5 POINTS"),
            createChallenge("10-minute dance session - 5 POINTS"),
            createChallenge("1-hour hike - 20 POINTS"),
            createChallenge("10 pull-ups - 10 POINTS"),
            createChallenge("10-minute jump rope - 10 POINTS"),
            createChallenge("20 lunges (each leg) - 10 POINTS"),
            createChallenge("50-meter sprint - 10 POINTS"),
            createChallenge("5 flights of stairs - 5 POINTS"),
            createChallenge("10 tricep dips - 5 POINTS"),
            createChallenge("15-minute swim - 15 POINTS"),
            createChallenge("10-minute mindfulness walk - 5 POINTS"),
            createChallenge("20-minute HIIT workout - 20 POINTS"),
            createChallenge("15-minute Pilates session - 15 POINTS"),
            createChallenge("10-minute core workout (crunches, leg raises, bicycles) - 10 POINTS"),
            createChallenge("20 calf raises - 5 POINTS"),
            createChallenge("30-second side planks (each side) - 5 POINTS"),
            createChallenge("10-minute stair climbing - 10 POINTS"),
            createChallenge("30-second mountain climbers - 5 POINTS"),
            createChallenge("10 minutes of foam rolling - 5 POINTS"),
            createChallenge("15-minute resistance band workout - 15 POINTS"),
            createChallenge("10 kettlebell swings - 5 POINTS"),
            createChallenge("5-minute breathing exercises - 5 POINTS"),
            createChallenge("15-minute shadow boxing - 10 POINTS"),
            createChallenge("1-mile walk/run - 10 POINTS"),
            createChallenge("30 squats with a jump - 10 POINTS"),
            createChallenge("10-minute tai chi session - 10 POINTS"),
            createChallenge("15-minute rowing session - 15 POINTS"),
            createChallenge("50 jumping jacks - 10 POINTS"),
            createChallenge("15-minute stair workout - 15 POINTS"),
            createChallenge("20-minute elliptical session - 20 POINTS"),
            createChallenge("5-minute balance exercises - 5 POINTS"),
            createChallenge("15-minute strength training (weights) - 15 POINTS"),
            createChallenge("10 minutes of boxing/kickboxing - 10 POINTS"),
            createChallenge("1-minute wall sit - 5 POINTS"),
            createChallenge("10 burpees with a push-up - 10 POINTS"),
            createChallenge("20 toe touches - 5 POINTS"),
            createChallenge("15-minute light jogging in place - 10 POINTS"),
            createChallenge("30-minute mixed cardio (bike, elliptical, rower) - 20 POINTS")
        };

        // Randomly select a challenge
        Random random = new Random();
        VBox selectedChallenge = challenges[random.nextInt(challenges.length)];

        // Create a gray box to contain the selected challenge
        VBox grayBox = new VBox(selectedChallenge);
        grayBox.setStyle("-fx-background-color: black; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-max-width: 900px");
        grayBox.setAlignment(Pos.CENTER);

        // Create "Done" button
        Button doneButton = new Button("DONE");
        doneButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");

        doneButton.setOnMouseEntered(e -> {
            doneButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 18px; -fx-font-weight: bold;");
        });

        doneButton.setOnMouseExited(e -> {
            doneButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        });

        doneButton.setOnAction(event -> {
            showDashboard(ChallengeStage); // Close the current stage and show the dashboard
        });

        grayBox.getChildren().add(doneButton);

        // Create a VBox to hold the top bar, heading, and the gray box
        VBox outerVBox = new VBox(20, topBar, headingLabel, grayBox);
        outerVBox.setAlignment(Pos.CENTER);
        outerVBox.setStyle("-fx-background-color: black;");

        // Create the main scene with the outer VBox layout
        mainScene = new Scene(outerVBox, 700, 400);

        // Set the title of the stage (window) and add the main scene to it
        ChallengeStage.setTitle("Fitness Challenge");
        ChallengeStage.setScene(mainScene);
        ChallengeStage.show();
    }

    private VBox createChallenge(String description) {
        // Create description label
        Label descriptionLabel = new Label(description);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setMaxWidth(400);  // Adjust the width as needed
        descriptionLabel.setPrefHeight(600); // Set the preferred height to 600 pixels
        descriptionLabel.setFont(new Font("Arial", 18));
        descriptionLabel.setStyle("-fx-text-fill: black; -fx-font-weight: bold;");

        // Create a green box for description
        VBox descriptionBox = new VBox(descriptionLabel);
        descriptionBox.setStyle("-fx-background-color: #00C958; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-padding: 20px;");
        descriptionBox.setAlignment(Pos.CENTER);  // Center the content inside the green box

        // Create VBox to hold description box and button
        VBox vbox = new VBox(descriptionBox);
        vbox.setStyle("-fx-background-color: #333333; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

}
