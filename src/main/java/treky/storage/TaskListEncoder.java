package treky.storage;

import java.util.List;

import treky.task.Task;
import treky.task.TaskList;

/**
 * Encodes the {@code TaskList} object into a data file for storage.
 * Adapted from AddressBook2.
 */
public class TaskListEncoder {

    /**
     * Encodes the {@code TaskList} object into a list of strings for storage.
     *
     * @param taskList The task list to be encoded.
     * @return The list of strings representing the task list.
     */
    public static List<String> encode(TaskList taskList) {
        return taskList.getTaskList()
                .stream()
                .map(Task::toSaveString)
                .toList();
    }
}
