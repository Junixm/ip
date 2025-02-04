package treky.command;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;

import treky.TrekyException;
import treky.task.Task;
import treky.task.Todo;
import treky.task.Deadline;
import treky.task.Event;

public class Storage {
    private final Path filePath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath Path to the file to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Initializes the storage file if it does not exist.
     *
     * @throws TrekyException If an error occurs while creating the file.
     */
    public void initStorage() throws TrekyException {
        File file = filePath.toFile();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new TrekyException(e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return List of tasks loaded from the storage file.
     * @throws TrekyException If an error occurs while loading tasks from the file.
     */
    public List<Task> loadTasks() throws TrekyException {
        List<Task> taskList = new ArrayList<>();
        try (Scanner sc = new Scanner(filePath)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = getTaskFromLine(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            throw new TrekyException("Failed to load tasks from file.");
        } catch (IllegalArgumentException e) {
            throw new TrekyException("File is corrupted: " + e.getMessage());
        }
        return taskList;
    }

    private Task getTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format.");
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];
        return switch (type) {
            case "T" -> {
                if (parts.length != 3) {
                    throw new IllegalArgumentException("Invalid todo format.");
                }
                yield new Todo(description, isDone);
            }
            case "D" -> {
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Invalid deadline format.");
                }
                try {
                    LocalDate date = LocalDate.parse(parts[3]);
                    yield new Deadline(description, date, isDone);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format.");
                }
            }
            case "E" -> {
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Invalid event format.");
                }
                try {
                    LocalDate dateFrom = LocalDate.parse(parts[3]);
                    LocalDate dateTo = LocalDate.parse(parts[4]);
                    yield new Event(description, dateFrom, dateTo, isDone);
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException("Invalid date format.");
                }
            }
            default -> throw new IllegalArgumentException("Unknown task type.");
        };
    }

    /**
     * Adds a line to the storage file.
     *
     * @param line Line to be added to the storage file.
     * @throws TrekyException If an error occurs while writing to the file.
     */
    public void addLine(String line) throws TrekyException {
        try (FileWriter writer = new FileWriter(filePath.toString(), true)) {
            writer.write(line + System.lineSeparator());
        } catch (IOException e) {
            throw new TrekyException("Failed to write to file.");
        }
    }

    /**
     * Updates the storage file with the specified list of tasks.
     *
     * @param taskList List of tasks to be written to the storage file.
     * @throws TrekyException If an error occurs while writing to the file.
     */
    public void updateFile(List<Task> taskList) throws TrekyException {
        try {
            new FileWriter(filePath.toString(), false).close();
            for (Task task : taskList) {
                addLine(task.toSaveString());
            }
        } catch (IOException e) {
            throw new TrekyException("Failed to write to file.");
        }
    }
}
