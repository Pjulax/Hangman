module Hangman {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.junit.jupiter.api;
    requires org.junit.jupiter.params;
    requires org.junit.jupiter.engine;
    opens pl.polsl.lab.hangman to javafx.fxml;
    opens pl.polsl.lab.hangman.view to javafx.fxml;
    exports pl.polsl.lab.hangman;
    exports pl.polsl.lab.hangman.view;
}