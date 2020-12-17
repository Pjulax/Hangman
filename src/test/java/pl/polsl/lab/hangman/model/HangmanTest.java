package pl.polsl.lab.hangman.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Hangman model tests on all business logic
 */
class HangmanTest {

    private final Hangman hangman = new Hangman("abcdefghijklmn");

    @ParameterizedTest
    @CsvSource({"test", "tEst", "Java", "1", "123", "$", "ł", "ż", "Ź","abcdefghiJklMn"})
    void handleGuessedValueShouldBeAtBadInputThrowTest(String guessedValue) {
        try{
            hangman.handleGuessedValue(guessedValue);
        }
        catch( IllegalArgumentException ex) {
            assertEquals("Invalid input, you should enter letters only", ex.getMessage(), "IllegalArgumentException didn't catched");
        }
    }

    @ParameterizedTest
    @CsvSource({"test", "tEst", "Java", "ż", "Ź","abcdefghiJklMn", "A"})
    void handleGuessedValueShouldBeBadInputTest(String guessedValue) {
        try{
            int mismatchSaved = hangman.getMismatchCount();
            hangman.handleGuessedValue(guessedValue);
            assertNotEquals(mismatchSaved, hangman.getMismatchCount(), "Input was mismatch");
        }
        catch( IllegalArgumentException ex) {
            fail("IllegalArgumentException catched");
        }
    }

    @ParameterizedTest
    @CsvSource({"a","b","c","abcdefghijklmn"})
    void handleGuessedValueShouldRevealViewWordTest(String guessedValue) {
        try{
            hangman.handleGuessedValue(guessedValue);
            assertTrue(hangman.getViewWord().contains(guessedValue),"Input '" + guessedValue + "' is not valid");
        }
        catch( IllegalArgumentException ex){
            fail("IllegalArgumentException catched");
        }
    }

    @Test
    void getUsedCharactersShouldHaveOneCharacter() {
        try{
            hangman.handleGuessedValue("a");
            assertEquals(hangman.getUsedCharacters(), "a", "characters used are not equal");
        }
        catch( IllegalArgumentException ex){
            fail("IllegalArgumentException catched");
        }
    }

    @ParameterizedTest
    @CsvSource({"ABCdefGhijkLmn","AbcdefGhijklmN","aBcdeFghiJklmn","abcdEfghijKlmN","abcdefghijklmN"})
    void handleGuessedValueShouldBeCaseSensitiveTest(String nonCaseSensitiveAnswer){
        assertEquals(nonCaseSensitiveAnswer.toLowerCase(),hangman.getChosenWord());
        assertNotEquals(nonCaseSensitiveAnswer,hangman.getChosenWord());
    }

}