import java.util.Scanner;

public class Treky {

    private final static String lineSpacer = "____________________________________________________________";
    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            hello();
            while (true) {
                String input = sc.nextLine();
                System.out.println(lineSpacer);
                if (input.equals("bye")) {
                    goodbye();
                    break;
                } else {
                    echo(input);
                }
            }
        }

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

    private static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(lineSpacer);
    }

    private static void echo(String input) {
        System.out.println(input);
        System.out.println(lineSpacer);
    }
}
