package pl.polsl.lab.hangman.contoller;

import java.io.IOException;
import java.util.Scanner;

/**
 * Handles with users interaction from console.
 * @author Pawel Potuczko
 */
public class HangmanViewController {

    private final Scanner sc;



    public HangmanViewController(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Returns users input, only checks if scanner reads properly.
     * @return String with users input
     * @throws IOException If end of transmission character is passed, application flow is redirected to be terminated
     */
    public String getInput() throws IOException {
        System.out.println("Answer with letter or full word: ");
        String text;
        if (sc.hasNextLine()) {
            text = sc.nextLine();
            return text;
        }
        else {
            throw new IOException("Application terminating, possibly end of transmission character has been used.");
        }
    }
}