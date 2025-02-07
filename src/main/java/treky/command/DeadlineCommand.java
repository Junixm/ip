package treky.command;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import treky.task.TaskList;
import treky.task.Task;
import treky.task.Deadline;
import treky.exception.TrekyException;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand implements Executable {
    private final Task task;
    private final TaskList taskList;
    private static final String FORMAT_MESSAGE = "Format: deadline <description> /by <yyyy-MM-dd>";
    private static final String SUCCESS_MESSAGE = """
            Got it. I've added this task:
              %s
            Now you have %d tasks in the list.""";

    /**
     * Constructs a DeadlineCommand object with the specified description and TaskList.
     * Description must contain the date of the deadline task in this format: description /by yyyy-MM-dd.
     * 
     * @param description The description with date of the deadline task.
     * @param taskList The TaskList object to manage tasks.
     * @throws TrekyException If the description or date is empty or the date is in an invalid format.
     */
    public DeadlineCommand(String description, TaskList taskList) throws TrekyException {
        String[] parts = description.split("/by");
        if (parts.length != 2) {
            throw new TrekyException(FORMAT_MESSAGE);
        }
        String trimDescription = parts[0].trim();
        String by = parts[1].trim();
        if (trimDescription.isEmpty() || by.isEmpty()) {
            throw new TrekyException(FORMAT_MESSAGE);
        }
        try {
            this.task = new Deadline(trimDescription, LocalDate.parse(by));
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
