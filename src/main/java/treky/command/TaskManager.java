package treky.command;

import treky.task.Task;
import treky.task.Todo;
import treky.task.Deadline;
import treky.task.Event;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private final ArrayList<Task> taskList;

    public TaskManager() {
        this(new ArrayList<>());
    }

    public TaskManager(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public int getTaskListSize() {
        return taskList.size();
    }

    private Task getTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Please provide a valid task number!");
        }
        return taskList.get(index);
    }

    public Task addTodoTask(String description) {
        Task task = new Todo(description);
        taskList.add(task);
        return task;
    }

    public Task addDeadlineTask(String description, LocalDate by) {
        Task task = new Deadline(description, by);
        taskList.add(task);
        return task;
    }

    public Task addEventTask(String description, LocalDate from, LocalDate to) {
        Task task = new Event(description, from, to);
        taskList.add(task);
        return task;
    }

    public Task deleteTask(int number) throws IndexOutOfBoundsException {
        Task task = getTask(number - 1);
        taskList.remove(task);
        return task;
    }

    public Task markTask(int number, boolean isDone) throws IndexOutOfBoundsException {
        Task task = getTask(number - 1);
        task.setDone(isDone);
        return task;
    }
}