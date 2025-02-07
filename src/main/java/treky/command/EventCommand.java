package treky.command;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import treky.task.TaskList;
import treky.task.Task;
import treky.task.Event;
import treky.exception.TrekyException;

/**
 * Adds an event task to the task list.
 */
public class EventCommand implements Executable{
    private final Task task;
    private final TaskList taskList;
    private static final String FORMAT_MESSAGE = "Format: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>";
    private static final String SUCCESS_MESSAGE = """
            Got it. I've added this task:
              %s
            Now you have %d tasks in the list.""";

    /**
     * Constructs a DeadlineCommand object with the specified description and TaskList.
     * Description must contain the date of the deadline task in this format:
     * "description /from yyyy-MM-dd /to yyyy-MM-dd".
     *
     * @param description The description with date of the deadline task.
     * @param taskList The TaskList object to manage tasks.
     * @throws TrekyException If the description or date is empty or the date is in an invalid format.
     */
    public EventCommand(String description, TaskList taskList) throws TrekyException {
        String[] parts = description.split("/from|/to");
        if (parts.length != 3) {
            throw new TrekyException(FORMAT_MESSAGE);
        }
        String trimDescription = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        if (trimDescription.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new TrekyException(FORMAT_MESSAGE);
        }
        try {
            this.task = new Event(trimDescription, LocalDate.parse(from), LocalDate.parse(to));
            this.taskList = taskList;
        } catch (DateTimeParseException e) {
            throw new TrekyException(FORMAT_MESSAGE);
        }
    }

    @Override
    public String execute() {
        taskList.addTask(task);
        return String.format(SUCCESS_MESSAGE, task, taskList.getTaskListSize());
    }
}
