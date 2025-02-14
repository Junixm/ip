package treky.command;

import treky.task.TaskList;
import treky.task.Task;
import treky.task.Todo;
import treky.exception.TrekyException;

/**
 * Adds a todo task to the task list.
 */
public class TodoCommand implements Executable {
    private final Task task;
    private final TaskList taskList;
    private static final String FORMAT_MESSAGE = "Format: todo <description>";
    private static final String SUCCESS_MESSAGE = """
            Got it. I've added this task:
              %s
            Now you have %d tasks in the list.""";

    /**
     * Constructs a TodoCommand object with the specified description and TaskList.
     *
     * @param description The description of the todo task.
     * @param taskList The TaskList object to manage tasks.
     * @throws TrekyException If the description is empty.
     */
    public TodoCommand(String description, TaskList taskList) throws TrekyException {
        assert description != null : "Description cannot be null";
        assert taskList != null : "TaskList cannot be null";

        if (description.isEmpty()) {
            throw new TrekyException(FORMAT_MESSAGE);
        }
        
        this.task = new Todo(description);
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        assert task != null : "Task cannot be null";

        taskList.addTask(task);
        return String.format(SUCCESS_MESSAGE, task, taskList.getTaskListSize());
    }
}
