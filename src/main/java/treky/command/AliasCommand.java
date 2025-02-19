package treky.command;

import treky.exception.TrekyException;

/**
 * Adds an alias to the alias map.
 */
public class AliasCommand implements Executable {
    private static final String FORMAT_MESSAGE = "Format: alias <shortcut> <command>";
    private static final String SUCCESS_MESSAGE = "Alias added: %s -> %s";
    private static final String ALIAS_EXISTS_MESSAGE = "Alias already exists: %s -> %s";
    private final Alias alias;
    private final String shortcut;
    private final String command;

    /**
     * Constructs an AddAlias object with the specified description and Alias.
     * Description must contain the shortcut and command in this format: "shortcut command".
     *
     * @param description The description with shortcut and command of the alias.
     * @param alias The Alias object to manage aliases.
     * @throws TrekyException If the description is empty or the format is invalid.
     */
    public AliasCommand(String description, Alias alias) throws TrekyException {
        String[] parts = description.split(" ", 2);
        if (parts.length != 2) {
            throw new TrekyException(FORMAT_MESSAGE);
        }

        this.shortcut = parts[0].trim();
        this.command = parts[1].trim();
        if (shortcut.isEmpty() || command.isEmpty()) {
            throw new TrekyException(FORMAT_MESSAGE);
        }

        this.alias = alias;
    }

    @Override
    public String execute() throws TrekyException {
        boolean isAdded = alias.addAlias(shortcut, command);

        if (!isAdded) {
            String cmd = alias.resolve(shortcut);
            String errorMessage = String.format(ALIAS_EXISTS_MESSAGE, shortcut, cmd);
            throw new TrekyException(errorMessage);
        }

        return String.format(SUCCESS_MESSAGE, shortcut, command);
    }
}
