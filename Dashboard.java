package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Dashboard {

    public void start(Stage DashboardStage) {
    	// Top bar with the dashboard label and clock
        Label dashboardLabel = new Label("Dashboard | Overview");
        dashboardLabel.setFont(new Font("Arial", 30));
        dashboardLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 15px 40px 0 0");

        Label clock = new Label();
        clock.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px 0 0 450px;");
        clock.setFont(new Font("Arial", 30));

        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(10));
        topBar.setAlignment(Pos.CENTER_LEFT); // Aligning dashboardLabel to the left
        HBox.setHgrow(clock, Priority.ALWAYS); // Allowing clock to grow to fill space

        topBar.getChildren().addAll(dashboardLabel, new Label(""), clock); // Using a placeholder to push clock to right
        topBar.setStyle("-fx-background-color: #333333;");

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalTime currentTime = LocalTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
            clock.setText(currentTime.format(formatter));
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(10));
        Label gymBuddyLabel = new Label("GYM BUDDY");
        gymBuddyLabel.setFont(new Font("Arial", 28));
        gymBuddyLabel.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold; -fx-padding: 20px ;");

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

        VBox logoutContainer = new VBox();
        logoutContainer.getChildren().add(logoutButton);
        logoutContainer.setPadding(new Insets(170, 0, 0, 0));
        VBox.setVgrow(logoutContainer, Priority.ALWAYS);

        sidebar.getChildren().addAll(gymBuddyLabel, homeButton, createAccountButton, workoutPlanButton, challengesButton, leaderboardButton);
        sidebar.getChildren().add(logoutContainer);
        sidebar.setStyle("-fx-background-color: #333333; -fx-padding: 10;");

        VBox mainContent = new VBox(20);
        mainContent.setPadding(new Insets(20));

        HBox buttonRow = new HBox(20);
        VBox leaderBoardContainer = createMainContainer("Leaderboard", "black", 30);
        VBox pointsContainer = createMainContainer("Points", "black", 30);
        VBox bmiContainer = createMainContainer("BMI", "black", 30);
        VBox weightContainer = createMainContainer("Weight", "black", 30);

        Label firstPlaceLabel = new Label("1st");
        firstPlaceLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        leaderBoardContainer.getChildren().addAll(firstPlaceLabel);

        Label pointsLabel = new Label("98");
        pointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        pointsContainer.getChildren().addAll(pointsLabel);

        Label bmiLabel = new Label("28.2");
        bmiLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        bmiContainer.getChildren().addAll(bmiLabel);

        Label weightLabel = new Label("80 Kgs");
        weightLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        weightContainer.getChildren().addAll(weightLabel);

        buttonRow.getChildren().addAll(leaderBoardContainer, pointsContainer, bmiContainer, weightContainer);

        Label weeklyProgressReport = new Label("Weekly Progress Report");
        weeklyProgressReport.setFont(new Font("Arial", 28));
        weeklyProgressReport.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 0 200px;");

        HBox reportBox = new HBox();
        reportBox.getChildren().addAll(weeklyProgressReport);
        reportBox.setPadding(new Insets(10));
        reportBox.setStyle("-fx-alignment: center-right;");

        mainContent.getChildren().addAll(buttonRow, reportBox);
        mainContent.setStyle("-fx-background-color: black; -fx-padding: 20;");

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 1020, 660);
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

    private VBox createMainContainer(String text, String textColor, int fontSize) {
        VBox container = new VBox();
        container.setStyle("-fx-background-color: #00C958; -fx-text-fill: " + textColor + "; -fx-font-size: " + fontSize + "px; -fx-font-weight: bold;");
        container.setMinSize(150, 100);
        
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: " + textColor + "; -fx-font-size: 20px; -fx-font-weight: bold;");
        container.getChildren().add(label);
        
        container.setAlignment(Pos.CENTER);
        return container;
    }
 
    private void applyButtonHoverEffect(Button button) {
    	button.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
    }

    private void applyButtonHoverEffect(Button button, String baseColor, String hoverColor) {
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: " + hoverColor + "; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: " + baseColor + "; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
    }
}
