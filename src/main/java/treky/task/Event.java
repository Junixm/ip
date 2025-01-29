package treky.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter DATE_PRINT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Event(String description, LocalDate from, LocalDate to) {
        this(description, from, to, false);
    }

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
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + from.format(DATE_PRINT_FORMATTER) +
                " | " + to.format(DATE_PRINT_FORMATTER);
    }
}
