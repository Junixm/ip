package treky.command;

import java.util.List;

import treky.exception.TrekyException;
import treky.task.Task;
import treky.task.TaskList;

/**
 * Represents a command to find tasks with a keyword.
 */
public class FindCommand implements Executable {

    private final String keyword;
    private final TaskList taskList;
    private static final String FORMAT_MESSAGE = "Format: find <keyword>";
    private static final String SUCCESS_MESSAGE = "Here are the matching tasks in your list:";
    private static final String NO_MATCH_MESSAGE = "No task found with this keyword: %s";

    /**
     * Constructs a FindCommand object with the specified keyword and task list.
     *
     * @param description The keyword to search for.
     * @param taskList The task list to search in.
     * @throws TrekyException If the keyword is empty.
     */
    public FindCommand(String description, TaskList taskList) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException(FORMAT_MESSAGE);
        }
        this.keyword = description;
        this.taskList = taskList;
    }

    @Override
    public String execute() throws TrekyException {
        List<Task> tasks = taskList.findTasks(keyword);
        if (tasks.isEmpty()) {
            return String.format(NO_MATCH_MESSAGE, keyword);
        }
        StringBuilder result = new StringBuilder(SUCCESS_MESSAGE);
        for (int i = 0; i < tasks.size(); i++) {
            result.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return result.toString();
    }
}
