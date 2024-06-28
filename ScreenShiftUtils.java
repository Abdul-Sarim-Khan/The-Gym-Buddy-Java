package application;

import javafx.scene.Scene;
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
        	WorkoutPlanSuggestor  workoutPlanSuggestor  = new WorkoutPlanSuggestor();
            Stage newStage = new Stage();
            workoutPlanSuggestor .start(newStage);
            currentStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        public static void BMIandCalorie(Stage currentStage) {
            try {
            	BMIandCalorie  bMIandCalorie  = new BMIandCalorie();
                Stage newStage = new Stage();
                bMIandCalorie .start(newStage);
                currentStage.close(); // Close the current stage
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
       /* public static void Registration(Stage currentStage) {
            try {
            	BMIandCalorie  bMIandCalorie  = new BMIandCalorie();
                Stage newStage = new Stage();
                GYM_BUDDY.primaryStage.start(newStage);
                GYM_BUDDY.primaryStage.setScene(regScene);
                primaryStage.show();
                currentStage.close(); // Close the current stage
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    */
}
