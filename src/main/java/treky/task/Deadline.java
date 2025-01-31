package treky.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDate date;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    private static final DateTimeFormatter DATE_PRINT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Deadline(String description, LocalDate date) {
        this(description, date, false);
    }

    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
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