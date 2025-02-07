package treky.command;

/**
 * Represents a command to be executed by the CommandHandler.
 */
public interface Executable {
    /**
     * Executes the command and returns the result.
     *
     * @return The result of the command.
     */
    public String execute();
}
