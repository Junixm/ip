package treky.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setMark(boolean isDone) {
        this.isDone = isDone;
    }

    private String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toFileString() {
        return (isDone ? "1" : "0") + " | " + description;
    }
}
