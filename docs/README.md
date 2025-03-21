# Treky - Your Task Manager Bot

Treky is a graphical user interface (GUI) task manager bot designed to help you organize your tasks efficiently. It supports various features to manage your to-dos, deadlines and events while providing easy way to track and find tasks.

![Treky UI](./Ui.png)

## Getting Started
1. Ensure that you have Java 17 or above installed.
2. Download the latest `.jar` file from [here](https://github.com/Junixm/ip/releases).
3. Copy the file to the folder you want to put the jar file in.
4. Run the application using this command `java -jar treky.jar`
5. Application will launch if no issues is detected. You will see the Treky logo on the cli as well as the application popup.
```
|  _____        _        
| |_   _| _ ___| |___  _ 
|   | || '_/ -_) / / || |
|   |_||_| \___|_\_\\_, |
|                   |__/ 
```

## Features
### 1. Todo
- Adds a simple task without any date.
- Format: `todo <description>`

### 2. Deadline
- Adds a task with a due date.
- Format: `deadline <description> /by <yyyy-MM-dd>`

### 3. Event
- Adds an event with a scheduled start and end date.
- Format: `todo <description> /from <yyyy-MM-dd> /to <yyyy-MM-dd>`

### 4. Find
- Search for tasks containing a specific keywords.
- Format: `find <keyword>`

### 5. List
- Displays all tasks in your list.
- Format: `list`

### 6. Mark/Unmark
- Mark or unmark a task as complete or not complete.
- Format: `mark <task_number>`
- Format: `unmark <task_number>`

### 5. Alias
- Adds a custom alias for a task command.
- Format: `alias <shortcut> <full_command>`

> [!NOTE]
> - Command names are non-case sensitive (both `todo`, `toDo` works).
> - Separator commands are case sensitive (use `/by`, `/to`, `/from`).
> - Type the command name alone to show the format.
> - Date must follow this format: `yyyy-MM-dd`.
> - Your custom shortcuts will not be saved after exiting.
