import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskManager {

    private final ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    protected void executeTask(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        try {
            switch (command) {
                case "list":
                    listTasks();
                    break;
                case "mark":
                    markTask(Integer.parseInt(words[1]));
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(words[1]));
                    break;
                default:
                    addTask(input);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid task number!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number!");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private void markTask(int index){
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid task number!");
        }
        Task task = tasks.get(index - 1);
        task.mark();
        System.out.println("Nice! I've marked this task as done:\n  " + task);
    }

    private void unmarkTask(int index) {
        if (!isValidIndex(index)) {
            throw new IndexOutOfBoundsException("Invalid task number!");
        }
        Task task = tasks.get(index - 1);
        task.unmark();
        System.out.println("OK, I've marked this task as not done yet:\n  " + task);
    }

    private boolean isValidIndex(int index) {
        return index > 0 && index <= tasks.size();
    }

    private void listTasks() {
        String list = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .reduce((x, y) -> x + "\n" + y)
                .orElse("No tasks added yet!");

        System.out.println(list);
    }

    private void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("added: " + task);
    }
}
