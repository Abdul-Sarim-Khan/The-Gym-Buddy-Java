package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static application.ScreenShiftUtils.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;



// Leaderboard Class

public class Leaderboard {
    public static int p; //I WANT USER POSITION TO BE STORED IN HERE SO I CAN EXPORT THIS VARIABLE TO OTHER CLASSES [POSITION IS STORED IN Data.txt  (Example: <name><password><points><position>]
    public static String user;
    
    public void start(Stage LeaderBoardStage, String loggedInUser) {
        user = loggedInUser;

     // Sample data for leaderboard
        ObservableList<Player> leaderboardData = FXCollections.observableArrayList();

        // Read all users and their points from the file
        try (BufferedReader br = new BufferedReader(new FileReader(GYM_BUDDY.filePath))) {
            String currentLine;
            String[] data;
            while ((currentLine = br.readLine()) != null) {
                data = currentLine.split(",");
                String playerName = data[0];
                int playerPoints = Integer.parseInt(data[2]);
                leaderboardData.add(new Player(playerName, playerPoints));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Sort leaderboard data in descending order with respect to points
        List<Player> sortedPlayers = leaderboardData.stream()
                .sorted(Comparator.comparingInt(Player::getPoints).reversed())
                .collect(Collectors.toList());

        // Format sorted leaderboard data and find the position of the logged-in user
        ObservableList<String> formattedLeaderboardData = FXCollections.observableArrayList();
        int position = 1;
        
        //
        for (Player player : sortedPlayers) {
            updatePositionInFile(player.getName(), position); // Update position in the file
            formattedLeaderboardData.add(formatLeaderboardEntry(position++, player.getName(), player.getPoints()));
        }
        
        
        
        // Creating UI elements
        Label titleLabel = new Label("LEADERBOARD");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #00C958; -fx-font-weight: bold");
        titleLabel.setPadding(new Insets(20, 0, 20, 0));

        Label headingLabel = new Label(String.format("%-35s %-35s %s", "Position", "Name", "Points"));
        headingLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold");

        ListView<String> leaderboardListView = new ListView<>(formattedLeaderboardData);
        leaderboardListView.setStyle("-fx-control-inner-background: #333333; -fx-background-color: #00C958; -fx-font-size: 20px; -fx-font-weight: bold");
        leaderboardListView.setPrefHeight(400);

        // Create a button to go back to the Dashboard
        Button HomeButton = new Button("DASHBOARD");
        HomeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        HomeButton.setOnMouseEntered(e -> HomeButton.setStyle("-fx-background-color: white; -fx-text-fill: #00C958; -fx-font-size: 16px; -fx-font-weight: bold;"));
        HomeButton.setOnMouseExited(e -> HomeButton.setStyle("-fx-background-color: #00C958; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;"));
        HomeButton.setOnAction(event -> {
            showDashboard(LeaderBoardStage); // Pass the current stage to close it
        });

        // VBox layout for the screen
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY))); // Set overall background color
        vbox.getChildren().addAll(titleLabel, headingLabel, leaderboardListView, HomeButton);

        // Scene creation
        Scene scene = new Scene(vbox, 800, 600);
        LeaderBoardStage.setTitle("Leaderboard Screen");
        LeaderBoardStage.setScene(scene);
        LeaderBoardStage.show();
    }

    private String formatLeaderboardEntry(int position, String playerName, int points) {
        return String.format("%-45s %-42s %d points", position, playerName, points);
    }

    private void updatePositionInFile(String playerName, int position) {
        File inputFile = new File(GYM_BUDDY.filePath);
        StringBuilder newData = new StringBuilder();
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                String[] data = currentLine.split(",");
                if (data[0].equals(playerName)) {
                    newData.append(data[0]).append(",").append(data[1]).append(",").append(data[2]).append(",").append(position).append(System.lineSeparator());
                   
                } else {
                    newData.append(currentLine).append(System.lineSeparator());
                }
               
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write(newData.toString());
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    // Helper class to represent a player
    class Player {
        private final String name;
        private final int points;

        public Player(String name, int points) {
            this.name = name;
            this.points = points;
        }

        public String getName() {
            return name;
        }
    
        public int getPoints() {
            return points;
        }
    }
