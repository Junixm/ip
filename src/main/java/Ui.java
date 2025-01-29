import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() throws TrekyException {
        System.out.print("> ");
        if (!sc.hasNextLine()) {
            throw new TrekyException("No input detected.");
        }
        return sc.nextLine();
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showGoodbye() {
        sc.close();
        System.out.println("Goodbye! Have a great day!");
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

        showLine();
        System.out.println(logo);
        System.out.println("Hello! I'm Treky\nWhat can I do for you?");
    }
}
