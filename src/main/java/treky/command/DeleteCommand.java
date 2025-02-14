package treky.command;

import treky.exception.TrekyException;
import treky.task.TaskList;
import treky.task.Task;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand implements Executable {

    private final int index;
    private final TaskList taskList;
    private static final String FORMAT_MESSAGE = "Format: delete <task number>";
    private static final String OUT_OF_BOUND_MESSAGE = "Please enter a valid task number.\n";
    private static final String NOT_A_NUMBER_MESSAGE = "Task number must be a number!\n";
    private static final String SUCCESS_MESSAGE = """
            Noted. I've removed this task:
              %s
            Now you have %d tasks in the list.""";

    /**
     * Constructs a DeleteCommand object with the specified index and TaskList.
     *
     * @param description The index of the task to be deleted.
     * @param taskList The TaskList object to manage tasks.
     * @throws TrekyException If the description is empty, not a number, or out of bounds.
     */
    public DeleteCommand(String description, TaskList taskList) throws TrekyException {
        assert description != null : "Description cannot be null";
        assert taskList != null : "TaskList cannot be null";

        if (description.isEmpty()) {
            throw new TrekyException(FORMAT_MESSAGE);
        }

        try {
            this.index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new TrekyException(NOT_A_NUMBER_MESSAGE + FORMAT_MESSAGE);
        }

        this.taskList = taskList;
    }

    @Override
    public String execute() throws TrekyException {
        try {
            Task task = taskList.deleteTask(index);
            return String.format(SUCCESS_MESSAGE, task, taskList.getTaskListSize());
        } catch (IndexOutOfBoundsException e) {
            throw new TrekyException(OUT_OF_BOUND_MESSAGE + FORMAT_MESSAGE);
        }
    }
}
