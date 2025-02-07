package treky.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private static final String DIVIDER = "____________________________________________________________";
    private static final String WELCOME_MESSAGE = "Hello! I'm Treky\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Goodbye! Have a great day!";
    private static final String NO_MORE_INPUT_MESSAGE = "No more input available.";
    private static final String LOGO = """
              _____        _       \s
             |_   _| _ ___| |___  _\s
               | || '_/ -_) / / || |
               |_||_| \\___|_\\_\\\\_, |
                               |__/\s
            """;

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
            throw new IllegalStateException(NO_MORE_INPUT_MESSAGE);
        }
        return sc.nextLine();
    }

    /**
     * Shows the result of the command.
     *
     * @param message The result of the command.
     */
    public void showResult(String message) {
        System.out.println(DIVIDER);
        if (!message.equals("bye")) {
            System.out.println(message);
            System.out.println(DIVIDER);
        }
    }

    /**
     * Shows an error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
        System.out.println(DIVIDER);
    }

    /** Shows the welcome message. */
    public void showWelcome() {
        System.out.println(LOGO);
        System.out.println(DIVIDER);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(DIVIDER);
    }

    /** Shows the goodbye message. */
    public void showGoodbye() {
        sc.close();
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(DIVIDER);
    }
}
