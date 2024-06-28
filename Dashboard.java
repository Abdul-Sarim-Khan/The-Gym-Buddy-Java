package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static application.ScreenShiftUtils.*;

public class Dashboard {

    // Define static fields for the scene and stage
    public static Scene dashboardscene;
    public static Stage DashboardStage;

    public void start(Stage stage) {
        DashboardStage = stage;

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

        Button BMIButton = createSidebarButton("BMI Calculator");
        Button CalorieButton = createSidebarButton("Calorie Burn Calculator");
        Button createAccountButton = createSidebarButton("Create User Account");
        Button workoutPlanButton = createSidebarButton("Workout Plan Suggestor");
        Button challengesButton = createSidebarButton("Fitness Challenges");
        Button leaderboardButton = createSidebarButton("Leaderboard");
        Button logoutButton = createSidebarButton("Logout");

        // Button Actions
        BMIButton.setOnAction(event -> {
            showBMIandCalorie(stage); // Pass the current stage to close it
        });

        CalorieButton.setOnAction(event -> {
            showBMIandCalorie(stage); // Pass the current stage to close it
        });

        createAccountButton.setOnAction(event -> {
            showRegistration(stage); // Pass the current stage to close it
        });

        workoutPlanButton.setOnAction(event -> {
            showWorkoutPlanSuggestor(stage); // Pass the current stage to close it
        });

        challengesButton.setOnAction(event -> {
        	showFitnessChallenge(stage); // Implement logic for challenges button action
        });

        leaderboardButton.setOnAction(event -> {
            showLeaderboard(stage); // Pass the current stage to close it
        });

        logoutButton.setOnAction(event -> {
            // Implement logout logic here
            stage.close(); // Close the current stage (assuming logout clears session)
        });

        // Button hover effects
        applyButtonHoverEffect(BMIButton);
        applyButtonHoverEffect(CalorieButton);
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

        sidebar.getChildren().addAll(gymBuddyLabel, BMIButton, CalorieButton, createAccountButton, workoutPlanButton, challengesButton, leaderboardButton);
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
        
       
        int points = FitnessChallenge.POINTS;
        String point = String.valueOf(points); // Convert int to String
        Label pointsLabel = new Label(point);
        
        pointsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        pointsContainer.getChildren().addAll(pointsLabel);

       
        	double Bami = BMIandCalorie.b ;
        	String bmiAsString = String.format("%.2f", Bami);
        	
        	
        Label bmiLabel = new Label(bmiAsString);
        bmiLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        
        bmiContainer.getChildren().addAll(bmiLabel);

        double wei = BMIandCalorie.w;
        String weightAsString = String.format("%.2f", wei);
        Label weightLabel = new Label(weightAsString);
        
        
        weightLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        weightContainer.getChildren().addAll(weightLabel);

        buttonRow.getChildren().addAll(leaderBoardContainer, pointsContainer, bmiContainer, weightContainer);

        Label weeklyProgressReport = new Label("Monthly Progress Report");
        weeklyProgressReport.setFont(new Font("Arial", 28));
        weeklyProgressReport.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 0 180px;");

        HBox reportBox = new HBox();
        reportBox.getChildren().addAll(weeklyProgressReport);
        reportBox.setPadding(new Insets(10));
        reportBox.setStyle("-fx-alignment: center-right;");

        LineChart<String, Number> weightChart = createWeightChart();

        VBox chartContainer = new VBox(20, reportBox, weightChart);
        chartContainer.setPadding(new Insets(20));
        chartContainer.setStyle("-fx-background-color: black;");

        mainContent.getChildren().addAll(buttonRow, chartContainer);
        mainContent.setStyle("-fx-background-color: black; -fx-padding: 20;");

        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(mainContent);

        dashboardscene = new Scene(root, 1030, 685);
        DashboardStage.setTitle("Gym Buddy Dashboard");
        DashboardStage.setScene(dashboardscene);
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

    private LineChart<String, Number> createWeightChart() {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Date");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Weight");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Weight Progress");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Weight Data");

        // Add data points
        series.getData().add(new XYChart.Data<>("4/16/2024", 30));
        series.getData().add(new XYChart.Data<>("4/23/2024", 70));
        series.getData().add(new XYChart.Data<>("4/30/2024", 50));
        series.getData().add(new XYChart.Data<>("5/7/2024", 80));
        series.getData().add(new XYChart.Data<>("5/14/2024", 60));
        series.getData().add(new XYChart.Data<>("5/21/2024", 55));
        series.getData().add(new XYChart.Data<>("5/28/2024", 65));
        series.getData().add(new XYChart.Data<>("6/4/2024", 85));
        series.getData().add(new XYChart.Data<>("6/11/2024", 100));
        series.getData().add(new XYChart.Data<>("6/18/2024", 90));
        series.getData().add(new XYChart.Data<>("6/25/2024", 75));

        lineChart.getData().add(series);

        // Apply CSS styling
        lineChart.setStyle("-fx-text-fill: #00C958;");
        xAxis.setStyle("-fx-text-fill: #00C958;");
        yAxis.setStyle("-fx-text-fill: #00C958;");

        // Set line color to green
        series.getNode().setStyle("-fx-stroke: green;");

        // Set chart title text color and font weight
        lineChart.lookup(".chart-title").setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");

        // Set axis labels to white with increased font size and bold weight
        xAxis.lookup(".axis-label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        yAxis.lookup(".axis-label").setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

        // Set tick label color to white using inline CSS
        lineChart.applyCss();  // Ensure CSS is applied
        xAxis.lookupAll(".axis-tick-mark-label").forEach(label -> ((Text)label).setStyle("-fx-fill: white;"));
        yAxis.lookupAll(".axis-tick-mark-label").forEach(label -> ((Text)label).setStyle("-fx-fill: white;"));

        return lineChart;
    }
}
