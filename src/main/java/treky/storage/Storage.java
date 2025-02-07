package treky.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;

import treky.exception.TrekyFatalException;
import treky.task.TaskList;

/**
 * Represents the file used to store tasks data.
 */
public class Storage {
    // Adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/StorageFile.java
    public static final String DEFAULT_FILE_PATH = "./data/tasks.txt";

    private final Path filePath;

    public Storage() throws TrekyFatalException {
        this(DEFAULT_FILE_PATH);
    }

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filePath Path to the file to store tasks.
     */
    public Storage(String filePath) throws TrekyFatalException {
        this.filePath = Paths.get(filePath);
        try {
            Files.createDirectories(this.filePath.getParent());
        } catch (IOException e) {
            throw new TrekyFatalException("Error creating directories for file: " + filePath);
        }
    }

    /**
     * Saves the {@code taskList} data to the storage file.
     *
     * @param taskList The task list to be saved.
     * @throws TrekyFatalException If an error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws TrekyFatalException {
        try {
            List<String> encodedTaskList = TaskListEncoder.encode(taskList);
            Files.write(filePath, encodedTaskList);
        } catch (IOException e) {
            throw new TrekyFatalException("Error writing to file: " + filePath);
        }
    }

    /**
     * Loads the {@code TaskList} data from the storage file.
     * Returns an empty {@code TaskList} if the file does not exist.
     *
     * @return The task list loaded from the storage file.
     * @throws TrekyFatalException If an error occurs while reading the file.
     */
    public TaskList load() throws TrekyFatalException {
        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            return new TaskList();
        }

        try {
            return TaskListDecoder.decode(Files.readAllLines(filePath));
        } catch (IOException e) {
            throw new TrekyFatalException("Error reading file: " + filePath);
        } catch (IllegalArgumentException e) {
            throw new TrekyFatalException("File is corrupted: " + e.getMessage());
        }
    }

    public String getPath() {
        return filePath.toString();
    }
}