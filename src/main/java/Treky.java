import java.util.Scanner;

public class Treky {
    static final String LINE_SEPARATOR = "____________________________________________________________";

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        CommandHandler commandHandler = new CommandHandler(taskManager);
        Scanner sc = new Scanner(System.in);

        hello();

        while (true) {
            System.out.print("> ");

            if (!sc.hasNextLine()) {
                break;
            }
            String input = sc.nextLine();

            System.out.println(LINE_SEPARATOR);

            if (input.equalsIgnoreCase("bye")) {
                goodbye();
                break;
            }

            if (input.isEmpty()) {
                System.out.println("You didn't enter anything!\nHow can I help you?");
            } else {
                String[] parts = input.split(" ", 2);
                String command = parts[0];
                String arguments = (parts.length > 1) ? parts[1] : "";
                commandHandler.executeCommand(command, arguments);
            }

            System.out.println(LINE_SEPARATOR);
        }
    }

    private static void goodbye() {
        System.out.println("Goodbye! Have a great day!");
        System.out.println(LINE_SEPARATOR);
    }

    private static void hello() {
        String logo = """
              _____        _       \s
             |_   _| _ ___| |___  _\s
               | || '_/ -_) / / || |
               |_||_| \\___|_\\_\\\\_, |
                               |__/\s
            """;

        System.out.println(LINE_SEPARATOR);
        System.out.println(logo);
        System.out.println("Hello! I'm Treky\nWhat can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }
}
