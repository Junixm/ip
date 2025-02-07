package treky.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads the input from the user.
     *
     * @return The input from the user.
     * @throws IllegalStateException If there is no more input available.
     */
    public String readInput() throws IllegalStateException {
        System.out.print("> ");
        if (!sc.hasNextLine()) {
            throw new IllegalStateException("No more input available.");
        }
        return sc.nextLine();
    }

    /**
     * Shows the result of the command.
     *
     * @param message The result of the command.
     */
    public void showResult(String message) {
        showLine();
        if (!message.equals("bye")) {
            System.out.println(message);
            showLine();
        }
    }

    /**
     * Shows an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
        showLine();
    }

    /** Shows the welcome message. */
    public void showWelcome() {
        String logo = """
              _____        _       \s
             |_   _| _ ___| |___  _\s
               | || '_/ -_) / / || |
               |_||_| \\___|_\\_\\\\_, |
                               |__/\s
            """;
        System.out.println(logo);
        showLine();
        System.out.println("Hello! I'm Treky\nWhat can I do for you?");
        showLine();
    }

    /** Shows the goodbye message. */
    public void showGoodbye() {
        sc.close();
        System.out.println("Goodbye! Have a great day!");
        showLine();
    }

    private void showLine() {
        System.out.println("____________________________________________________________");
    }
}
