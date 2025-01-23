import java.util.ArrayList;
import java.util.stream.IntStream;

public class TaskManager {

    private final ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    protected void listTasks() {
        String list = IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .reduce((x, y) -> x + "\n" + y)
                .orElse("No tasks added yet!");

        System.out.println(list);
    }
    
    protected void addTask(String description) {
        Task task = new Task(description);
        tasks.add(task);
        System.out.println("added: " + task);
    }
}
