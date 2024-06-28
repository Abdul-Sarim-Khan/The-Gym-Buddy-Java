package application;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static application.ScreenShiftUtils.*;

public class BMIandCalorie {
	private Stage primaryStage;
	
    private static final String CSS_FILE = "styles.css";

   
    public void start(Stage CalculationStage) {
    	 primaryStage = CalculationStage;
        GridPane gridPane = createGridPane();
        Scene scene = new Scene(gridPane, 700, 600);
        scene.setFill(Color.web("#000000")); // Set scene background color
        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());

        primaryStage.setTitle("BMI Calculator and Calorie Burn Estimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPrefWidth(300);


        gridPane.getColumnConstraints().addAll(labelColumn);

        addUIControls(gridPane);
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        gridPane.add(createLabel("Weight (kgs):"), 0, 0);
        gridPane.add(createTextField(), 1, 0);
        gridPane.add(createLabel("Height (cm):"), 0, 1);
        gridPane.add(createTextField(), 1, 1);
        gridPane.add(createLabel("Age:"), 0, 2);
        gridPane.add(createTextField(), 1, 2);
        gridPane.add(createLabel("Gender:"), 0, 3);
        gridPane.add(createGenderChoiceBox(), 1, 3);
        gridPane.add(createLabel("Health Condition:"), 0, 4);
        gridPane.add(createHealthConditionChoiceBox(), 1, 4);

        Button calculateButton = new Button("Calculate BMI and Calorie Burn");
        calculateButton.getStyleClass().add("calculate-button");
        gridPane.add(calculateButton, 0, 6, 2, 1);


        Label resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        gridPane.add(resultLabel, 0, 7, 2, 1);
        
        Label ConditionLabel = new Label();
        ConditionLabel.setPrefWidth(800); // Set preferred width to ensure full text is visible
        ConditionLabel.setWrapText(true); // Enable text wrapping for long advice
        gridPane.add(ConditionLabel, 0, 10, 2, 1);
        
        // Create a button to go back to the  Dashboard
        Button HomeButton = new Button("DASHBOARD");
        HomeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        HomeButton.setOnMouseEntered(e -> HomeButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
        HomeButton.setOnMouseExited(e -> HomeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        HomeButton.setOnAction(event -> {
            showDashboard(primaryStage); // Pass the current stage to close it
        });
        
        gridPane.add(HomeButton, 0, 15, 2, 1);
        ConditionLabel.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold");


       
        
        calculateButton.setOnAction(e -> calculateBMIAndCalories(gridPane, resultLabel, ConditionLabel));
    }

    private Label createLabel(String labelText) {
        Label label = new Label(labelText);
        label.getStyleClass().add("label");
        return label;
    }

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.getStyleClass().add("input-field");
        return textField;
    }

    private ChoiceBox<String> createGenderChoiceBox() {
        ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
        genderChoiceBox.getStyleClass().add("choice-box");
        genderChoiceBox.getItems().addAll("Male", "Female");
        return genderChoiceBox;
    }

    private ChoiceBox<String> createHealthConditionChoiceBox() {
        ChoiceBox<String> healthConditionChoiceBox = new ChoiceBox<>();
        healthConditionChoiceBox.getStyleClass().add("choice-box");
        healthConditionChoiceBox.getItems().addAll("No Health Condition", "Heart Disease", "High Blood Pressure", "Chronic Pain", "Back Or Joint Pain");
        return healthConditionChoiceBox;
    }

    private void calculateBMIAndCalories(GridPane gridPane, Label resultLabel, Label ConditionLabel) {
        TextField weightField = (TextField) gridPane.getChildren().get(1);
        TextField heightField = (TextField) gridPane.getChildren().get(3);
        TextField ageField = (TextField) gridPane.getChildren().get(5);
        ChoiceBox<String> genderChoiceBox = (ChoiceBox<String>) gridPane.getChildren().get(7);
        ChoiceBox<String> healthConditionChoiceBox = (ChoiceBox<String>) gridPane.getChildren().get(9);

        try {
            double weightKgs = Double.parseDouble(weightField.getText());
            double heightCm = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String gender = genderChoiceBox.getValue();
            String healthCondition = healthConditionChoiceBox.getValue();

            if (weightKgs <= 0 || heightCm <= 0 || age <= 0 || gender == null || healthCondition == null) {
                throw new NumberFormatException();
            }

            double heightMeters = heightCm / 100.0;
            double bmi = weightKgs / (heightMeters * heightMeters);
            String bmiText = String.format("%.2f", bmi);
            String bmiClassification = classifyBMI(bmi);

            double estimatedCaloriesBurned = calculateCaloriesBurned(weightKgs, heightCm, String.valueOf(age), gender);
            String caloriesText = String.format("%.2f", estimatedCaloriesBurned);

            resultLabel.setText("BMI: " + bmiText + " (" + bmiClassification + ")\nEstimated Calorie Burn: " + caloriesText + " kcal/day");
            resultLabel.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold");

            String healthAdvice = getHealthAdvice(healthCondition);
            ConditionLabel.setText(healthAdvice);
            ConditionLabel.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold");

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter valid numbers and select all fields.");
            resultLabel.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 16px; -fx-font-weight: bold");
        }
    }

    private String classifyBMI(double bmi) {
        if (bmi < 16) {
            return "Severe Thinness";
        } else if (bmi >= 16 && bmi < 17) {
            return "Moderate Thinness";
        } else if (bmi >= 17 && bmi < 18.5) {
            return "Mild Thinness";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else if (bmi >= 30 && bmi < 35) {
            return "Obese Class I";
        } else if (bmi >= 35 && bmi < 40) {
            return "Obese Class II";
        } else {
            return "Obese Class III";
        }
    }

    private double calculateCaloriesBurned(double weightKgs, double heightCm, String age, String gender) {
        final double BASE_CALORIES_PER_MINUTE = 0.035;
        final double WEIGHT_FACTOR = 0.029;
        final double HEIGHT_FACTOR = 0.026;
        final double AGE_FACTOR = 0.203;

        double heightMeters = heightCm / 100.0;

        double caloriesPerMinute = BASE_CALORIES_PER_MINUTE +
                (WEIGHT_FACTOR * weightKgs) +
                (HEIGHT_FACTOR * heightMeters) -
                (AGE_FACTOR * Integer.parseInt(age));

        double totalCaloriesBurned = caloriesPerMinute * 30;

        if (gender.equalsIgnoreCase("female")) {
            totalCaloriesBurned *= 0.9;
        }

        return totalCaloriesBurned;
    }

    private String getHealthAdvice(String healthCondition) {
        switch (healthCondition) {
            case "No Health Condition":
                return "Maintain regular physical activity and a balanced diet.";
            case "Heart Disease":
                return "Focus on a heart-healthy diet, regular exercise, and stress management. Avoid smoking and excessive alcohol consumption.";
            case "High Blood Pressure":
                return "Limit salt intake, maintain a healthy weight, exercise regularly, and manage stress. Avoid excessive caffeine and alcohol.";
            case "Chronic Pain":
                return "Consult a healthcare professional for pain management strategies. Avoid prolonged inactivity and overuse of pain medications.";
            case "Back Or Joint Pain":
                return "Strengthen core muscles, maintain good posture, and avoid heavy lifting. Consult a physical therapist for personalized guidance. Avoid prolonged sitting and poor ergonomics.";
            default:
                return "Consult a healthcare professional for personalized advice.";
        }
    }


}





//THIS CODE IS UPDATED BY MUHAMMAD FAHEEM 
//PLEASE RUN AND CHECK IF ITS WORKS OR NOT MY IDE IS NOT WORKING PROPERLY DONT RECOGNIZE THE PROBLEM



package application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static application.ScreenShiftUtils.*;

public class BMIandCalorie {
    private Stage primaryStage;
    private static final String CSS_FILE = "styles.css";

    private TextField weightField;
    private TextField heightField;
    private TextField ageField;
    private ChoiceBox<String> genderChoiceBox;
    private ChoiceBox<String> healthConditionChoiceBox;
    private Label resultLabel;
    private Label conditionLabel;

    public void start(Stage CalculationStage) {
        primaryStage = CalculationStage;
        GridPane gridPane = createGridPane();
        Scene scene = new Scene(gridPane, 700, 600);
        scene.setFill(Color.web("#000000")); // Set scene background color
        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());

        primaryStage.setTitle("BMI Calculator and Calorie Burn Estimation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPrefWidth(300);
        gridPane.getColumnConstraints().addAll(labelColumn);

        addUIControls(gridPane);
        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        gridPane.add(createLabel("Weight (kgs):"), 0, 0);
        weightField = createNumericTextField();
        gridPane.add(weightField, 1, 0);

        gridPane.add(createLabel("Height (cm):"), 0, 1);
        heightField = createNumericTextField();
        gridPane.add(heightField, 1, 1);

        gridPane.add(createLabel("Age:"), 0, 2);
        ageField = createNumericTextField();
        gridPane.add(ageField, 1, 2);

        gridPane.add(createLabel("Gender:"), 0, 3);
        genderChoiceBox = createGenderChoiceBox();
        gridPane.add(genderChoiceBox, 1, 3);

        gridPane.add(createLabel("Health Condition:"), 0, 4);
        healthConditionChoiceBox = createHealthConditionChoiceBox();
        gridPane.add(healthConditionChoiceBox, 1, 4);

        Button calculateButton = new Button("Calculate BMI and Calorie Burn");
        calculateButton.getStyleClass().add("calculate-button");
        gridPane.add(calculateButton, 0, 6, 2, 1);

        resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        gridPane.add(resultLabel, 0, 7, 2, 1);

        conditionLabel = new Label();
        conditionLabel.setPrefWidth(800); // Set preferred width to ensure full text is visible
        conditionLabel.setWrapText(true); // Enable text wrapping for long advice
        gridPane.add(conditionLabel, 0, 10, 2, 1);

        // Create a button to go back to the Dashboard
        Button HomeButton = new Button("BACK");
        HomeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        HomeButton.setOnMouseEntered(e -> HomeButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
        HomeButton.setOnMouseExited(e -> HomeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        HomeButton.setOnAction(event -> {
            showDashboard(primaryStage); // Pass the current stage to close it
        });

        gridPane.add(HomeButton, 0, 15, 2, 1);
        conditionLabel.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold");

        calculateButton.setOnAction(e -> calculateBMIAndCalories());
    }

    private Label createLabel(String labelText) {
        Label label = new Label(labelText);
        label.getStyleClass().add("label");
        return label;
    }

    private TextField createNumericTextField() {
        TextField textField = new TextField();
        textField.getStyleClass().add("input-field");
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(oldValue);
            }
        });
        return textField;
    }

    private ChoiceBox<String> createGenderChoiceBox() {
        ChoiceBox<String> genderChoiceBox = new ChoiceBox<>();
        genderChoiceBox.getStyleClass().add("choice-box");
        genderChoiceBox.getItems().addAll("Male", "Female");
        return genderChoiceBox;
    }

    private ChoiceBox<String> createHealthConditionChoiceBox() {
        ChoiceBox<String> healthConditionChoiceBox = new ChoiceBox<>();
        healthConditionChoiceBox.getStyleClass().add("choice-box");
        healthConditionChoiceBox.getItems().addAll("No Health Condition", "Heart Disease", "High Blood Pressure", "Chronic Pain", "Back Or Joint Pain");
        return healthConditionChoiceBox;
    }

    private void calculateBMIAndCalories() {
        try {
            float weightKgs = Float.parseFloat(weightField.getText());
            float heightCm = Float.parseFloat(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String gender = genderChoiceBox.getValue();
            String healthCondition = healthConditionChoiceBox.getValue();

            if (weightKgs <= 0 || heightCm <= 0 || age <= 0 || gender == null || healthCondition == null) {
                throw new NumberFormatException();
            }

            float heightMeters = heightCm / 100.0f;
            float bmi = weightKgs / (heightMeters * heightMeters);
            String bmiText = String.format("%.2f", bmi);
            String bmiClassification = classifyBMI(bmi);

            float estimatedCaloriesBurned = calculateCaloriesBurned(weightKgs, heightCm, age, gender);
            String caloriesText = String.format("%.2f", estimatedCaloriesBurned);

            resultLabel.setText("BMI: " + bmiText + " (" + bmiClassification + ")\nEstimated Calorie Burn: " + caloriesText + " kcal/day");
            resultLabel.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold");

            String healthAdvice = getHealthAdvice(healthCondition);
            conditionLabel.setText(healthAdvice);
            conditionLabel.setStyle("-fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold");

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter valid numbers and select all fields.");
            resultLabel.setStyle("-fx-text-fill: #FF0000; -fx-font-size: 16px; -fx-font-weight: bold");
        }
    }

    private String classifyBMI(float bmi) {
        if (bmi < 16) {
            return "Severe Thinness";
        } else if (bmi >= 16 && bmi < 17) {
            return "Moderate Thinness";
        } else if (bmi >= 17 && bmi < 18.5) {
            return "Mild Thinness";
        } else if (bmi >= 18.5 && bmi < 25) {
            return "Normal";
        } else if (bmi >= 25 && bmi < 30) {
            return "Overweight";
        } else if (bmi >= 30 && bmi < 35) {
            return "Obese Class I";
        } else if (bmi >= 35 && bmi < 40) {
            return "Obese Class II";
        } else {
            return "Obese Class III";
        }
    }

    private float calculateCaloriesBurned(float weightKgs, float heightCm, int age, String gender) {
        final float BASE_CALORIES_PER_MINUTE = 0.035f;
        final float WEIGHT_FACTOR = 0.029f;
        final float HEIGHT_FACTOR = 0.026f;
        final float AGE_FACTOR = 0.203f;

        float heightMeters = heightCm / 100.0f;

        float caloriesPerMinute = BASE_CALORIES_PER_MINUTE +
                (WEIGHT_FACTOR * weightKgs) +
                (HEIGHT_FACTOR * heightMeters) -
                (AGE_FACTOR * age);

        float totalCaloriesBurned = caloriesPerMinute * 30;

        if (gender.equalsIgnoreCase("female")) {
            totalCaloriesBurned *= 0.9f;
        }

        return totalCaloriesBurned;
    }

    private String getHealthAdvice(String healthCondition) {
        switch (healthCondition) {
            case "No Health Condition":
                return "Maintain regular physical activity and a balanced diet.";
            case "Heart Disease":
                return "Focus on a heart-healthy diet, regular exercise, and stress management. Avoid smoking and excessive alcohol consumption.";
            case "High Blood Pressure":
                return "Limit salt intake, maintain a healthy weight, exercise regularly, and manage stress. Avoid excessive caffeine and alcohol.";
            case "Chronic Pain":
                return "Consult a healthcare professional for pain management strategies. Avoid prolonged inactivity and overuse of pain medications.";
            case "Back Or Joint Pain":
                return "Strengthen core muscles, maintain good posture, and avoid heavy lifting. Consult a physical therapist for personalized guidance. Avoid prolonged sitting and poor ergonomics.";
            default:
                return "Consult a healthcare professional for personalized advice.";
        }
    }
}
