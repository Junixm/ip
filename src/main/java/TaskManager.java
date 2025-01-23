import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskManager {

    private final ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    protected void executeTask(String input) {
        String command = input.split(" ")[0];
        try {
            switch (command) {
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(input);
                    break;
                case "unmark":
                    unmarkTask(input);
                    break;
                case "todo":
                    addTodoTask(input);
                    break;
                case "deadline":
                    addDeadlineTask(input);
                    break;
                case "event":
                    addEventTask(input);
                    break;
                default:
                    System.out.println("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void addEventTask(String input) {
        try {
            String description = input.split(" ", 2)[1];
            String[] event = description.split(" /from | /to ");
            Task task = new Event(event[0], event[1], event[2]);
            addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Please provide a valid event description!");
        }
    }

    private void addDeadlineTask(String input) {
        try {
            String description = input.split(" ", 2)[1];
            String[] deadline = description.split(" /by ");
            Task task = new Deadline(deadline[0], deadline[1]);
            addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Please provide a valid deadline description!");
        }
    }

    private void addTodoTask(String input) {
        try {
            String description = input.split(" ", 2)[1];
            Task task = new Todo(description);
            addTask(task);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Please provide a valid todo description!");
        }
    }

    private Task getTask(String input) {
        try {
            int index = Integer.parseInt(input.split(" ")[1]);
            return tasks.get(index - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Please provide a valid task number!");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Task number must be a number!");
        }
    }

    private void unmarkTask(String input){
        Task task = getTask(input);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n  " + task);
    }

    private void markTask(String input){
        Task task = getTask(input);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    private void listTasks() {
        String list = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .reduce((x, y) -> x + "\n" + y)
                .orElse("No tasks added yet!");

        System.out.println("Here are the tasks in your list:\n" + list);
    }
}