package treky.command;

import org.junit.jupiter.api.Test;
import treky.exception.TrekyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandHandlerTest {

    @Test
    public void parse_emptyInput_exceptionThrown() {
        CommandHandler commandHandler = new CommandHandler(null, null);

        try {
            commandHandler.parse("");
            fail();
        } catch (TrekyException e) {
            assertEquals("You didn't enter anything!\nHow can I help you?", e.getMessage());
        }
    }

    @Test
    public void parse_randomInput_exceptionThrown() {
        CommandHandler commandHandler = new CommandHandler(null, null);

        try {
            commandHandler.parse("random");
            fail();
        } catch (TrekyException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_byeCommand_success() {
        CommandHandler commandHandler = new CommandHandler(null, null);
        try {
            assertEquals("bye", commandHandler.parse("bye"));
        } catch (TrekyException e) {
            fail();
        }
    }
}
