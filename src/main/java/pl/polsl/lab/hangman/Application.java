package pl.polsl.lab.hangman;

import pl.polsl.lab.hangman.contoller.HangmanController;
import pl.polsl.lab.hangman.contoller.HangmanGameState;
import pl.polsl.lab.hangman.model.Hangman;
import pl.polsl.lab.hangman.view.HangmanView;
import pl.polsl.lab.hangman.view.HangmanViewController;

import java.io.IOException;
import java.util.*;

/**
 * Hangman game, user has to guess word which is randomly chosen from words at start of game.
 * Game ends if user guess the word or mismatch ten times. Special behavior comes if
 * user sends end of transmission character, that will provide applications end.
 * @author Pawel Potuczko
 * @version 1.0
 */
public class Application {

    /**
     * In main are initialized words table, model, view and controllers. Word to guess
     * is randomly picked from table. HangmanController is used as main controller
     * in game loop, which is refreshing view, taking users input and checking state
     * of game.
     * @param args  Are not used
     */
    public static void main(String[] args) {
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
        Hangman hangman = new Hangman(words.get(Math.abs(random.nextInt() % words.size())));
        HangmanView hangmanView = new HangmanView();
        HangmanViewController hangmanViewController = new HangmanViewController(new Scanner(System.in));
        HangmanController hangmanController = new HangmanController(hangman, hangmanView, hangmanViewController);

        try {
            hangmanController.printFirstView();

            do {
                hangmanController.refresh();
                hangmanController.guess();
                hangmanController.checkIfGameIsComplete();
            } while (hangmanController.getHangmanGameState().equals(HangmanGameState.IN_PROGRESS));

            hangmanController.printFinalView();

            System.out.println("Press enter key to close application.");
            new Scanner(System.in).nextLine();
        }
        catch (IllegalArgumentException | IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}