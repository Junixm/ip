package treky;

import treky.command.CommandHandler;
import treky.command.Storage;
import treky.command.TaskManager;

public class Treky {
    private final Ui ui;
    private CommandHandler commandHandler;
    private boolean isExit;

    /**
     * Constructs a Treky object.
     *
     * @param filePath The file path of the storage file.
     */
    public Treky(String filePath) {
        this.ui = new Ui();
        this.isExit = false;
        try {
            Storage storage = new Storage(filePath);
            storage.initStorage();
            TaskManager taskManager = new TaskManager(storage.loadTasks());
            this.commandHandler = new CommandHandler(taskManager, storage);
        } catch (TrekyException e) {
            ui.showError(e.getMessage());
            isExit = true;
        }
    }

    /** Runs the Treky program.*/
    public void run() {
        if (!isExit) {
            ui.showWelcome();
        }
        while (!isExit) {
            try {
                String input = ui.readInput();
                String result = commandHandler.parse(input);
                ui.showResult(result);
                isExit = commandHandler.getExit();
            } catch (IllegalStateException e) {
                ui.showError(e.getMessage());
                isExit = true;
            } catch (TrekyException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    /**
     * The main method of the Treky program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        new Treky("./data/tasks.txt").run();
    }
}
