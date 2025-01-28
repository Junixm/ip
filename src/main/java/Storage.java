import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {
    private final Path filePath;

    public Storage(String filePath) throws TrekyException {
        this.filePath = Paths.get(filePath);
        createFileIfNotExists();
    }

    private void createFileIfNotExists() throws TrekyException {
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

    public ArrayList<Task> loadTasks() throws TrekyException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner sc = new Scanner(filePath)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = getTaskFromLine(line);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new TrekyException("Failed to load tasks from file.");
        } catch (IllegalArgumentException e) {
            throw new TrekyException("File is corrupted: " + e.getMessage());
        }
        return tasks;
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
                yield new Deadline(description, parts[3], isDone);
            }
            case "E" -> {
                if (parts.length != 5) {
                    throw new IllegalArgumentException("Invalid event format.");
                }
                yield new Event(description, parts[3], parts[4], isDone);
            }
            default -> throw new IllegalArgumentException("Unknown task type.");
        };
    }

    public void addLine(String line) throws TrekyException {
        try (FileWriter writer = new FileWriter(filePath.toString(), true)) {
            writer.write(line + System.lineSeparator());
        } catch (IOException e) {
            throw new TrekyException("Failed to write to file.");
        }
    }

    public void updateFile(ArrayList<Task> tasks) throws TrekyException {
        try {
            new FileWriter(filePath.toString(), false).close();
            for (Task task : tasks) {
                addLine(task.toFileString());
            }
        } catch (IOException e) {
            throw new TrekyException("Failed to write to file.");
        }
    }
}
