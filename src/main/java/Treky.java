public class Treky {
    private Ui ui;
    private CommandHandler commandHandler;

    public Treky(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            TaskManager taskManager = new TaskManager(storage.loadTasks());
            this.commandHandler = new CommandHandler(taskManager, storage);
        } catch (TrekyException e) {
            ui.showError(e.getMessage());
            ui.showGoodbye();
            System.exit(1);
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                ui.showLine();
                String command = ui.readCommand();
                ui.showLine();
                commandHandler.executeCommand(command);
                isExit = commandHandler.isExit();
            } catch (TrekyException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Treky("./data/tasks.txt").run();
    }
}
