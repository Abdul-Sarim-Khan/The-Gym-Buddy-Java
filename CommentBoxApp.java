package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CommentBoxApp extends Application {

    private Scene mainScene;

    @Override
    public void start(Stage primaryStage) {
        // Create a TextArea for the comment box
        TextArea commentBox = new TextArea();
        commentBox.setPromptText("Enter your comment here...");
        commentBox.setPrefSize(300, 200);

        // Create a Submit button
        Button submitButton = new Button("Submit");

        // Create three other buttons
        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        Button button3 = new Button("Button 3");

        // Create a VBox layout and add the comment box, submit button, and other buttons to it
        VBox vbox = new VBox(10); // 10 is the spacing between elements
        vbox.getChildren().addAll(commentBox, submitButton, button1, button2, button3);

        // Create the main scene with the VBox layout
        mainScene = new Scene(vbox, 350, 300);

        // Event handlers for buttons to open new pages
        button1.setOnAction(event -> openNewPage(primaryStage, "This is Page 1"));
        button2.setOnAction(event -> openNewPage(primaryStage, "This is Page 2"));
        button3.setOnAction(event -> openNewPage(primaryStage, "This is Page 3"));

        // Set the title of the stage (window) and add the main scene to it
        primaryStage.setTitle("Comment Box App");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void openNewPage(Stage primaryStage, String message) {
        // Create a label with the given message
        Label label = new Label(message);

        // Create a button to go back to the main scene
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> primaryStage.setScene(mainScene));

        // Create a layout for the new page
        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, backButton);

        // Create a new scene with the layout
        Scene newScene = new Scene(layout, 350, 300);

        // Set the new scene on the primary stage
        primaryStage.setScene(newScene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}