package pl.polsl.lab.hangman.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
//todo - delete this and rename HangmanView
/**
 * Handles with users interaction from console.
 * @author Pawel Potuczko
 */
public class HangmanViewController implements Initializable {

    @FXML
    private ImageView imageView1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image;
        try {
            image = new Image(getClass().getResourceAsStream("/drawings/zero.png"));
            imageView1.setImage(image);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onGuessClick(){
        try {
            Image image = new Image(getClass().getResourceAsStream("/drawings/eight.png"));
            imageView1.setImage(image);
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    private final Scanner sc;



    public HangmanViewController(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Returns users input, only checks if scanner reads properly.
     * @return String with users input
     * @throws IOException If end of transmission character is passed, application flow is redirected to be terminated
     */
    public String getInput() throws IOException {
        System.out.println("Answer with letter or full word: ");
        String text;
        if (sc.hasNextLine()) {
            text = sc.nextLine();
            return text;
        }
        else {
            throw new IOException("Application terminating, possibly end of transmission character has been used.");
        }
    }
}