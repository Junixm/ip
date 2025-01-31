package treky.task;

public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean getDone() {
        return isDone;
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

    public String toSaveString() {
        return getStatusBinary() + " | " + description;
    }
}
