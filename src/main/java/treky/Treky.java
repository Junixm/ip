package treky;

import treky.task.TaskList;
import treky.ui.Ui;
import treky.command.CommandHandler;
import treky.storage.Storage;
import treky.exception.TrekyException;
import treky.exception.TrekyFatalException;

public class Treky {
    // Adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private CommandHandler commandHandler;

    public static void main(String[] args) {
        new Treky().run();
    }

    /** Constructs a Treky object.*/
    public Treky() {
        try {
            this.ui = new Ui();
            this.storage = new Storage();
            this.taskList = storage.load();
            this.commandHandler = new CommandHandler(taskList);
            ui.showWelcome();
        } catch (TrekyFatalException e) {
            ui.showError(e.getMessage());
            System.exit(1);
        }
    }

   private void run() {
       boolean isExit = false;
       while (!isExit) {
           try {
               String input = ui.readInput();
               String result = commandHandler.parse(input);
               ui.showResult(result);
               isExit = commandHandler.getExit();
               storage.save(taskList);
           } catch (TrekyException e) {
               ui.showError(e.getMessage());
           } catch (TrekyFatalException e) {
               ui.showError(e.getMessage());
               System.exit(1);
           }
       }
       ui.showGoodbye();
   }
}