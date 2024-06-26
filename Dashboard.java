package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Dashboard {

  
    public void start(Stage DashboardStage) {  

        // Top bar with the dashboard label and clock  
        Label dashboardLabel = new Label("Dashboard | Overview");  
        dashboardLabel.setFont(new Font("Arial", 24));  
        dashboardLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 0 20 10 20;");  
        HBox topBar = new HBox(10);  
        topBar.setPadding(new Insets(10));  
        Label clock = new Label();  
        clock.setTextFill(Color.WHITE);  
        clock.setFont(new Font("Arial", 24));  
        topBar.getChildren().addAll(dashboardLabel, clock);  
        topBar.setStyle("-fx-background-color: #333333; -fx-padding: 10 20; -fx-alignment: center-right;");  

        // Update the clock every second  
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {  
            LocalTime currentTime = LocalTime.now();  
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");  
            clock.setText(currentTime.format(formatter));  
        }));  
        timeline.setCycleCount(Timeline.INDEFINITE);  
        timeline.play();  

        // Sidebar with navigation buttons  
        VBox sidebar = new VBox(10);  
        sidebar.setPadding(new Insets(10));  
        Label gymBuddyLabel = new Label("GYM BUDDY");  
        gymBuddyLabel.setFont(new Font("Arial", 28));  
        gymBuddyLabel.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");  
        gymBuddyLabel.setEffect(new DropShadow(10, Color.BLACK));  
        sidebar.setEffect(new DropShadow(10, Color.BLACK));  

        Button homeButton = createSidebarButton("Home");  
        Button createAccountButton = createSidebarButton("Create User Account");  
        Button workoutPlanButton = createSidebarButton("Workout Plan Suggestor");  
        Button challengesButton = createSidebarButton("Fitness Challenges");  
        Button leaderboardButton = createSidebarButton("Leaderboard");  
        Button logoutButton = createSidebarButton("Logout");  

        // Button hover effects  
        applyButtonHoverEffect(homeButton);  
        applyButtonHoverEffect(createAccountButton);  
        applyButtonHoverEffect(workoutPlanButton);  
        applyButtonHoverEffect(challengesButton);  
        applyButtonHoverEffect(leaderboardButton);  
        applyButtonHoverEffect(logoutButton);  

        logoutButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        applyButtonHoverEffect(logoutButton, "red", "#FF6666");

        // VBox to contain the logout button and set padding
        VBox logoutContainer = new VBox();
        logoutContainer.getChildren().add(logoutButton);
        logoutContainer.setPadding(new Insets(170, 0, 0, 0));
        VBox.setVgrow(logoutContainer, Priority.ALWAYS);

        sidebar.getChildren().addAll(gymBuddyLabel, homeButton, createAccountButton, workoutPlanButton, challengesButton, leaderboardButton);
        sidebar.getChildren().add(logoutContainer);  
        sidebar.setStyle("-fx-background-color: #333333; -fx-padding: 10;");  

        // Main content area with action buttons and report label  
        VBox mainContent = new VBox(20);  
        mainContent.setPadding(new Insets(20));  

        HBox buttonRow = new HBox(20);  
        Button leaderBoardButton = createMainButton("Leaderboard");  
        Button pointsButton = createMainButton("Points");  
        Button bmiButton = createMainButton("BMI");  
        Button weightButton = createMainButton("Weight");  
        buttonRow.getChildren().addAll(leaderBoardButton, pointsButton, bmiButton, weightButton);  

        Label weeklyProgressReport = new Label("Weekly Progress Report");  
        weeklyProgressReport.setFont(new Font("Arial", 28));  
        weeklyProgressReport.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 0 20;");  
        weeklyProgressReport.setEffect(new DropShadow(10, Color.BLACK));  
        HBox reportBox = new HBox();  
        reportBox.getChildren().addAll(weeklyProgressReport);  
        reportBox.setPadding(new Insets(10));  
        reportBox.setStyle("-fx-background-color: #333333; -fx-alignment: center-right;");  

        mainContent.getChildren().addAll(buttonRow, reportBox);  
        mainContent.setStyle("-fx-background-color: #333333; -fx-padding: 20;");  

        // Combine all parts into the main layout  
        BorderPane root = new BorderPane();  
        root.setTop(topBar);  
        root.setLeft(sidebar);  
        root.setCenter(mainContent);  

        Scene scene = new Scene(root, 1020, 600);  
        DashboardStage.setTitle("Gym Buddy Dashboard");  
        DashboardStage.setScene(scene);  
        DashboardStage.show();  
    }  

    private Button createSidebarButton(String text) {  
        Button button = new Button(text);  
        button.setStyle("-fx-background-color: #666666; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");  
        button.setMaxWidth(Double.MAX_VALUE);  
        button.setMinHeight(40);  
        return button;  
    }  

    private Button createMainButton(String text) {  
        Button button = new Button(text);  
        button.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");  
        button.setMinSize(150, 100);  
        return button;  
    }  

    private void applyButtonHoverEffect(Button button) {  
        button.setOnMouseEntered(e -> button.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #00C958;"));  
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #666666; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));  
    }

    private void applyButtonHoverEffect(Button button, String baseColor, String hoverColor) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: " + hoverColor + "; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + baseColor + "; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
    }

  
}