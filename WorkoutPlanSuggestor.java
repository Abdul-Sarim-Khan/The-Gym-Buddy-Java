package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
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
        headingLabel.setFont(new Font("Arial", 24));
        headingLabel.setStyle("-fx-text-fill: white;");

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

        // Set action for each plan button
        switch (buttonText) {
            case "BEGINNER PLAN":
                planButton.setOnAction(event -> openBeginnerPlanPage((Stage) planButton.getScene().getWindow()));
                break;
            case "INTERMEDIATE PLAN":
                planButton.setOnAction(event -> openIntermediatePlanPage((Stage) planButton.getScene().getWindow()));
                break;
            case "ADVANCED PLAN":
                planButton.setOnAction(event -> openAdvancedPlanPage((Stage) planButton.getScene().getWindow()));
                break;
        }

        // Create VBox to hold description box and button
        VBox vbox = new VBox(20, descriptionBox, planButton);
        vbox.setStyle("-fx-background-color: #333333; -fx-padding: 20px; -fx-border-radius: 10px; -fx-background-radius: 10px; -fx-pref-height: 400px; -fx-min-width: 250px");
        vbox.setAlignment(Pos.CENTER);

        return vbox;
    }

    private void openBeginnerPlanPage(Stage primaryStage) {
        // Create the main container for the new page
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setStyle("-fx-background-color: #333333;");

        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mainContainer);

        // Create the header section
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #000000; -fx-padding: 20px;");

        Label headerTitle = new Label("BEGINNER WORKOUT PLAN");
        headerTitle.setFont(new Font("Arial", 36));
        headerTitle.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");

        Label subTitle = new Label("FOR 2024");
        subTitle.setFont(new Font("Arial", 18));
        subTitle.setStyle("-fx-text-fill: #00C958;");

        header.getChildren().addAll(headerTitle, subTitle);

        // Create the weekday schedule header
        Label weekdayScheduleLabel = new Label("WEEKLY SCHEDULE");
        weekdayScheduleLabel.setFont(new Font("Arial", 24));
        weekdayScheduleLabel.setStyle("-fx-background-color: #000000; -fx-text-fill: #00C958; -fx-padding: 10px; -fx-alignment: center;");
        weekdayScheduleLabel.setMaxWidth(Double.MAX_VALUE);
        weekdayScheduleLabel.setAlignment(Pos.CENTER);

        // Create the schedule grid
        GridPane scheduleGrid = new GridPane();
        scheduleGrid.setAlignment(Pos.CENTER);
        scheduleGrid.setHgap(10);
        scheduleGrid.setVgap(10);
        scheduleGrid.setStyle("-fx-padding: 20px;");

        // Set column constraints for three columns
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33.33); // 33.33% width for each column

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33.33);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33.33);

        scheduleGrid.getColumnConstraints().addAll(col1, col2, col3);

        // All day Tasks.....
        String[] days = {"MONDAY - Full-Body Workout", "TUESDAY - Active Rest Day", "WEDNESDAY - Full-Body Workout", "THURSDAY - Active Rest Day", "FRIDAY - Full-Body Workout", "SATURDAY - Rest Day", "SUNDAY - Rest Day"};
        String[] Montasks = {"Warmup: 5–10 minutes of light cardio (e.g., marching in place)", "Core: Standing march", "Chest: Stability ball dumbbell press", "Back: Seated cable row", "Shoulders: Seated stability ball military press", "Legs: Ball squat", "Triceps: Single-leg triceps pushdown", "Biceps: Single-leg dumbbell curl", "Cooldown: 5–10 minutes of stretching"};
        String[] Tuetasks = {"Take it easy! Rest or do light activities like walking or yoga"};
        String[] Wedtasks = {"Warmup: 5–10 minutes", "Core: Plank", "Back: Stability ball dumbbell row", "Chest: Push-up on stability ball", "Shoulders: Single-leg dumbbell scaption", "Legs: Walking lunge", "Biceps: Single-leg biceps cable curl", "Triceps: Stability ball triceps extension", "Cooldown: 5–10 minutes of stretching"};
        String[] Thurtasks = {"Rest or engage in light movement"};
        String[] Fritasks = {"Warmup: 5–10 minutes", "Core: Floor prone cobra","Legs: Alternating step-up","Chest: Stability ball dumbbell fly","Shoulders: Shoulder shrug with dumbbells or resistance bands", "Cooldown: 5–10 minutes of stretching"};
        String[] Sattasks = {"Resting is an essential part of any\nworkout routine. Two days of rest\ncan help your muscles recover and\nprevent overtraining. Use this time\nto relax, stretch,and recharge.\nRemember, consistency and\nbalance are key to achieving\nyour fitness goals! 💪🌟 "};
        String[] Suntasks = {"Resting is an essential part of any\nworkout routine. Two days of rest\ncan help your muscles recover and\nprevent overtraining. Use this time\nto relax, stretch,and recharge.\nRemember, consistency and\nbalance are key to achieving\nyour fitness goals! 💪🌟 "};

        // Add day labels and checklists to the grid
        for (int i = 0; i < days.length; i++) {
            Label dayLabel = new Label(days[i]);
            dayLabel.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setAlignment(Pos.CENTER);
            VBox dayColumn = new VBox(20, dayLabel);

            String[] tasks;
            switch(i) {
                case 0: tasks = Montasks; break;
                case 1: tasks = Tuetasks; break;
                case 2: tasks = Wedtasks; break;
                case 3: tasks = Thurtasks; break;
                case 4: tasks = Fritasks; break;
                case 5: tasks = Sattasks; break;
                case 6: tasks = Suntasks; break;
                default: tasks = new String[0]; break;
            }

            for (String task : tasks) {
                CheckBox taskCheckBox = new CheckBox(task);
              //  taskCheckBox.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 14px;");
                taskCheckBox.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
                taskCheckBox.setOnMouseEntered(e -> taskCheckBox.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
                taskCheckBox.setOnMouseExited(e -> taskCheckBox.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
                dayColumn.getChildren().add(taskCheckBox);
            }

            scheduleGrid.add(dayColumn, i % 3, i / 3); // Adjust to have 3 columns
        }

        // Create a button to go back to the main scene
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));
        
        // Assemble all parts in the main container
        mainContainer.getChildren().addAll(header, weekdayScheduleLabel, scheduleGrid, backButton);

        // Create a new scene with the scroll pane containing the main container
        Scene newScene = new Scene(scrollPane, 1020, 700);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
    }


    
    private void openIntermediatePlanPage(Stage primaryStage) {
        // Create the main container for the new page
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setStyle("-fx-background-color: #333333;");

        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mainContainer);

        // Create the header section
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #000000; -fx-padding: 20px;");

        Label headerTitle = new Label("INTERMEDIATE WORKOUT PLAN");
        headerTitle.setFont(new Font("Arial", 36));
        headerTitle.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");

        Label subTitle = new Label("FOR 2024");
        subTitle.setFont(new Font("Arial", 18));
        subTitle.setStyle("-fx-text-fill: #00C958;");

        header.getChildren().addAll(headerTitle, subTitle);

        // Create the weekday schedule header
        Label weekdayScheduleLabel = new Label("WEEKLY SCHEDULE");
        weekdayScheduleLabel.setFont(new Font("Arial", 24));
        weekdayScheduleLabel.setStyle("-fx-background-color: #000000; -fx-text-fill: #00C958; -fx-padding: 10px; -fx-alignment: center;");
        weekdayScheduleLabel.setMaxWidth(Double.MAX_VALUE);
        weekdayScheduleLabel.setAlignment(Pos.CENTER);

        // Create the schedule grid
        GridPane scheduleGrid = new GridPane();
        scheduleGrid.setAlignment(Pos.CENTER);
        scheduleGrid.setHgap(10);
        scheduleGrid.setVgap(10);
        scheduleGrid.setStyle("-fx-padding: 20px;");

        // Set column constraints for three columns
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33.33); // 33.33% width for each column

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33.33);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33.33);

        scheduleGrid.getColumnConstraints().addAll(col1, col2, col3);

        // All day Tasks.....
        String[] days = {"MONDAY - Full-Body Workout", "TUESDAY - Active Rest Day", "WEDNESDAY - Full-Body Workout", "THURSDAY - Active Rest Day", "FRIDAY - Full-Body Workout", "SATURDAY - Rest Day", "SUNDAY - Rest Day"};
        String[] Montasks = {"Warmup: 5–10 minutes of light cardio (e.g., jogging in place)", "Chest: Bench press (dumbbells or barbell)", "Back: Bent-over rows", "Shoulders: Overhead press", "Biceps: Bicep curls", "Triceps: Tricep dips", "Core: Planks", "Cooldown: 5–10 minutes of stretching"};
        String[] Tuetasks = {"Rest or engage in light activities like walking or yoga."};
        String[] Wedtasks = {"Warmup: 5–10 minutes", "Legs: Squats (weighted or bodyweight)", "Hamstrings: Romanian deadlifts", "Glutes: Hip thrusts", "Calves: Calf raises", "Core: Russian twists", "Cooldown: 5–10 minutes of stretching"};
        String[] Thurtasks = {"Rest or do light movement."};
        String[] Fritasks = {"Warmup: 5–10 minutes", "Cardio: Choose your favorite: running, cycling, or jumping jacks","Core: Bicycle crunches, leg raises","Cooldown: 5–10 minutes of stretching"};
        String[] Sattasks = {"Resting is an essential part of any\nworkout routine. Two days of rest\ncan help your muscles recover and\nprevent overtraining. Use this time\nto relax, stretch,and recharge.\nRemember, consistency and\nbalance are key to achieving\nyour fitness goals! 💪🌟 "};
        String[] Suntasks = {"Resting is an essential part of any\nworkout routine. Two days of rest\ncan help your muscles recover and\nprevent overtraining. Use this time\nto relax, stretch,and recharge.\nRemember, consistency and\nbalance are key to achieving\nyour fitness goals! 💪🌟 "};

        // Add day labels and checklists to the grid
        for (int i = 0; i < days.length; i++) {
            Label dayLabel = new Label(days[i]);
            dayLabel.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setAlignment(Pos.CENTER);
            VBox dayColumn = new VBox(20, dayLabel);

            String[] tasks;
            switch(i) {
                case 0: tasks = Montasks; break;
                case 1: tasks = Tuetasks; break;
                case 2: tasks = Wedtasks; break;
                case 3: tasks = Thurtasks; break;
                case 4: tasks = Fritasks; break;
                case 5: tasks = Sattasks; break;
                case 6: tasks = Suntasks; break;
                default: tasks = new String[0]; break;
            }

            for (String task : tasks) {
                CheckBox taskCheckBox = new CheckBox(task);
              //  taskCheckBox.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 14px;");
                taskCheckBox.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
                taskCheckBox.setOnMouseEntered(e -> taskCheckBox.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
                taskCheckBox.setOnMouseExited(e -> taskCheckBox.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
                dayColumn.getChildren().add(taskCheckBox);
            }

            scheduleGrid.add(dayColumn, i % 3, i / 3); // Adjust to have 3 columns
        }

        // Create a button to go back to the main scene
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));
        
        // Assemble all parts in the main container
        mainContainer.getChildren().addAll(header, weekdayScheduleLabel, scheduleGrid, backButton);

        // Create a new scene with the scroll pane containing the main container
        Scene newScene = new Scene(scrollPane, 1020, 700);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
    }
    
    private void openAdvancedPlanPage(Stage primaryStage) {
        // Create the main container for the new page
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setStyle("-fx-background-color: #333333;");

        // Create a ScrollPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(mainContainer);

        // Create the header section
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #000000; -fx-padding: 20px;");

        Label headerTitle = new Label("ADVANCED WORKOUT PLAN");
        headerTitle.setFont(new Font("Arial", 36));
        headerTitle.setStyle("-fx-text-fill: #00C958; -fx-font-weight: bold;");

        Label subTitle = new Label("FOR 2024");
        subTitle.setFont(new Font("Arial", 18));
        subTitle.setStyle("-fx-text-fill: #00C958;");

        header.getChildren().addAll(headerTitle, subTitle);

        // Create the weekday schedule header
        Label weekdayScheduleLabel = new Label("WEEKLY SCHEDULE");
        weekdayScheduleLabel.setFont(new Font("Arial", 24));
        weekdayScheduleLabel.setStyle("-fx-background-color: #000000; -fx-text-fill: #00C958; -fx-padding: 10px; -fx-alignment: center;");
        weekdayScheduleLabel.setMaxWidth(Double.MAX_VALUE);
        weekdayScheduleLabel.setAlignment(Pos.CENTER);

        // Create the schedule grid
        GridPane scheduleGrid = new GridPane();
        scheduleGrid.setAlignment(Pos.CENTER);
        scheduleGrid.setHgap(10);
        scheduleGrid.setVgap(10);
        scheduleGrid.setStyle("-fx-padding: 20px;");

        // Set column constraints for three columns
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(33.33); // 33.33% width for each column

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(33.33);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(33.33);

        scheduleGrid.getColumnConstraints().addAll(col1, col2, col3);

        // All day Tasks.....
        String[] days = {"MONDAY - Full-Body Workout", "TUESDAY - Active Rest Day", "WEDNESDAY - Full-Body Workout", "THURSDAY - Active Rest Day", "FRIDAY - Full-Body Workout", "SATURDAY - Rest Day", "SUNDAY - Rest Day"};
        String[] Montasks = {"Warmup: 5–10 minutes of light cardio (e.g., brisk walking or jogging)", "Core: Standing march (10 repetitions)","Chest: Stability ball dumbbell press (10 repetitions)", "Back: Seated cable row (10 repetitions)", "Shoulders: Seated stability ball military press (10 repetitions)", "Legs: Ball squat (10 repetitions)", "Triceps: Single-leg triceps pushdown (10 repetitions)",  "Biceps: Single-leg dumbbell curl (10 repetitions)", "Cooldown: 5–10 minutes of stretching"};
        String[] Tuetasks = {"Take it easy today! Engage in light activities like walking or gentle stretching."};
        String[] Wedtasks = {"Warmup: 5–10 minutes of light cardio (e.g., brisk walking or jogging)",  "Core: Plank (hold for 30 seconds)", "Back: Stability ball dumbbell row (10 repetitions)", "Chest: Push-up on stability ball (10 repetitions)", "Shoulders: Single-leg dumbbell scaption (10 repetitions)", "Legs: Walking lunge (10 repetitions per leg)", "Biceps: Single-leg biceps cable curl (10 repetitions)", "Triceps: Stability ball triceps extension (10 repetitions)",  "Cooldown: 5–10 minutes of stretching"};
        String[] Thurtasks = {"Another day of light activity. Listen to your body and rest as needed."};
        String[] Fritasks = {"Warmup: 5–10 minutes of light cardio (e.g., brisk walking or jogging)",  "Core: Floor prone cobra (10 repetitions)", "Legs: Alternating step-up (10 repetitions per leg)", "Chest: Stability ball dumbbell fly (10 repetitions)", "Shoulders: Shoulder shrug with dumbbells or resistance bands (10 repetitions)", "Cooldown: 5–10 minutes of stretching"};
        String[] Sattasks = {"Resting is an essential part of any\nworkout routine. Two days of rest\ncan help your muscles recover and\nprevent overtraining. Use this time\nto relax, stretch,and recharge.\nRemember, consistency and\nbalance are key to achieving\nyour fitness goals! 💪🌟 "};
        String[] Suntasks = {"Resting is an essential part of any\nworkout routine. Two days of rest\ncan help your muscles recover and\nprevent overtraining. Use this time\nto relax, stretch,and recharge.\nRemember, consistency and\nbalance are key to achieving\nyour fitness goals! 💪🌟 "};

        // Add day labels and checklists to the grid
        for (int i = 0; i < days.length; i++) {
            Label dayLabel = new Label(days[i]);
            dayLabel.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px;");
            dayLabel.setMaxWidth(Double.MAX_VALUE);
            dayLabel.setAlignment(Pos.CENTER);
            VBox dayColumn = new VBox(20, dayLabel);

            String[] tasks;
            switch(i) {
                case 0: tasks = Montasks; break;
                case 1: tasks = Tuetasks; break;
                case 2: tasks = Wedtasks; break;
                case 3: tasks = Thurtasks; break;
                case 4: tasks = Fritasks; break;
                case 5: tasks = Sattasks; break;
                case 6: tasks = Suntasks; break;
                default: tasks = new String[0]; break;
            }

            for (String task : tasks) {
                CheckBox taskCheckBox = new CheckBox(task);
              //  taskCheckBox.setStyle("-fx-text-fill: #FFFFFF; -fx-font-size: 14px;");
                taskCheckBox.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
                taskCheckBox.setOnMouseEntered(e -> taskCheckBox.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
                taskCheckBox.setOnMouseExited(e -> taskCheckBox.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
                dayColumn.getChildren().add(taskCheckBox);
            }

            scheduleGrid.add(dayColumn, i % 3, i / 3); // Adjust to have 3 columns
        }

        // Create a button to go back to the main scene
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));
        
        // Assemble all parts in the main container
        mainContainer.getChildren().addAll(header, weekdayScheduleLabel, scheduleGrid, backButton);

        // Create a new scene with the scroll pane containing the main container
        Scene newScene = new Scene(scrollPane, 1020, 700);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
    }

}
