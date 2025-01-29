import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskManager {

    private final ArrayList<Task> tasks;

    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    protected ArrayList<Task> getTasks() {
        return tasks;
    }

    protected void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    protected void listTasks() {
        String list = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .reduce((x, y) -> x + "\n" + y)
                .orElse("No tasks added yet!");

        System.out.println("Here are the tasks in your list:\n" + list);
    }

    protected void markTask(int index, boolean isDone) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(index);
        task.setMark(isDone);
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:\n  " + task);
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n  " + task);
        }
    }

    protected void deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException();
        }
        Task task = tasks.get(index);
        tasks.remove(task);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}