package treky;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInput() throws IllegalStateException {
        System.out.print("> ");
        if (!sc.hasNextLine()) {
            throw new IllegalStateException("No more input available.");
        }
        return sc.nextLine();
    }

    public void showResult(String message) {
        showLine();
        if (!message.equals("bye")) {
            System.out.println(message);
            showLine();
        }
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
        showLine();
    }

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

    public void showGoodbye() {
        sc.close();
        System.out.println("Goodbye! Have a great day!");
        showLine();
    }

    private void showLine() {
        System.out.println("____________________________________________________________");
    }
}
