package treky.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import treky.task.Task;
import treky.task.Todo;
import treky.task.Deadline;
import treky.task.Event;

public class TaskManager {

    private final ArrayList<Task> taskList;

    /** Constructs a TaskManager object with an empty task list. */
    public TaskManager() {
        this(new ArrayList<>());
    }

    /**
     * Constructs a TaskManager object with the specified task list.
     *
     * @param taskList The task list to be used.
     */
    public TaskManager(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /** Returns the task list. */
    public List<Task> getTaskList() {
        return taskList;
    }

    /** Returns the number of tasks in the task list. */
    public int getTaskListSize() {
        return taskList.size();
    }

    private Task getTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            throw new IndexOutOfBoundsException("Please provide a valid task number!");
        }
        return taskList.get(index);
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param description The description of the task.
     * @return Todo task that was created.
     **/
    public Task addTodoTask(String description) {
        Task task = new Todo(description);
        taskList.add(task);
        return task;
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     * @return Deadline task that was created.
     **/
    public Task addDeadlineTask(String description, LocalDate by) {
        Task task = new Deadline(description, by);
        taskList.add(task);
        return task;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param description The description of the task.
     * @param from The start date of the event.
     * @param to The end date of the event.
     * @return Event task that was created.
     **/
    public Task addEventTask(String description, LocalDate from, LocalDate to) {
        Task task = new Event(description, from, to);
        taskList.add(task);
        return task;
    }

    /**
     * Deletes a task from the task list.
     *
     * @param number The number of the task listed to be deleted.
     * @return Task that was deleted.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     **/
    public Task deleteTask(int number) throws IndexOutOfBoundsException {
        Task task = getTask(number - 1);
        taskList.remove(task);
        return task;
    }

    /**
     * Marks a task as done or undone.
     *
     * @param number The number of the task listed to be marked.
     * @param isDone The status of the task.
     * @return Task that was marked.
     * @throws IndexOutOfBoundsException If the task number is invalid.
     **/
    public Task markTask(int number, boolean isDone) throws IndexOutOfBoundsException {
        Task task = getTask(number - 1);
        task.setDone(isDone);
        return task;
    }
}