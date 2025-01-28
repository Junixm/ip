public class CommandHandler {
    private final TaskManager taskManager;
    private final Storage storage;

    public CommandHandler(TaskManager taskManager, Storage storage) {
        this.taskManager = taskManager;
        this.storage = storage;
    }

    public void executeCommand(String command, String description) {
        try {
            switch (command.toLowerCase()) {
            case "todo":
                addTask(description);
                break;
            case "deadline":
                addDeadlineTask(description);
                break;
            case "event":
                addEventTask(description);
                break;
            case "list":
                taskManager.listTasks();
                break;
            case "mark":
                setMarkTask(description, true);
                break;
            case "unmark":
                setMarkTask(description, false);
                break;
            case "delete":
                deleteTask(description);
                break;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addTask(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        taskManager.addTask(task);
        storage.addLine(task.toFileString());
    }

    private void addDeadlineTask(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("The description of a deadline cannot be empty.");
        }
        String[] parts = description.split(" /by ");
        if (parts.length < 2) {
            throw new TrekyException("Deadline must include '/by' followed by date/time.");
        }
        Task task = new Deadline(parts[0], parts[1]);
        taskManager.addTask(task);
        storage.addLine(task.toFileString());
    }

    private void addEventTask(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("The description of an event cannot be empty.");
        }
        String[] parts = description.split(" /from | /to ");
        if (parts.length < 3) {
            throw new TrekyException("Event must include '/from' and '/to' followed by dates/times.");
        }
        Task task = new Event(parts[0], parts[1], parts[2]);
        taskManager.addTask(task);
        storage.addLine(task.toFileString());
    }

    private void setMarkTask(String description, boolean isDone) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("Please provide a task number.");
        }
        try {
            int index = Integer.parseInt(description);
            taskManager.markTask(index - 1, isDone);
            storage.updateFile(taskManager.getTasks());
        } catch (NumberFormatException e) {
            throw new TrekyException("Task number must be a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new TrekyException("Please provide a valid task number!");
        } catch (Exception e) {
            throw new TrekyException(e.getMessage());
        }
    }

    private void deleteTask(String description) throws TrekyException {
        if (description.isEmpty()) {
            throw new TrekyException("Please provide a task number.");
        }
        try {
            int index = Integer.parseInt(description);
            taskManager.deleteTask(index - 1);
            storage.updateFile(taskManager.getTasks());
        } catch (NumberFormatException e) {
            throw new TrekyException("Task number must be a number!");
        } catch (IndexOutOfBoundsException e) {
            throw new TrekyException("Please provide a valid task number!");
        } catch (Exception e) {
            throw new TrekyException(e.getMessage());
        }
    }
}
