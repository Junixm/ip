import java.util.Scanner;

public class Treky {

    private final static String lineSpacer = "____________________________________________________________";

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        try (Scanner sc = new Scanner(System.in)) {
            hello();
            while (true) {
                String input = sc.nextLine();
                System.out.println(lineSpacer);

                switch (input) {
                    case "bye":
                        goodbye();
                        return;
                    case "":
                        System.out.println("You didn't enter anything!\nHow can I help you?");
                        break;
                    default:
                        taskManager.executeTask(input);
                        break;
                }

                System.out.println(lineSpacer);
            }
        }
    }

    private static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineSpacer);
    }

    private static void hello() {
        String logo = """
              _____        _       \s
             |_   _| _ ___| |___  _\s
               | || '_/ -_) / / || |
               |_||_| \\___|_\\_\\\\_, |
                               |__/\s
            """;

        System.out.println(lineSpacer);
        System.out.println(logo);
        System.out.println("Hello! I'm Treky\nWhat can I do for you?");
        System.out.println(lineSpacer);
    }
}
