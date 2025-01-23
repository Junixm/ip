import java.util.Scanner;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Treky {

    private final static String lineSpacer = "____________________________________________________________";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            hello();
            while (true) {
                String input = sc.nextLine();
                System.out.println(lineSpacer);
                switch (input) {
                    case "bye":
                        goodbye();
                        return;
                    default:
                        addTask(input);
                        break;
                }
            }
        }

    }

    private static void addTask(String input) {
        Task task = new Task(input);
        tasks.add(task);
        echo("added: " + task);
    }

    private static void echo(String input) {
        System.out.println(input);
        System.out.println(lineSpacer);
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
