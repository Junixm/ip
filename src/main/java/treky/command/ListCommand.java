package treky.command;

import treky.task.TaskList;

/**
 * Lists all tasks in the task list.
 */
public class ListCommand implements Executable {
    private final TaskList taskList;
    private static final String EMPTY_LIST_MESSAGE = "The list is empty.";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";

    /**
     * Constructs a ListCommand object with the specified TaskList.
     *
     * @param taskList The TaskList object to manage tasks.
     */
    public ListCommand(TaskList taskList) {
        assert taskList != null : "TaskList cannot be null";

        this.taskList = taskList;
    }

    @Override
    public String execute() {
        if (taskList.getTaskListSize() == 0) {
            return EMPTY_LIST_MESSAGE;
        }

        StringBuilder sb = new StringBuilder(LIST_MESSAGE);
        for (int i = 0; i < taskList.getTaskListSize(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(taskList.getTask(i));
        }

        return sb.toString();
    }
}
