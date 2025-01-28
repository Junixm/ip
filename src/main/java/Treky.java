import java.util.Scanner;

public class Treky {
    static final String LINE_SEPARATOR = "____________________________________________________________";
    private CommandHandler commandHandler;

    public Treky(String filePath) {
        try {
            Storage storage = new Storage(filePath);
            TaskManager taskManager = new TaskManager(storage.loadTasks());
            this.commandHandler = new CommandHandler(taskManager, storage);
        } catch (TrekyException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        hello();
        while (true) {
            System.out.print("> ");
            if (!sc.hasNextLine()) {
                goodbye();
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
        sc.close();
    }

    private void goodbye() {
        System.out.println("Goodbye! Have a great day!");
        System.out.println(LINE_SEPARATOR);
    }

    private void hello() {
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

    public static void main(String[] args) {
        new Treky("./data/tasks.txt").run();
    }
}
