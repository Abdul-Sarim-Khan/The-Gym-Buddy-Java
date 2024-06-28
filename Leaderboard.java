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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static application.ScreenShiftUtils.*;

public class Leaderboard{

    public void start(Stage LeaderBoardStage) {
        // Sample data for leaderboard
        ObservableList<Player> leaderboardData = FXCollections.observableArrayList(
                new Player("Player A", 3000),
                new Player("Player B", 400),
                new Player("Player C", 4500),
                new Player("Player D", 4200),
                new Player("Player E", 4000)
        );

        // Sort leaderboard data in descending order with respect to points
        List<Player> sortedPlayers = leaderboardData.stream()
                .sorted(Comparator.comparingInt(Player::getPoints).reversed())
                .collect(Collectors.toList());

        // Format sorted leaderboard data
        ObservableList<String> formattedLeaderboardData = FXCollections.observableArrayList();
        int position = 1;
        for (Player player : sortedPlayers) {
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

    // Helper class to represent a player
    private static class Player {
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
}