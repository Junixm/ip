package treky.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import treky.task.Task;
import treky.TrekyException;

public class CommandHandler {
    private final TaskManager taskManager;
    private final Storage storage;
    private boolean isExit;

    /**
     * Constructs a CommandHandler object with the specified TaskManager and Storage.
     * Exit status is set to false by default.
     *
     * @param taskManager TaskManager object to manage tasks.
     * @param storage Storage object to manage file operations.
     */
    public CommandHandler(TaskManager taskManager, Storage storage) {
        this.taskManager = taskManager;
        this.storage = storage;
        this.isExit = false;
    }

    /**
     * Returns the exit status of the CommandHandler.
     *
     * @return true if the CommandHandler is set to exit, false otherwise.
     */
    public boolean getExit() {
        return isExit;
    }

    /**
     * Parses the input string and executes the corresponding command.
     *
     * @param input input string to be parsed.
     * @return result of the command execution.
     * @throws TrekyException if the input is empty or the command is invalid.
     */
    public String parse(String input) throws TrekyException {
        if (input.isEmpty()) {
            throw new TrekyException("You didn't enter anything!\nHow can I help you?");
        }
        String[] parts = input.split(" ", 2);
        String command = parts[0].trim();
        String arguments = (parts.length > 1) ? parts[1] : "";
        return execute(command, arguments);
    }

    private String execute(String command, String description) throws TrekyException {
        return switch (command.toLowerCase()) {
        case "bye" -> setExit();
        case "todo" -> addTodo(description);
        case "deadline" -> addDeadline(description);
        case "event" -> addEvent(description);
        case "list" -> listTasks();
        case "mark" -> markTask(description, true);
        case "unmark" -> markTask(description, false);
        case "delete" -> deleteTask(description);
        default -> throw new TrekyException("I'm sorry, but I don't know what that means :-(");
        };
    }

    private String setExit() {
        isExit = true;
        return "bye";
    }

    private String addResult(Task task) {
        return "Got it. I've added this task:\n  " + task + "\n Now you have " +
                taskManager.getTaskListSize() + " tasks in the list.";
    }

    private String addTodo(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("Format: todo <description>");
        }
        Task task = taskManager.addTodoTask(description);
        storage.addLine(task.toSaveString());
        return addResult(task);
    }

    private String addDeadline(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("Format: deadline <description> /by <yyyy-MM-dd>");
        }
        String[] parts = description.split("/by");
        String trimmedDescription = parts[0].trim();
        if (trimmedDescription.isEmpty()) {
            throw new TrekyException("Description cannot be empty.\n" +
                    "Format: deadline <description> /by <yyyy-MM-dd>");
        }
        if (parts.length != 2 || parts[1].trim().isEmpty()) {
            throw new TrekyException("Deadline cannot be empty\n" +
                    "Format: deadline <description> /by <yyyy-MM-dd>");
        }
        try {
            LocalDate by = LocalDate.parse(parts[1].trim());
            Task task = taskManager.addDeadlineTask(trimmedDescription, by);
            storage.addLine(task.toSaveString());
            return addResult(task);
        } catch (DateTimeParseException e) {
            throw new TrekyException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private String addEvent(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("Format: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }
        String[] parts = description.split("/from|/to");
        String trimmedDescription = parts[0].trim();
        if (trimmedDescription.isEmpty()) {
            throw new TrekyException("Description cannot be empty.\n" +
                    "Format: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }
        if (parts.length != 3 || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            throw new TrekyException("Invalid event format.\n" +
                    "Format: event <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>");
        }
        try {
            LocalDate from = LocalDate.parse(parts[1].trim());
            LocalDate to = LocalDate.parse(parts[2].trim());
            Task task = taskManager.addEventTask(trimmedDescription, from, to);
            storage.addLine(task.toSaveString());
            return addResult(task);
        } catch (DateTimeParseException e) {
            throw new TrekyException("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    private String listTasks() {
        List<Task> tasks = taskManager.getTaskList();
        if (tasks.isEmpty()) {
            return "You have no tasks!";
        }
        StringBuilder result = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            result.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return result.toString();
    }

    private String markTask(String description, boolean isDone) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("Format: mark <task number>");
        }
        try {
            int number = Integer.parseInt(description);
            Task task = taskManager.markTask(number, isDone);
            storage.updateFile(taskManager.getTaskList());
            if (isDone) {
                return "Nice! I've marked this task as done:\n  " + task;
            } else {
                return "OK, I've marked this task as not done yet:\n  " + task;
            }
        } catch (NumberFormatException e) {
            throw new TrekyException("Task number must be a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new TrekyException("Please provide a valid task number!");
        }
    }

    private String deleteTask(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("Format: delete <task number>");
        }
        try {
            int number = Integer.parseInt(description);
            Task task = taskManager.deleteTask(number);
            storage.updateFile(taskManager.getTaskList());
            return "Noted. I've removed this task:\n  " + task + "\n Now you have " +
                    taskManager.getTaskListSize() + " tasks in the list.";
        } catch (NumberFormatException e) {
            throw new TrekyException("Task number must be a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new TrekyException("Please provide a valid task number!");
        }
    }
}
