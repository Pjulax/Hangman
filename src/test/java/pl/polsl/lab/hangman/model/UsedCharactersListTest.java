package pl.polsl.lab.hangman.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.polsl.lab.hangman.utils.ICharactersListToUsedCharactersString;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UsedCharactersList adding and formatting on toString tests
 */
class UsedCharactersListTest {
    UsedCharactersList usedCharactersList = new UsedCharactersList();

    @ParameterizedTest
    @CsvSource({"a,b,c,d","c,d,q,r"})
    void addUsedCharacterShouldAddManyTest(char letter1, char letter2, char letter3, char letter4) {
        usedCharactersList.addUsedCharacter(letter1);
        usedCharactersList.addUsedCharacter(letter2);
        usedCharactersList.addUsedCharacter(letter3);
        usedCharactersList.addUsedCharacter(letter4);
        List<Character> letters = List.of(letter1, letter2, letter3, letter4);
        ICharactersListToUsedCharactersString lambdaMakingUnexpectedValue = () -> letters.stream().map(String::valueOf).collect(Collectors.joining(", "));

        assertEquals(lambdaMakingUnexpectedValue.getStringFromCharactersList(),usedCharactersList.toString(), "Letters are not added");
    }

    @ParameterizedTest
    @CsvSource({"a,a,a","a,a,b","a,c,c"})
    void addUsedCharacterShouldNotAddDuplicatesTest(char letter1, char letter2, char letter3) {
        List<Character> letters = List.of(letter1, letter2, letter3);
        for(Character letter : letters){
            usedCharactersList.addUsedCharacter(letter);
        }
        ICharactersListToUsedCharactersString lambdaMakingUnexpectedValue = () -> letters.stream().map(String::valueOf).collect(Collectors.joining(", "));

        assertNotEquals(lambdaMakingUnexpectedValue.getStringFromCharactersList(), usedCharactersList.toString());
    }



}