package pl.polsl.lab.hangman.controller;

import org.junit.jupiter.api.Test;
import pl.polsl.lab.hangman.model.Hangman;
import pl.polsl.lab.hangman.model.HangmanGameState;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests only HangmanController independent methods, it is checking game state
 */
class HangmanControllerTest {

    Hangman hangman = new Hangman("test");
    HangmanController hangmanController = new HangmanController(hangman, null, null);

    @Test
    void getHangmanGameStateShouldReturnInProgressState() {
        assertEquals(HangmanGameState.IN_PROGRESS, hangmanController.getHangmanGameState());
    }

    @Test
    void getHangmanGameStateShouldReturnFinishedState() {
        hangman.handleGuessedValue("test");
        hangmanController.checkIfGameIsComplete();
        assertEquals(HangmanGameState.FINISHED, hangmanController.getHangmanGameState());
    }

}