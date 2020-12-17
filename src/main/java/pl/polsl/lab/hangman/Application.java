package pl.polsl.lab.hangman;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hangman game, user has to guess word which is randomly chosen from words at start of game.
 * Game ends if user guess the word or mismatch ten times. Special behavior comes if
 * user sends end of transmission character, that will provide applications end.
 * @author Pawel Potuczko
 * @version 1.0
 */
public class Application extends javafx.application.Application {

    public static Stage stg;

    /**
     * In main are initialized words table, model, view and controllers. Word to guess
     * is randomly picked from table. HangmanController is used as main controller
     * in game loop, which is refreshing view, taking users input and checking state
     * of game.
     * @param args  Are not used
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stg = stage;
        stage.setTitle("Pawel Potuczko");
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("view/HangmanWelcomeView.fxml"));
        Parent root = fxmlLoader.load();

        stage.setScene(new Scene(root, 800,600));
        stage.show();
    }
}