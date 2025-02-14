package treky.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> taskList;

    /**
     * Constructs a TaskList object with an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object with the specified task list.
     *
     * @param taskList The task list to be used.
     */
    public TaskList(List<Task> taskList) {
        assert taskList != null : "Task list cannot be null";
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Returns the task list.
     *
     * @return The task list.
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskListSize() {
        return taskList.size();
    }

    /**
     * Gets the task at the specified index.
     * @param index The index of the task.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        return taskList.get(index);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        taskList.add(task);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        return taskList.remove(index);
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The task that was marked as done.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task markTask(int index) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        assert task != null : "Task cannot be null";
        task.setDone(true);
        return task;
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @return The task that was marked as undone.
     * @throws IndexOutOfBoundsException If the index is out of bounds.
     */
    public Task unmarkTask(int index) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        assert task != null : "Task cannot be null";
        task.setDone(false);
        return task;
    }

    /**
     * Finds tasks that contain the specified keyword.
     *
     * @param keyword The keyword to search for.
     * @return List of tasks that contain the keyword.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(task);
            }
        }
        return tasks;
    }
}
