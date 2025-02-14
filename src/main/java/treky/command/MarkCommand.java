package treky.command;

import treky.exception.TrekyException;
import treky.task.TaskList;
import treky.task.Task;

/**
 * Marks a task as done or not done.
 */
public class MarkCommand implements Executable {

    private final int index;
    private final TaskList taskList;
    private final boolean isDone;
    private static final String FORMAT_MESSAGE = "Format: mark <task number>\n    or: unmark <task number>";
    private static final String NO_NUMBER_MESSAGE = "Please enter a task number.\n";
    private static final String OUT_OF_BOUND_MESSAGE = "Please enter a valid task number.\n";
    private static final String NOT_A_NUMBER_MESSAGE = "Task number must be a number!\n";
    private static final String SUCCESS_MARK_MESSAGE = "Nice! I've marked this task as done:\n  %s";
    private static final String SUCCESS_UNMARK_MESSAGE = "OK, I've marked this task as not done yet:\n  %s";

    /**
     * Constructs a MarkCommand object with the specified index, TaskList, and isDone.
     *
     * @param description The index of the task to be marked.
     * @param taskList The TaskList object to manage tasks.
     * @param isDone The boolean value to indicate if the task is done.
     * @throws TrekyException If the description is empty, not a number, or out of bounds.
     */
    public MarkCommand(String description, TaskList taskList, boolean isDone) throws TrekyException {
        assert description != null : "Description cannot be null";
        assert taskList != null : "TaskList cannot be null";

        if (description.isEmpty()) {
            throw new TrekyException(NO_NUMBER_MESSAGE + FORMAT_MESSAGE);
        }
        try {
            this.index = Integer.parseInt(description) - 1;
        } catch (NumberFormatException e) {
            throw new TrekyException(NOT_A_NUMBER_MESSAGE + FORMAT_MESSAGE);
        }
        this.taskList = taskList;
        this.isDone = isDone;
    }

    @Override
    public String execute() throws TrekyException {
        try {
            if (isDone) {
                Task task = taskList.markTask(index);
                return String.format(SUCCESS_MARK_MESSAGE, task);
            }
            Task task = taskList.unmarkTask(index);
            return String.format(SUCCESS_UNMARK_MESSAGE, task);
        } catch (IndexOutOfBoundsException e) {
            throw new TrekyException(OUT_OF_BOUND_MESSAGE + FORMAT_MESSAGE);
        }
    }
}
