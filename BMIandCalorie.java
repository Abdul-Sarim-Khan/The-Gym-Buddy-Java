package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BMIandCalorie {

    private static final String CSS_FILE = "styles.css";

    @Override
    public void start(Stage CalculationStage) {
        GridPane gridPane = createGridPane();
        Scene scene = new Scene(gridPane, 600, 400);
        scene.setFill(Color.web("#333333")); // Set scene background color
        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());

        CalculationStage.setTitle("BMI Calculator and Calorie Burn Estimation");
        CalculationStage.setScene(scene);
        CalculationStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        ColumnConstraints labelColumn = new ColumnConstraints();
        labelColumn.setPercentWidth(50);
        ColumnConstraints fieldColumn = new ColumnConstraints();
        fieldColumn.setPercentWidth(50);
        gridPane.getColumnConstraints().addAll(labelColumn, fieldColumn);

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

        Button calculateButton = new Button("Calculate BMI and Calorie Burn");
        calculateButton.getStyleClass().add("calculate-button");
        gridPane.add(calculateButton, 0, 4, 2, 1);

        Label resultLabel = new Label();
        resultLabel.getStyleClass().add("result-label");
        gridPane.add(resultLabel, 0, 5, 2, 1);

        calculateButton.setOnAction(e -> calculateBMIAndCalories(gridPane, resultLabel));
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
        genderChoiceBox.setPrefWidth(150);
        genderChoiceBox.getStyleClass().add("choice-box");
        genderChoiceBox.getItems().addAll("Male", "Female");

        // Set the text color and background color
        genderChoiceBox.setStyle("-fx-text-fill: white; -fx-background-color: #333333;");

        return genderChoiceBox;
    }

    private void calculateBMIAndCalories(GridPane gridPane, Label resultLabel) {
        TextField weightField = (TextField) gridPane.getChildren().get(1);
        TextField heightField = (TextField) gridPane.getChildren().get(3);
        TextField ageField = (TextField) gridPane.getChildren().get(5);
        ChoiceBox<String> genderChoiceBox = (ChoiceBox<String>) gridPane.getChildren().get(7);

        try {
            double weightKgs = Double.parseDouble(weightField.getText());
            double heightCm = Double.parseDouble(heightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String gender = genderChoiceBox.getValue();

            if (weightKgs <= 0 || heightCm <= 0 || age <= 0 || gender == null) {
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

        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input. Please enter valid numbers and select gender.");
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

   
}
