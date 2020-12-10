module Hangman {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    opens pl.polsl.lab.hangman to javafx.fxml;
    opens pl.polsl.lab.hangman.view to javafx.fxml;
    exports pl.polsl.lab.hangman;
    exports pl.polsl.lab.hangman.view;
}