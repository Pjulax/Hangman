package pl.polsl.lab.hangman.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Model for letters used by user during the game. Provides method to fancy print all letters.
 * @author Pawel Potuczko
 */
class UsedCharactersList {

    private final List<Character> letters;

    public UsedCharactersList() {
        this.letters = new LinkedList<>();
    }

    /**
     * Adds character to letters list, if character already is in list, doesn't add it again.
     * @param character Character to add
     */
    public void addUsedCharacter(Character character){
        if(!letters.contains(character))
            letters.add(character);
    }

    /**
     * Overridden method toString provides list of letters in string formatted to "char1, char2, char3"
     * @return returns string formatted to list of letters
     */
    @Override
    public String toString(){
        return letters.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }
}
