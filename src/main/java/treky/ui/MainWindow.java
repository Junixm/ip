package treky.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.util.Duration;
import treky.Treky;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Treky treky;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image trekyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Treky instance */
    public void setTreky(Treky treky) {
        this.treky = treky;
        Label welcomeLabel = new Label(treky.getWelcomeMessage());
        dialogContainer.getChildren().add(DialogBox.getTrekyDialog(welcomeLabel, trekyImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Treky's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = treky.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), userImage),
                DialogBox.getTrekyDialog(new Label(response), trekyImage)
        );
        userInput.clear();

        if (treky.getExit()) {
            exitApp();
        }
    }

    private void exitApp() {
        Label dialogLabel = new Label("Exiting in 3");
        Timeline countdown = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> dialogLabel.setText("Exiting in 2")),
                new KeyFrame(Duration.seconds(2), e -> dialogLabel.setText("Exiting in 1")),
                new KeyFrame(Duration.seconds(3), e -> Platform.exit())
        );
        dialogContainer.getChildren().add(DialogBox.getTrekyDialog(dialogLabel, trekyImage));
        countdown.play();
    }
}

