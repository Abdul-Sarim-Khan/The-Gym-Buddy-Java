package application;

import javafx.stage.Stage;

public class ScreenShiftUtils {
    public static void showDashboard(Stage currentStage) {
        try {
            Dashboard dashboard = new Dashboard();
            Stage newStage = new Stage();
            dashboard.start(newStage);
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showLeaderboard(Stage currentStage) {
        try {
            Leaderboard leaderboard = new Leaderboard();
            Stage newStage = new Stage();
            leaderboard.start(newStage);
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showWorkoutPlanSuggestor(Stage currentStage) {
        try {
            WorkoutPlanSuggestor workoutPlanSuggestor = new WorkoutPlanSuggestor();
            workoutPlanSuggestor.start(currentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showFitnessChallenge(Stage currentStage) {
        try {
        	FitnessChallenge fitnessChallenge = new FitnessChallenge();
        	fitnessChallenge.start(currentStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    public static void showBMIandCalorie(Stage currentStage) {
        try {
            BMIandCalorie bMIandCalorie = new BMIandCalorie();
            Stage newStage = new Stage();
            bMIandCalorie.start(newStage);
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showRegistration(Stage currentStage) {
        try {
            GYM_BUDDY gymBuddy = new GYM_BUDDY();
            Stage newStage = new Stage();
            gymBuddy.showRegistrationForm(newStage);
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
