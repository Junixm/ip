package treky;

import javafx.scene.web.HTMLEditorSkin;
import treky.task.TaskList;
import treky.ui.TextUi;
import treky.command.CommandHandler;
import treky.storage.Storage;
import treky.exception.TrekyException;
import treky.exception.TrekyFatalException;

public class Treky {
    // Adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/Main.java
    private static final String WELCOME_MESSAGE = "Hello! I'm Treky\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Goodbye! Have a great day!";
    private TextUi textUi;
    private Storage storage;
    private TaskList taskList;
    private CommandHandler commandHandler;
    private static boolean isExit = false;

    public static void main(String[] args) {
        new Treky().run();
    }

    /** Constructs a Treky object.*/
    public Treky() {
        try {
            this.textUi = new TextUi();
            this.storage = new Storage();
            this.taskList = storage.load();
            this.commandHandler = new CommandHandler(taskList);
            textUi.showLogo();
        } catch (TrekyFatalException e) {
            textUi.showError(e.getMessage());
            System.exit(1);
        }
    }

   private void run() {
       textUi.showWelcome();
       while (!isExit) {
           try {
               String input = textUi.readInput();
               String result = commandHandler.parse(input);
               textUi.showResult(result);
               isExit = commandHandler.getExit();
               storage.save(taskList);
           } catch (TrekyException e) {
               textUi.showError(e.getMessage());
           } catch (TrekyFatalException e) {
               textUi.showError(e.getMessage());
               System.exit(1);
           }
       }
       textUi.showGoodbye();
       System.exit(0);
   }

   public String getWelcomeMessage() {
       return WELCOME_MESSAGE;
   }

   public String getResponse(String input) {
       try {
           String result = commandHandler.parse(input);
           isExit = commandHandler.getExit();
           if (isExit) {
               return GOODBYE_MESSAGE;
           }
           storage.save(taskList);
           return result;
       } catch (TrekyException e) {
           return e.getMessage();
       } catch (TrekyFatalException e) {
           isExit = true;
           return e.getMessage();
       }
   }

   public boolean getExit() {
       return isExit;
   }
}