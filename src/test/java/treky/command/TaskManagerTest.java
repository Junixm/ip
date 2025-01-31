package treky.command;

import org.junit.jupiter.api.Test;

import java.util.List;
import treky.task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskManagerTest {

    @Test
    public void markTask_nonEmptyTaskList_success() {
        List<Task> taskList = List.of(new Task("task 1", false), new Task("task 2", true));
        TaskManager taskManager = new TaskManager(taskList);

        // Mark task 1 as done
        assertEquals("[X] task 1", taskManager.markTask(1, true).toString());

        // Mark task 2 as undone
        assertEquals("[ ] task 2", taskManager.markTask(2, false).toString());

        // Mark task 1 as undone
        assertEquals("[ ] task 1", taskManager.markTask(1, false).toString());

        // Mark task 1 as undone again
        assertEquals("[ ] task 1", taskManager.markTask(1, false).toString());
    }

    @Test
    public void markTask_nonEmptyTaskList_exceptionThrown() {
        List<Task> taskList = List.of(new Task("task 1", false), new Task("task 2", true));
        TaskManager taskManager = new TaskManager(taskList);

        // Mark task 0 as done
        try {
            taskManager.markTask(0, true);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Please provide a valid task number!", e.getMessage());
        }

        // Mark task 3 as done
        try {
            taskManager.markTask(3, true);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Please provide a valid task number!", e.getMessage());
        }
    }

    @Test
    public void markTask_emptyTaskList_exceptionThrown() {
        TaskManager taskManager = new TaskManager();

        // Mark task 0 as done
        try {
            taskManager.markTask(0, true);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Please provide a valid task number!", e.getMessage());
        }

        // Mark task 1 as done
        try {
            taskManager.markTask(1, true);
            fail();
        } catch (IndexOutOfBoundsException e) {
            assertEquals("Please provide a valid task number!", e.getMessage());
        }
    }
}
