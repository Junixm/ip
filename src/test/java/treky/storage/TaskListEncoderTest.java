package treky.storage;

import org.junit.jupiter.api.Test;

import java.util.List;
import treky.task.Task;
import treky.task.TaskList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListEncoderTest {

    @Test
    public void encode_emptyTaskList_success() {
        TaskList taskList = new TaskList();
        List<String> encodedTaskList = TaskListEncoder.encode(taskList);
        assertEquals(0, encodedTaskList.size());
    }

    @Test
    public void encode_nonEmptyTaskList_success() {
        List<Task> list = List.of(new Task("task 1", false), new Task("task 2", true));
        TaskList taskList = new TaskList(list);
        List<String> encodedTaskList = TaskListEncoder.encode(taskList);
        assertEquals(2, encodedTaskList.size());
        assertEquals("[ ] task 1", encodedTaskList.get(0));
        assertEquals("[X] task 2", encodedTaskList.get(1));
    }
}
