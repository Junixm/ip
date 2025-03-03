package treky.command;

import treky.task.TaskList;
import treky.exception.TrekyException;


public class CommandHandler {
    private static final String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :-(";
    private static final String EMPTY_INPUT_MESSAGE = "You didn't enter anything!\nHow can I help you?";
    private final TaskList taskList;
    private final Alias alias;
    private static boolean isExit = false;

    /**
     * Constructs a CommandHandler object with the specified TaskManager and Storage.
     * Exit status is set to false by default.
     *
     * @param taskList TaskManager object to manage tasks.
     */
    public CommandHandler(TaskList taskList) {
        this.taskList = taskList;
        this.alias = new Alias();
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
        assert input != null : "Input cannot be null";

        if (input.isBlank()) {
            throw new TrekyException(EMPTY_INPUT_MESSAGE);
        }

        String[] parts = splitInput(input);
        String command = alias.resolve(parts[0]);
        String arguments = parts[1];
        return prepareCommand(command, arguments);
    }

    private String[] splitInput(String input) {
        String[] parts = input.trim().split(" ", 2);
        String command = parts[0].trim().toLowerCase();
        String arguments = (parts.length > 1) ? parts[1].trim() : "";
        return new String[] {command, arguments};
    }

    private String prepareCommand(String command, String description) throws TrekyException {
        return switch (command) {
        case "bye" -> setExit();
        case "todo" -> new TodoCommand(description, taskList).execute();
        case "deadline" -> new DeadlineCommand(description, taskList).execute();
        case "event" -> new EventCommand(description, taskList).execute();
        case "list" -> new ListCommand(taskList).execute();
        case "delete" -> new DeleteCommand(description, taskList).execute();
        case "mark" -> new MarkCommand(description, taskList, true).execute();
        case "unmark" -> new MarkCommand(description, taskList, false).execute();
        case "find" -> new FindCommand(description, taskList).execute();
        case "alias" -> new AliasCommand(description, alias).execute();
        default -> throw new TrekyException(UNKNOWN_COMMAND_MESSAGE);
        };
    }

    private String setExit() {
        isExit = true;
        return "";
    }
}