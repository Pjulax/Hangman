package pl.polsl.lab.hangman.view;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests essential refresh method
 */
public class HangmanViewTest {

    HangmanView hangmanView = new HangmanView();

    @ParameterizedTest
    @CsvSource({"0","5","7"})
    public void refreshShouldWorkProperly(int mismatchCount){
        try {
            hangmanView.refreshView("test", "test", mismatchCount);
        }
        catch(IllegalArgumentException ex) {
            fail("Exception has existed.");
        }

    }

    @ParameterizedTest
    @CsvSource({"-1","10","11"})
    public void refreshShouldThrowIllegalArgumentException(int mismatchCount){
        try {
            hangmanView.refreshView("test", "test", mismatchCount);
            fail();
        }
        catch(IllegalArgumentException ex) {
            assertEquals("Mismatch count corrupted, game is ending right now.", ex.getMessage());
        }

    }
}
