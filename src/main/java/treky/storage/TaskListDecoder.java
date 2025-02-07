package treky.storage;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import treky.task.TaskList;
import treky.task.Task;
import treky.task.Todo;
import treky.task.Deadline;
import treky.task.Event;

/**
 * Decodes the data from the storage file into a {@code TaskList} object.
 */
public class TaskListDecoder {
    // Adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
    /**
     * Decodes the data from the storage file into a {@code TaskList} object.
     *
     * @param data The data to be decoded.
     * @return The {@code TaskList} object decoded from the data.
     * @throws IllegalArgumentException If the data is corrupted or in an invalid format.
     */
    public static TaskList decode(List<String> data) throws IllegalArgumentException{
        TaskList taskList = new TaskList();
        for (String line : data) {
            Task task = decodeTask(line);
            taskList.addTask(task);
        }
        return taskList;
    }

    private static Task decodeTask(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format in file");
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        return switch (type) {
            case "T" -> prepareTodoTask(parts, isDone, description);
            case "D" -> prepareDeadlineTask(parts, isDone, description);
            case "E" -> prepareEventTask(parts, isDone, description);
            default -> throw new IllegalArgumentException("Invalid task type in file");
        };
    }

    private static Task prepareTodoTask(String[] parts, boolean isDone, String description) {
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid todo format in file");
        }
        return new Todo(description, isDone);
    }

    private static Task prepareDeadlineTask(String[] parts, boolean isDone, String description) {
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid deadline format in file");
        }
        try {
            LocalDate date = LocalDate.parse(parts[3]);
            return new Deadline(description, date, isDone);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid deadline date format in file");
        }
    }

    private static Task prepareEventTask(String[] parts, boolean isDone, String description) {
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid event format in file");
        }
        try {
            LocalDate dateFrom = LocalDate.parse(parts[3]);
            LocalDate dateTo = LocalDate.parse(parts[4]);
            return new Event(description, dateFrom, dateTo, isDone);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid event date format in file");
        }
    }
}
