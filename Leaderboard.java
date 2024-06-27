package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Leaderboard{

   
    public void start(Stage LeaderBoardStage) {
        // Sample data for leaderboard
        ObservableList<String> leaderboardData = FXCollections.observableArrayList(
                formatLeaderboardEntry(1, "Player A", 5000),
                formatLeaderboardEntry(2, "Player B", 4800),
                formatLeaderboardEntry(3, "Player C", 4500),
                formatLeaderboardEntry(4, "Player D", 4200),
                formatLeaderboardEntry(5, "Player E", 4000)
        );

        // Creating UI elements
        Label titleLabel = new Label("LEADERBOARD");
        titleLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #00C958; -fx-font-weight: bold");
        titleLabel.setPadding(new Insets(20, 0, 20, 0));

        Label headingLabel = new Label(String.format("%-35s %-35s %s", "Position", "Name", "Points"));
        headingLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: white; -fx-font-weight: bold");

        ListView<String> leaderboardListView = new ListView<>(leaderboardData);
        leaderboardListView.setStyle("-fx-background-color: #00C958; -fx-font-size: 20px; -fx-font-weight: bold");
        leaderboardListView.setPrefHeight(400);

        // VBox layout for the screen
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY))); // Set overall background color
        vbox.getChildren().addAll(titleLabel, headingLabel, leaderboardListView);

        // Scene creation
        Scene scene = new Scene(vbox, 800, 600);
        LeaderBoardStage.setTitle("Leaderboard Screen");
        LeaderBoardStage.setScene(scene);
        LeaderBoardStage.show();
    }

    private String formatLeaderboardEntry(int position, String playerName, int points) {
        return String.format("%-45s %-42s %d points", position, playerName, points);
    }

}