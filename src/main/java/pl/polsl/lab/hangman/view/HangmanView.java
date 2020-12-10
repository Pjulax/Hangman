package pl.polsl.lab.hangman.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pl.polsl.lab.hangman.model.Hangman;
import pl.polsl.lab.hangman.model.HangmanGameState;

import java.net.URL;
import java.util.*;

/**
 * View class, provides methods to print games state for user.
 * @author Pawel Potuczko
 */
public class HangmanView implements Initializable {

    private Hangman hangman;
    @FXML
    private Button guessButton;
    @FXML
    private Text errorText;
    @FXML
    private Text viewWordText;
    @FXML
    private Text mismatchText;
    @FXML
    private TextField guessedValueBox;
    @FXML
    private ImageView imageView1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*        List<String> words = new ArrayList<>();
        words.add("forest");
        words.add("sword");
        words.add("union");
        words.add("collection");
        words.add("company");
        words.add("city"};*/
        List<String> words = new ArrayList<>();
        words.add("kartka");
        words.add("tekst");
        words.add("bażant");
        words.add("komplikacja");
        words.add("lama");
        words.add("zwierze");
        words.add("teczka");

        Random random = new Random(new Date().getTime());
        String choosenWord = words.get(Math.abs(random.nextInt() % words.size()));
        hangman = new Hangman(choosenWord);
        viewWordText.setText(getWordFormatted(hangman.getViewWord()));
        //mismatchText.setText("Mismatched count: " + hangman.getMismatchCount());
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
            errorText.setVisible(false);
            String text = guessedValueBox.getText();
            hangman.handleGuessedValue(text);
            refreshView();
        } catch(IllegalArgumentException e){
            errorText.setVisible(true);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method views actual state of the game. Views hangmans image,
     * state of word, letters used to guess and how many mismatches
     * actually were made.
     */
    public void refreshView() {
        Image image;
        viewWordText.setText(getWordFormatted(hangman.getViewWord()));
        mismatchText.setText("Mismatched count: " + hangman.getMismatchCount());
        if(hangman.isWordGuessed()) {
            guessedValueBox.setDisable(true);
            guessButton.setDisable(true);
            hangman.setGameState(HangmanGameState.FINISHED);
            image = new Image(getClass().getResourceAsStream("/drawings/win.png"));
            imageView1.setImage(image);
        }
        else if(hangman.getMismatchCount() >= 0 && hangman.getMismatchCount() <= 10) {
            String screenNumber = "";
            switch (hangman.getMismatchCount()) {
                case 0:
                    screenNumber = "zero";
                    break;
                case 1:
                    screenNumber = "one";
                    break;
                case 2:
                    screenNumber = "two";
                    break;
                case 3:
                    screenNumber = "three";
                    break;
                case 4:
                    screenNumber = "four";
                    break;
                case 5:
                    screenNumber = "five";
                    break;
                case 6:
                    screenNumber = "six";
                    break;
                case 7:
                    screenNumber = "seven";
                    break;
                case 8:
                    screenNumber = "eight";
                    break;
                case 9:
                    screenNumber = "nine";
                    break;
                case 10:
                    screenNumber = "ten";
                    hangman.setGameState(HangmanGameState.FINISHED);
                    guessedValueBox.setDisable(true);
                    guessButton.setDisable(true);
                    break;
            }
            image = new Image(getClass().getResourceAsStream("/drawings/" + screenNumber + ".png"));
            imageView1.setImage(image);
            //todo - make tree view of "containing and missed" letters

            // System.out.println("Actually used letters: " + lettersUsed);
        }
        else {
            throw new IllegalArgumentException("Mismatch count corrupted, game is ending right now.");
        }
    }

    /**
     * Returns actual word formatted to fancy display, enters spaces between all characters in word
     * @param actualWord    String word with revealed only guessed letters
     * @return  String, actual word with spaces between characters
     */
    private String getWordFormatted(String actualWord){
        StringBuilder actualWordFormatted = new StringBuilder();
        for(int i = 0; i < actualWord.length(); i++){
            actualWordFormatted.append(actualWord.charAt(i));
            if(i < actualWord.length()-1)
                actualWordFormatted.append(" ");
        }
        return actualWordFormatted.toString();
    }

    /**
     * Views final screen for user. In winning scenario congratulates user,
     * tells the word and how many mismatches had player. In losing scenario
     * displays full hangman image and informs what was the correct word.
     * @param isWon         Flag informing if it is winning or losing scenario
     * @param chosenWord    Word, which was to guess
     * @param mismatchCount Count of mismatches during the game
     */
    public void printFinalView(boolean isWon, String chosenWord, int mismatchCount){
        Image image;
        if(isWon) {
            image = new Image(getClass().getResourceAsStream("/drawings/win.png"));
            imageView1.setImage(image);
        }
        else{
            image = new Image(getClass().getResourceAsStream("/drawings/ten.png"));
            imageView1.setImage(image);
        }
    }
}