package com.example.lunasshell;

import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
import java.time.LocalTime;

import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGeneratorFactory;

public class Main extends Application {
    BufferedWriter out = new BufferedWriter(new FileWriter("/Users/paytonkeultjes/Desktop/blankslate/LunasShell/src/main/resources/com/example/lunasshell/log.txt"));
    String osName = System.getProperty("os.name").toLowerCase();

    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {

        // System.out.println(osName);
        stage.setOpacity(0.6);
        BorderPane root = new BorderPane();


        TextArea terminalOutput = new TextArea();
        terminalOutput.getStyleClass().add("terminal-output");

        terminalOutput.setEditable(false);
        terminalOutput.setWrapText(true);

        root.setLeft(terminalOutput);
        TextField userInput = new TextField();

        userInput.setOnAction(event -> {

                    String command = userInput.getText();
                    terminalOutput.appendText("\n$ " + command);
                    try {
                        userInputFilter(command, terminalOutput, userInput);
                    } catch (InterruptedException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }


        );


        root.getStyleClass().add("root");

        System.out.println("CSS File URL: " + getClass().getResource("style.css"));
        VBox layout = new VBox(5, terminalOutput, userInput);
        VBox.setVgrow(terminalOutput, Priority.ALWAYS);
        layout.getStyleClass().add("custom-vbox");
        userInput.getStyleClass().add("user-input");

        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("style.css")).toExternalForm());


        terminalOutput.appendText("Last  Login:  Sun  Mar  08/12/2093  on " + System.getProperty("os.name"));
        terminalOutput.appendText("\nTo show a working list of commands:  --help ");
        terminalOutput.appendText("\nThis is a home baked faux terminal designed to handle user requests within mac os based systems --help ");

        stage.setScene(scene);
        stage.setTitle("LunasShell");
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }


    public void userInputFilter(String command, TextArea terminalOutput, TextField userInput) throws InterruptedException, IOException {
        try {
            out.write("User: GUEST -  " + command);
            out.newLine();
            out.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (command.substring(0, 2).equalsIgnoreCase("--")) {
            checkForCommand(command, terminalOutput);
        } else if (command.equalsIgnoreCase("clear")) {

            terminalOutput.clear();
            LocalTime currentTime = LocalTime.now();
            System.out.println("Current time: " + currentTime);
            terminalOutput.appendText("Last Login: NULL\nLunar Station #443\nUser: Unauthorized User\nCurrent Period: " + currentTime);

        } else {
            terminalOutput.appendText("\nzsh:  command not found");
        }
        userInput.clear();
    }

    private void checkForCommand(String command, TextArea terminalOutput) throws InterruptedException, IOException {
        if (command.equalsIgnoreCase("--help")) {
            terminalOutput.appendText("\nzsh:  start - Launches the application in interactive mode.\n" +
                    "--stop - Gracefully shuts down the application.\n" +
                    "--restart - Restarts the application, reloading all configurations.\n" +
                    "--status - Displays the current status of the application.\n" +
                    "--config - Opens the configuration settings for the application.\n" +
                    "--help - Displays this help list.\n" +
                    "--version - Shows the current version of the application.\n" +
                    "--log - Displays application logs.\n" +
                    "--update - Checks for and applies updates.\n" +
                    "--connect <server_address> - Connects to a remote server.\n" +
                    "--disconnect - Disconnects from the currently connected server.\n" +
                    "--ping <address> - Tests connectivity with an IP address or domain.\n" +
                    "--encrypt <file_path/message> - Encrypts a file or message.\n" +
                    "--decrypt <file_path/message> - Decrypts a file or message.");

        } else if (command.equalsIgnoreCase("--stop")) {
            System.out.println("zsh: The program has initiated a complete shutdown");
            System.exit(0);

        } else if (command.equalsIgnoreCase("--restart")) {
            terminalOutput.appendText("zsh: This command is currently unsupported, Please try again later.");
        } else if (command.equalsIgnoreCase("--status")) {
            terminalOutput.appendText("\nzsh: Checking System Status");
            LocalTime currentTime = LocalTime.now();
            terminalOutput.appendText("Current Period: " + currentTime);
            terminalOutput.appendText("\nzsh: All systems are behaving correctly");

        } else if (command.equalsIgnoreCase("--config")) {
            terminalOutput.appendText("\nzsh: There are no configuration options at this time");
        } else if (command.equalsIgnoreCase("--version")) {

            terminalOutput.appendText("LunasShell\nVersion - 1.0");

        } else if (command.equalsIgnoreCase("--log")) {
            System.out.println("Attempting to log...");
            String filePath = "/Users/paytonkeultjes/Desktop/blankslate/LunasShell/src/main/resources/com/example/lunasshell/log.txt";
            //TODO: ADD DYNAMIC FILE PATHS HERE
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                Scanner infile = new Scanner(reader);
                String line;
                while (infile.hasNext()) {
                    line = infile.nextLine();
                    terminalOutput.appendText("\n" + line);
                }

            } catch (IOException e) {
                throw new RuntimeException("There was an error loading the log");
            }
        } else if (command.equalsIgnoreCase("--update")) {
            terminalOutput.appendText("\nzsh: There are no updates at this time");
        } else if (command.substring(0, 6).equalsIgnoreCase("--ping")) {
            Networking network = new Networking(command.substring(6), terminalOutput);
            network.pingIpAddress();
        } else if (command.equalsIgnoreCase("--disconnect")) {
            terminalOutput.appendText("\nzsh: There are no targets to disconnect from at this time");
        } else if (command.equalsIgnoreCase("--encrypt")) {
            terminalOutput.appendText("\nzsh: Please enter the text you would like to encrypt");

        } else if (command.equalsIgnoreCase("--passwordgen")) {
            String password;
            final String ALPHABET = "abcd"


        }


    }

}


