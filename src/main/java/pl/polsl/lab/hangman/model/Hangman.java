package pl.polsl.lab.hangman.model;

/**
 * Model of data needed to keep state of hangman game.
 * Provides method to modify it's state.
 * @author Pawel Potuczko
 */
public class Hangman {

    private Integer mismatchCount;
    private HangmanGameState gameState;
    private String viewWord;
    private final String chosenWord;
    private final UsedCharactersList usedCharacters;

    public Hangman(String chosenWord) {
        this.mismatchCount = 0;
        this.gameState = HangmanGameState.IN_PROGRESS;
        this.chosenWord = chosenWord;
        this.usedCharacters = new UsedCharactersList();
        this.viewWord = initViewWord(chosenWord);
    }

    /**
     * Initialize viewWord as underlines with length of chosenWord.
     * @param chosenWord    Word to guess
     * @return  Hidden word string
     */
    private String initViewWord(String chosenWord){
        StringBuilder viewWordBuilder = new StringBuilder();
        for(int i = 0; i < chosenWord.length(); i++)
            viewWordBuilder.append("_");
        return viewWordBuilder.toString();
    }

    /**
     * First layer of game state modifying methods in model.
     * Decides if input is valid and if is proceeded as letter or word.
     * @param guessedValue  String with users input of guessed word or letter
     * @throws IllegalArgumentException if input is null or not matches regex pattern
     */
    public void handleGuessedValue(String guessedValue) throws IllegalArgumentException {
        if(guessedValue != null && guessedValue.matches("^[\\p{L}]+$")) {
            if (guessedValue.length() > 1) {
                handleWordGuess(guessedValue);
            } else {
                Character letter = guessedValue.toCharArray()[0];
                handleLetterGuess(letter);
            }
        }
        else {
            throw new IllegalArgumentException("Invalid input, you should enter letters only");
        }
    }

    /**
     * As input is proceeded as word, it checks if word is good
     * and reveals it or increment mismatch count.
     * @param guessedWord   Word passed by user
     */
    private void handleWordGuess(String guessedWord) {
        if(isChosenWordEqual(guessedWord))
            setViewWord();
        else
            mismatchCount++;
    }

    /**
     * Checks if word equals chosen word.
     * @param guessedWord   Word passed by user
     * @return  true if guessed word is good
     */
    private boolean isChosenWordEqual(String guessedWord) {
        return chosenWord.equals(guessedWord);
    }

    /**
     * Sets view word value to chosen word value.
     * Is used when user guessed full word.
     */
    private void setViewWord() {
        viewWord = chosenWord;
    }

    /**
     * As input is proceeded as character it adds letter
     * to used characters list and reveals letter in word
     * or increment mismatch count.
     * @param letter    Character passed by user
     */
    private void handleLetterGuess(Character letter) {
        usedCharacters.addUsedCharacter(letter);
        if(isWordContainingLetter(letter))
            revealGuessedLetterInViewWord(letter);
        else
            mismatchCount++;
    }

    /**
     * Checks if letter contains in word.
     * @param letter    Character passed by user
     * @return  true if guessed letter is containing in word
     */
    private boolean isWordContainingLetter(Character letter) {
        return chosenWord.contains(letter.toString());
    }

    /**
     * Reveals letter in all positions in word.
     * @param letter    Character passed by user
     */
    private void revealGuessedLetterInViewWord(Character letter) {
        StringBuilder newViewWord = new StringBuilder(viewWord);
        for (int i = 0; i < chosenWord.length(); i++){
            if(chosenWord.charAt(i)==letter)
                newViewWord.setCharAt(i, letter);
        }
        viewWord = newViewWord.toString();
    }

    public boolean isWordGuessed(){
        return chosenWord.equals(viewWord);
    }

    public String getViewWord() {
        return viewWord;
    }

    public String getChosenWord() {
        return chosenWord;
    }

    /**
     * @return list of used characters in string formatted as "char1, char2, char3"
     */
    public String getUsedCharacters(){
        return usedCharacters.toString();
    }

    public Integer getMismatchCount() {
        return mismatchCount;
    }

    public HangmanGameState getGameState() {
        return gameState;
    }

    public void setGameState(HangmanGameState gameState) {
        this.gameState = gameState;
    }
}