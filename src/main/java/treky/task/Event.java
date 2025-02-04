package treky.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter DATE_PRINT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Constructs an Event task object with a description, start date and end date.
     * Task status is false by default.
     *
     * @param description Description of task.
     * @param from Start date of task.
     * @param to End date of task.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        this(description, from, to, false);
    }

    /**
     * Constructs an Event task object with a description, start date, end date and status.
     *
     * @param description Description of task.
     * @param from Start date of task.
     * @param to End date of task.
     * @param isDone Status of task.
     */
    public Event(String description, LocalDate from, LocalDate to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DATE_FORMATTER) +
                " to: " + to.format(DATE_FORMATTER) + ")";
    }

    @Override
    public String toSaveString() {
        return "E | " + super.toSaveString() + " | " + from.format(DATE_PRINT_FORMATTER) +
                " | " + to.format(DATE_PRINT_FORMATTER);
    }
}
