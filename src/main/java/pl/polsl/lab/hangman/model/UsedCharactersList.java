package pl.polsl.lab.hangman.model;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Model for letters used by user during the game. Provides method to fancy print all letters.
 * @author Pawel Potuczko
 */
class UsedCharactersList {

    private final List<Character> lettersContained;
    private final List<Character> lettersNotContained;

    public UsedCharactersList() {
        this.lettersContained = new LinkedList<>();
        this.lettersNotContained = new LinkedList<>();
    }

    /**
     * Adds character to letters list, if character already is in list, doesn't add it again.
     * @param character Character to add
     */
    public void addUsedCharacter(Character character, boolean isWordContaining){
        if (isWordContaining && !lettersContained.contains(character)) {
            lettersContained.add(character);
            lettersContained.sort(Character::compareTo);
        }
        else if (!lettersNotContained.contains(character)){
            lettersNotContained.add(character);
            lettersNotContained.sort(Character::compareTo);
        }
    }

    public List<Character> getLettersContained() {
        return lettersContained;
    }

    public List<Character> getLettersNotContained() {
        return lettersNotContained;
    }

    /**
     * Overridden method toString provides list of letters in string formatted to "char1, char2, char3"
     * @return returns string formatted to list of letters
     */
    @Override
    public String toString(){
        List<Character> letters = new LinkedList<>(lettersContained);
        letters.addAll(lettersNotContained);
        letters.sort(Character::compareTo);
        return letters.stream().map(String::valueOf).collect(Collectors.joining(", "));
    }
}