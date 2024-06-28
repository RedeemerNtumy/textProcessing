# Text Processing Tool

## Project Description

The Text Processing Tool is a JavaFX application designed to provide users with various text manipulation capabilities. It allows users to add, delete, update, search, match, and replace text based on both simple and regular expression searches. This tool aims to enhance the efficiency of text manipulation tasks while providing a user-friendly graphical interface.

## Features

### Core Features

1. **Add Text**
   - Users can add new text entries to the system, which are then displayed in a table view.
   - Accessible via an "Add" button, prompting a dialog where the user can input the new text.

2. **Delete Text**
   - Allows users to select and delete text entries from the displayed table.
   - Accessible via a "Delete" button.

3. **Update Text**
   - Users can update existing text entries.
   - A selection from the table allows editing through a prompted dialog.

4. **Search Text**
   - Provides the ability to search through the selected text entry for specific words or using regular expressions.
   - The search can be initiated through a "Search" button, which opens a dialog for entering the search criteria.

5. **Match Text**
   - This feature enables exact word matching or pattern matching via regular expressions across all text entries.
   - Accessible through a "Match" button, with inputs for either exact words or regex patterns.

6. **Replace Text**
   - Users can replace specified patterns or words within a text entry.
   - Accessed via a "Replace" button, where users can input both the pattern to be replaced and the replacement text.

### Additional Features

- **Upload File**
  - An extra feature that allows users to upload text files (.txt) into the system.
  - Users can add the entire content of a file to the system through a file chooser dialog, which parses and displays the text in the application.
  - This feature enhances the versatility of the tool by integrating external data seamlessly.

## User Interface

- The interface includes a main window displaying a table of text entries and a series of buttons for various operations.
- Each function (Add, Delete, Update, Search, Match, Replace, Upload File) is accessed through clearly labeled buttons, making the tool intuitive and easy to use.

## Development Environment

- **Programming Language:** Java
- **Framework:** JavaFX
- **IDE:** Any IDE that supports Java and JavaFX (e.g., IntelliJ IDEA, Eclipse)

## Installation

- Ensure Java 11 or newer is installed on your system.
- Clone the repository and import the project into your chosen IDE.
- Run `MainApp.java` or run the jar file with `java --module-path /PATH_TO_MODULES --add-modules javafx.controls,javafx.fxml -jar textProcessing.jar
`to launch the application.

## Contribution Guidelines

- Fork the repository and create your branch from `main`.
- If you add functionality or fix a bug, please add relevant unit tests.
- Ensure your code adheres to the existing style to maintain code consistency.
- Submit a pull request with a comprehensive description of changes.


