package com.projects.text.textprocessing;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InputPrompt {
    public static String displayAdd() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Item");
        window.setMinWidth(800);
        window.setMinHeight(500);

        TextArea inputField = new TextArea();
        inputField.setPrefWidth(400);  // Set the preferred width
        inputField.setPrefHeight(250);
        inputField.setPromptText("Enter text here");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(inputField, addButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return inputField.getText();
    }
    public static String displayAdd(String initialText) {  // Modify here to accept initial text
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit Item");
        window.setMinWidth(250);

        TextField inputField = new TextField();
        inputField.setText(initialText);  // Set the initial text to the input field
        inputField.setPromptText("Enter text here");

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(inputField, saveButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return inputField.getText();
    }

    public static String[] displaySearch() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search Text");
        window.setMinWidth(300);

        // Text field for direct word search
        TextField wordField = new TextField();
        wordField.setPromptText("Enter a word to search");

        // Text field for regular expression search
        TextField regexField = new TextField();
        regexField.setPromptText("Enter a regular expression");

        Button submitButton = new Button("Search");
        submitButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(
                new Label("Search by word:"),
                wordField,
                new Label("Search by regular expression:"),
                regexField,
                submitButton
        );

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return new String[] { wordField.getText(), regexField.getText() };
    }

    public static String[] displayReplace() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Replace Text");
        window.setMinWidth(300);

        TextField patternField = new TextField();
        patternField.setPromptText("Enter a word or regex pattern to replace");

        TextField replacementField = new TextField();
        replacementField.setPromptText("Enter replacement text");

        Button submitButton = new Button("Replace");
        submitButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(
                new Label("Pattern (word or regex):"),
                patternField,
                new Label("Replacement Text:"),
                replacementField,
                submitButton
        );

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return new String[] { patternField.getText(), replacementField.getText() };
    }
    public static String[] displayMatch() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Match Text");
        window.setMinWidth(300);

        TextField wordField = new TextField();
        wordField.setPromptText("Enter a word to match exactly");

        TextField regexField = new TextField();
        regexField.setPromptText("Enter a regular expression to match");

        Button submitButton = new Button("Match");
        submitButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(
                new Label("Match by word:"),
                wordField,
                new Label("Match by regular expression:"),
                regexField,
                submitButton
        );

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return new String[] { wordField.getText(), regexField.getText() };
    }
}
