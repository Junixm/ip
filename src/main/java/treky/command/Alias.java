package treky.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of aliases for commands.
 */
public class Alias {
    private final Map<String, String> aliasMap;

    /**
     * Constructs an Alias object with an empty alias map.
     */
    public Alias() {
        this.aliasMap = new HashMap<>();
    }

    /**
     * Adds an alias to the alias map.
     *
     * @param alias The alias to search for.
     * @param command The command to execute
     * @return true if the alias is successfully added, false if the alias already exists.
     */
    public boolean addAlias(String alias, String command) {
        if (aliasMap.containsKey(alias)) {
            return false;
        }
        aliasMap.put(alias, command);
        return true;
    }

    /**
     * Deletes an alias from the alias map.
     *
     * @param alias The alias to delete.
     * @return true if the alias is successfully deleted, false if the alias is not found.
     */
    public boolean deleteAlias(String alias) {
        return aliasMap.remove(alias) != null;
    }

    /**
     * Resolves an alias to the corresponding command.
     *
     * @param alias The alias to resolve.
     * @return The command corresponding to the alias.
     * If the alias is not found, the alias itself is returned.
     */
    public String resolve (String alias) {
        return aliasMap.getOrDefault(alias, alias);
    }
}
