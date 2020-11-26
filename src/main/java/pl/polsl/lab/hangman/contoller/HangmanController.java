package pl.polsl.lab.hangman.contoller;

import pl.polsl.lab.hangman.model.Hangman;
import pl.polsl.lab.hangman.view.HangmanView;
import pl.polsl.lab.hangman.view.HangmanViewController;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Base controller of whole Hangman application. Provides methods controlling flow of the game between view and models.
 * @author Pawel Potuczko
 */
public class HangmanController {

    private final Hangman hangman;
    private final HangmanView hangmanView;
    private final HangmanViewController hangmanViewController;
    private HangmanGameState hangmanGameState;

    public HangmanController(Hangman hangman, HangmanView hangmanView, HangmanViewController hangmanViewController) {
        this.hangman = hangman;
        this.hangmanView = hangmanView;
        this.hangmanViewController = hangmanViewController;
        this.hangmanGameState = HangmanGameState.IN_PROGRESS;
    }

    /**
     * Calls view to print logo to output.
     */
    public void printFirstView(){
        hangmanView.printFirstView();
    }

    /**
     * Calls view to print refreshed actual state of game.
     */
    public void refresh() throws IllegalArgumentException{
        hangmanView.refreshView(hangman.getViewWord(), hangman.getUsedCharacters(), hangman.getMismatchCount());
    }

    /**
     * Gets view controllers input and passes it to model to modify state of game.
     * @throws IOException  if user does special behaviour
     */
    public void guess() throws IOException {
        String guessedValue;
        try {
            guessedValue = hangmanViewController.getInput();
            hangman.handleGuessedValue(guessedValue);
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        catch (NoSuchElementException ex) {
            System.out.println("Error happened - " + ex.getMessage());
        }
    }

    /**
     * Checks if game is ended.
     */
    public void checkIfGameIsComplete() {
        if (hangman.isWordGuessed() || hangman.getMismatchCount() >= 10)
            hangmanGameState = HangmanGameState.FINISHED;
    }

    /**
     * Calls view to print final view of the game.
     */
    public void printFinalView(){
        hangmanView.printFinalView((hangman.getChosenWord().equals(hangman.getViewWord())), hangman.getChosenWord(), hangman.getMismatchCount());
    }

    public HangmanGameState getHangmanGameState() {
        return hangmanGameState;
    }
}