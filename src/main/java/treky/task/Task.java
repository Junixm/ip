package treky.task;

/**
 * Represents a task in the task list.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Task object with a description and status.
     *
     * @param description Description of task.
     * @param isDone Status of task.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "Task description cannot be null";
        assert !description.isBlank() : "Task description cannot be empty";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Sets the Task status to done or not done.
     *
     * @param isDone Status of task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the Task status.
     *
     * @return Status of task.
     */
    public boolean getDone() {
        return isDone;
    }

    /**
     * Gets the Task description.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    private String getStatusBinary() {
        return (isDone ? "1" : "0");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Converts the Task object to a string for saving to file.
     *
     * @return Description of task for saving to file.
     */
    public String toSaveString() {
        return getStatusBinary() + " | " + description;
    }
}
