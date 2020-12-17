package pl.polsl.lab.hangman.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import pl.polsl.lab.hangman.model.Hangman;

import java.net.URL;
import java.util.*;

/**
 * View class, provides methods to print games state for user.
 * @author Pawel Potuczko
 */
public class HangmanView implements Initializable {

    private Hangman hangman;
    @FXML
    private TreeView usedCharactersTreeView;
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
        String chosenWord = getRandomWord();
        this.hangman = new Hangman(chosenWord);
        this.viewWordText.setText(getWordFormatted(hangman.getViewWord()));
        this.mismatchText.setText("Mismatched count: 0");
        try {
            Image image = new Image(getClass().getResourceAsStream("/drawings/zero.png"));
            imageView1.setImage(image);
        } catch(Exception e){
            Alert alert = ExceptionAlertView.getAlert("Exception Dialog","There was exception while loading image!", e);
            alert.showAndWait();
            System.exit(-1);
        }

        TreeItem containingItem = new TreeItem("Contained");
        TreeItem notContainingItem = new TreeItem("Not contained");
        ArrayList<TreeItem> firstColumn = new ArrayList<>();
        firstColumn.add(containingItem);
        firstColumn.add(notContainingItem);
        TreeItem rootItem = new TreeItem("Used characters");
        rootItem.getChildren().addAll(firstColumn);
        rootItem.setExpanded(true);
        this.usedCharactersTreeView.setRoot(rootItem);
    }

    /**
     * Method provides word from provided word base in random order. Randoms seed are actual time millis.
     * @return word to guess from word base
     */
    private String getRandomWord() {
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
        return words.get(Math.abs(random.nextInt() % words.size()));
    }

    /**
     * This method runs one full game logic iteration - checks input text with word, modifies it and runs refresh on whole view.
     */
    @FXML
    private void onGuessClick(){
        try {
            errorText.setVisible(false);
            String text = guessedValueBox.getText();
            hangman.handleGuessedValue(text);
            refreshView();
        } catch(IllegalArgumentException e){
            errorText.setVisible(true);
        } catch(Exception e){
            Alert alert = ExceptionAlertView.getAlert("Exception Dialog","There was exception while guessing a word!", e);
            alert.showAndWait();
            System.exit(-1);
        }
    }

    /**
     * Method views actual state of the game. Views hangmans image,
     * state of word, letters used to guess and how many mismatches
     * actually were made.
     * @throws IllegalArgumentException - when mismatch in model is corrupted (value not in range 0 - 10)
     */
    private void refreshView() {
        viewWordText.setText(getWordFormatted(hangman.getViewWord()));
        mismatchText.setText("Mismatched count: " + hangman.getMismatchCount());
        if(hangman.isWordGuessed()) {
            setGameWon();
        }
        else if(hangman.getMismatchCount() >= 0 && hangman.getMismatchCount() <= 10) {
            setImageByMismatch();
        }
        else {
            throw new IllegalArgumentException("Mismatch count corrupted.");
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
     * Sets game won image and sets ui in finished game state.
     */
    private void setGameWon() {
        finalizeGame();
        try {
            Image image = new Image(getClass().getResourceAsStream("/drawings/win.png"));
            imageView1.setImage(image);
        }
        catch(Exception e){
            Alert alert = ExceptionAlertView.getAlert("Exception Dialog","There was exception while loading image!", e);
            alert.showAndWait();
            System.exit(-1);
        }
    }

    /**
     *  Disables guessing mechanism.
     */
    private void finalizeGame() {
        hangman.setGameStateFinished();
        guessedValueBox.setDisable(true);
        guessButton.setDisable(true);
    }

    /**
     * Sets images due to mismatch count, when mismatch count is maximum provides finish state image and game state
     */
    private void setImageByMismatch() {
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
                finalizeGame();
                break;
        }
        try {
            Image image = new Image(getClass().getResourceAsStream("/drawings/" + screenNumber + ".png"));
            imageView1.setImage(image);
            usedCharactersTreeView.setRoot(getRefreshedList());
        }
        catch(Exception e){
            Alert alert = ExceptionAlertView.getAlert("Exception Dialog","There was exception while loading image!", e);
            alert.showAndWait();
            System.exit(-1);
        }
    }

    /**
     * Creates Tree Items for TreeView from used characters.
     * @return rootItem containing used character as children
     */
    private TreeItem getRefreshedList() {
        TreeItem containingItem = new TreeItem("Contained");
        TreeItem notContainingItem = new TreeItem("Not contained");

        containingItem.setExpanded(true);
        notContainingItem.setExpanded(true);

        List<TreeItem> containedUsedCharactersList = convertCharacterListToTreeItemList(hangman.getContainingUsedCharactersList());
        List<TreeItem> notContainedUsedCharactersList = convertCharacterListToTreeItemList(hangman.getNotContainingUsedCharactersList());
        containingItem.getChildren().addAll(containedUsedCharactersList);
        notContainingItem.getChildren().addAll(notContainedUsedCharactersList);
        ArrayList<TreeItem> firstColumn = new ArrayList<>();
        firstColumn.add(containingItem);
        firstColumn.add(notContainingItem);

        TreeItem rootItem = new TreeItem("Used characters");
        rootItem.getChildren().addAll(firstColumn);
        rootItem.setExpanded(true);
        return rootItem;
    }

    private List<TreeItem> convertCharacterListToTreeItemList(List<Character> characters) {
        ArrayList<TreeItem> characterItemList = new ArrayList<>();
        for ( Character c : characters ) {
            characterItemList.add(new TreeItem(c.toString()));
        }
        return characterItemList;
    }

}