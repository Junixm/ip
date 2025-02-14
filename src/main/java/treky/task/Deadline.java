package treky.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the task list.
 */
public class Deadline extends Task {
    private final LocalDate date;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter DATE_PRINT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs a Deadline task object with a description and date.
     * Task status is false by default.
     *
     * @param description Description of task.
     * @param date        Date of task.
     */
    public Deadline(String description, LocalDate date) {
        this(description, date, false);
    }

    /**
     * Constructs a Deadline task object with a description, date and status.
     *
     * @param description Description of task.
     * @param date        Date of task.
     * @param isDone      Status of task.
     */
    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        assert date != null : "Deadline date cannot be null";
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DATE_FORMATTER) + ")";
    }

    @Override
    public String toSaveString() {
        return "D | " + super.toSaveString() + " | " + date.format(DATE_PRINT_FORMATTER);
    }
}