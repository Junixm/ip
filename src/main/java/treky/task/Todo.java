package treky.task;

/**
 * Represents a Todo task in the task list.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task object with a description.
     * Task status is false by default.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        this(description, false);
    }

    /**
     * Constructs a Todo task object with a description and status.
     *
     * @param description Description of task.
     * @param isDone Status of task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toSaveString() {
        return "T | " + super.toSaveString();
    }
}
