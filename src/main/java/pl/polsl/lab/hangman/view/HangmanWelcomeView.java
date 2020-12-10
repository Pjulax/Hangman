package pl.polsl.lab.hangman.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.polsl.lab.hangman.Application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HangmanWelcomeView implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void onStartButtonClicked() throws IOException {
        try {
            System.out.println("Clicked start :)");
            Stage stage = new Stage();
            stage.setTitle("Pawel Potuczko");
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("HangmanView" + ".fxml"));
            Parent root = fxmlLoader.load();

            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            Application.stg.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
