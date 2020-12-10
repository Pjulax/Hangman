package pl.polsl.lab.hangman.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * View class, provides methods to print games state for user.
 * @author Pawel Potuczko
 */
public class HangmanView implements Initializable {

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

    /**
     * Provides Logo "Hangman" as first view seen in console.
     */
    public void printFirstView(){
        System.out.print(" __    __\n");
        System.out.print("|  \\  |  \\\n");
        System.out.print("| $$  | $$  ______   _______    ______   ______ ____    ______   _______\n");
        System.out.print("| $$__| $$ |      \\ |       \\  /      \\ |      \\    \\  |      \\ |       \\\n");
        System.out.print("| $$    $$  \\$$$$$$\\| $$$$$$$\\|  $$$$$$\\| $$$$$$\\$$$$\\  \\$$$$$$\\| $$$$$$$\\\n");
        System.out.print("| $$$$$$$$ /      $$| $$  | $$| $$  | $$| $$ | $$ | $$ /      $$| $$  | $$\n");
        System.out.print("| $$  | $$|  $$$$$$$| $$  | $$| $$__| $$| $$ | $$ | $$|  $$$$$$$| $$  | $$\n");
        System.out.print("| $$  | $$ \\$$    $$| $$  | $$ \\$$    $$| $$ | $$ | $$ \\$$    $$| $$  | $$\n");
        System.out.print(" \\$$   \\$$  \\$$$$$$$ \\$$   \\$$ _\\$$$$$$$ \\$$  \\$$  \\$$  \\$$$$$$$ \\$$   \\$$\n");
        System.out.print("                               | \\__| $$\n");
        System.out.print("                                \\$$   $$\n");
        System.out.print("                                 \\$$$$$$\n");
        System.out.print("\n");
    }

    /**
     * Method views actual state of the game. Views hangmans image,
     * state of word, letters used to guess and how many mismatches
     * actually were made.
     * @param actualWord    String word with revealed only guessed letters
     * @param lettersUsed   String containing letters used, while trying to guess
     * @param mismatchCount Integer number of mismatches since start of the game
     */
    public void refreshView(String actualWord, String lettersUsed, Integer mismatchCount) {
        if(mismatchCount >= 0 && mismatchCount < 10) {
            switch (mismatchCount) {
                case 1:
                    System.out.print("=================================================\n" +
                            "\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "=================================================\n" +
                            "===              First mismatch               ===\n" +
                            "=================================================\n\n");
                    break;
                case 2:
                    System.out.print("=================================================\n" +
                            "\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===              Second mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
                case 3:
                    System.out.print("=================================================\n" +
                            "      ______\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===               Third mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
                case 4:
                    System.out.print("=================================================\n" +
                            "      ______\n" +
                            "     |      |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===              Fourth mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
                case 5:
                    System.out.print("=================================================\n" +
                            "      ______\n" +
                            "     |      |\n" +
                            "     |      @\n" +
                            "     |\n" +
                            "     |\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===               Fifth mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
                case 6:
                    System.out.print("=================================================\n" +
                            "      ______\n" +
                            "     |      |\n" +
                            "     |      @\n" +
                            "     |      |\n" +
                            "     |\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===               Sixth mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
                case 7:
                    System.out.print("=================================================\n" +
                            "      ______\n" +
                            "     |      |\n" +
                            "     |      @\n" +
                            "     |     /|\n" +
                            "     |\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===             Seventh mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
                case 8:
                    System.out.print("=================================================\n" +
                            "      ______\n" +
                            "     |      |\n" +
                            "     |      @\n" +
                            "     |     /|\\\n" +
                            "     |\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===              Eighth mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
                case 9:
                    System.out.print("=================================================\n" +
                            "      ______\n" +
                            "     |      |\n" +
                            "     |      @\n" +
                            "     |     /|\\\n" +
                            "     |     /\n" +
                            "     |\n" +
                            "    /|\\\n" +
                            "=================================================\n" +
                            "===               Ninth mismatch              ===\n" +
                            "=================================================\n\n");
                    break;
            }
            System.out.println("Actual state of word: " + getWordFormatted(actualWord));
            System.out.println("Actually used letters: " + lettersUsed);
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
        if(isWon) {
            System.out.println("Congratulations, you won!\nYou guessed \"" + chosenWord + "\" with " + mismatchCount + " mismatches.");
        }
        else{
            System.out.print(   "=================================================\n" +
                                "      ______\n" +
                                "     |      |\n" +
                                "     |      @\n" +
                                "     |     /|\\\n" +
                                "     |     / \\\n" +
                                "     |\n" +
                                "    /|\\\n" +
                                "=================================================\n" +
                                "===                 GAME OVER                 ===\n" +
                                "=================================================\n\n");
            System.out.println("You lost :(\nWord to guess was \"" + chosenWord + "\", it's not your lucky day.");
        }
    }
}