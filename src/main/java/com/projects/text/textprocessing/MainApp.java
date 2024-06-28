package com.projects.text.textprocessing;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MainApp extends Application {
    private final TableView<Item> tableView = new TableView<>();
    private final ArrayList<Item> dataList = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        // Initialize table columns
        TableColumn<Item, String> column = new TableColumn<>("Words");
        column.setCellValueFactory(new PropertyValueFactory<>("text"));
        tableView.getColumns().add(column);

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create buttons
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> onAdd());

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> onDelete());

        Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction(e -> onUpdate());

        // Other buttons (Search, Update, Delete, Replace, Match) without functionality for simplicity
        Button btnSearch = new Button("Search");
        btnSearch.setOnAction(e -> onSearch());

        Button btnReplace = new Button("Replace");
        btnReplace.setOnAction(e -> onReplace());

        Button btnMatch = new Button("Match");
        btnMatch.setOnAction(e -> onMatch());


        HBox buttonLayout = new HBox(10, btnSearch, btnUpdate, btnAdd, btnDelete, btnReplace, btnMatch);
        VBox mainLayout = new VBox(10, tableView, buttonLayout);
        mainLayout.setStyle("-fx-background-color: #F6E6CB;");
        mainLayout.setPrefSize(800, 500);
        mainLayout.setPadding(new Insets(30));
        buttonLayout.setAlignment(Pos.CENTER);
        mainLayout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainLayout);
        primaryStage.setTitle("Text Processing Tool");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void onAdd() {
        String result = InputPrompt.displayAdd();
        if (result != null && !result.isEmpty()) {
            dataList.add(new Item(result));
            tableView.getItems().setAll(dataList);
        }
    }

    private void onDelete() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            dataList.remove(selectedItem);
            tableView.getItems().remove(selectedItem);
        }
    }


    private void onSearch() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] inputs = InputPrompt.displaySearch();
            String wordSearch = inputs[0];
            String regexSearch = inputs[1];
            String content = selectedItem.getText();

            if (!wordSearch.isEmpty()) {
                // Simple search using word
                searchAndDisplay(content, Pattern.quote(wordSearch), "Word");
            } else if (!regexSearch.isEmpty()) {
                // Regex search
                searchAndDisplay(content, regexSearch, "Regular expression");
            } else {
                showAlert("Search Result", "Please enter a word or a regular expression to search.");
            }
        }
    }
    private void searchAndDisplay(String content, String patternText, String searchType) {
        Pattern pattern = Pattern.compile(patternText);
        Matcher matcher = pattern.matcher(content);

        StringBuilder resultMessage = new StringBuilder();
        boolean found = false;

        while (matcher.find()) {
            found = true;
            resultMessage.append("Found '").append(matcher.group()).append("' at position ").append(matcher.start()).append("\n");
        }

        if (found) {
            showAlert("Search Result", "Found matches:\n" + resultMessage.toString());
        } else {
            showAlert("Search Result", "No matches found");
        }
    }

    private void onReplace() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String[] inputs = InputPrompt.displayReplace();
            String patternText = inputs[0];
            String replacementText = inputs[1];

            if (!patternText.isEmpty() && !replacementText.isEmpty()) {
                Pattern pattern = Pattern.compile(patternText);
                Matcher matcher = pattern.matcher(selectedItem.getText());
                if (matcher.find()) {
                    String originalText = selectedItem.getText();
                    String newText = matcher.replaceAll(replacementText);
                    selectedItem.setText(newText);
                    tableView.refresh();
                    showReplaceResult(originalText, newText, patternText, replacementText);
                } else {
                    showAlert("Replace Result", "No matches found for replacement.");
                }
            } else {
                showAlert("Replace Result", "Please enter both a pattern and a replacement text.");
            }
        }
    }

    private void showReplaceResult(String originalText, String newText, String patternText, String replacementText) {
        TextFlow originalFlow = new TextFlow(new Text(originalText));
        TextFlow newFlow = highlightText(newText, replacementText);
        showAlertWithTextFlow("Replace Result", "The replacement has been done", originalFlow,newFlow);
    }

    private TextFlow highlightText(String content, String searchQuery) {
        TextFlow textFlow = new TextFlow();
        int lastEnd = 0;
        // Compile the pattern to be case-insensitive
        Pattern pattern = Pattern.compile(Pattern.quote(searchQuery.trim()));
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            Text priorText = new Text(content.substring(lastEnd, matcher.start()));
            Text matchedText = new Text(content.substring(matcher.start(), matcher.end()));
//            matchedText.setStyle("-fx-font-weight: bold; -fx-fill: green;");
            textFlow.getChildren().addAll(priorText, matchedText);
            lastEnd = matcher.end();
        }
        Text remainingText = new Text(content.substring(lastEnd));
        // Apply a default style if needed
        remainingText.setStyle("-fx-font-weight: normal; -fx-fill: black;");
        textFlow.getChildren().add(remainingText);
        return textFlow;
    }



    private void showAlertWithTextFlow(String title, String header, TextFlow content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.getDialogPane().setContent(content);
        alert.showAndWait();
    }
    private void showAlertWithTextFlow(String title, String header, TextFlow originalFlow, TextFlow newFlow) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);

        // Create a VBox to hold both TextFlows
        VBox contentBox = new VBox(10);  // Spacing of 10 pixels
        contentBox.getChildren().add(new Label("The original Text:"));
        contentBox.getChildren().add(originalFlow);
        contentBox.getChildren().add(new Label(" Has been modified to"));
        contentBox.getChildren().add(newFlow);

        // Set the content of the alert
        alert.getDialogPane().setContent(contentBox);
        alert.showAndWait();
    }

    private void onMatch() {
        String[] inputs = InputPrompt.displayMatch();
        String wordMatch = inputs[0];
        String regexMatch = inputs[1];
        boolean found = false;

        // Check if a word match is requested
        if (!wordMatch.isEmpty()) {
            Pattern pattern = Pattern.compile("\\b" + Pattern.quote(wordMatch) + "\\b", Pattern.UNICODE_CHARACTER_CLASS);
            for (Item item : dataList) {
                Matcher matcher = pattern.matcher(item.getText());
                if (matcher.find()) {
                    showAlert("Match Result", "Word match found.");
                    found = true;
                    break;
                }
            }
        }

        // Check if a regex match is requested
        if (!found && !regexMatch.isEmpty()) {
            Pattern pattern = Pattern.compile(regexMatch);
            for (Item item : dataList) {
                Matcher matcher = pattern.matcher(item.getText());
                if (matcher.find()) {
                    showAlert("Match Result", "Regular expression match found.");
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            showAlert("Match Result", "No match found.");
        }
    }



    private void onUpdate() {
        Item selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            String currentText = selectedItem.getText(); // Current text before updating
            String updatedText = InputPrompt.displayAdd(currentText); // Prompt user for new text

            // Check if the updated text is not null, not empty, and different from the current text
            if (updatedText != null && !updatedText.isEmpty() && !updatedText.equals(currentText)) {
                selectedItem.setText(updatedText); // Update the item's text
                tableView.refresh(); // Refresh the TableView to reflect the update

                // Optionally, if you need to ensure the ArrayList is explicitly updated:
                int index = dataList.indexOf(selectedItem); // Find the index of the item in the ArrayList
                if (index != -1) {
                    dataList.set(index, selectedItem); // Update the ArrayList (optional since it's the same instance)
                }

                showAlert("Update Successful", "The item has been updated successfully.");
            }
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Create a scrollable text area for the content
        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);

        // Wrap the text area in a scroll pane
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setMaxWidth(Double.MAX_VALUE);
        scrollPane.setMaxHeight(Double.MAX_VALUE);

        // Set the scroll pane as the content of the alert
        alert.getDialogPane().setContent(scrollPane);
        alert.getDialogPane().setPrefSize(480, 320);  // Adjust size as needed

        alert.showAndWait();
    }


    public static void main(String[] args) {
        launch(args);
    }

    // Data model class
    public class Item {
        private final SimpleStringProperty text;

        public Item(String text) {
            this.text = new SimpleStringProperty(text);
        }


        public String getText() {
            return text.get();
        }

        public void setText(String text) {
            this.text.set(text);
        }


        public StringProperty textProperty() {
            return text;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Item item = (Item) obj;
            return text != null ? text.get().equals(item.text.get()) : item.text == null;
        }

        @Override
        public int hashCode() {
            return text != null ? text.get().hashCode() : 0;
        }
    }
}
