package command;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.io.IOException;

public class Command {
    private boolean isExit;
    private boolean validDeadlineEventFormat;
    private final String fullCommand;
    private String taskDetail;
    private String taskName;
    private String commandDetail;
    private String[] parsedCommand;


    public Command(String fullCommand) {
        //makes the commands not case sensitive
        this.fullCommand = fullCommand.toLowerCase();
        this.isExit = false;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, InvalidCommandException {
        parsedCommand = this.fullCommand.split(" ", 2);
        String commandPhrase = parsedCommand[0]; //command phrase is only for one worded commands
        if (parsedCommand.length > 1) {
            commandDetail = parsedCommand[1];
            if (fullCommand.contains("deadline")||fullCommand.contains("event")) {
                try{
                    taskName = commandDetail.substring(0, commandDetail.indexOf("/") - 1);
                    taskDetail = commandDetail.substring(commandDetail.indexOf("/") + 4);
                    validDeadlineEventFormat = true;
                }catch (IndexOutOfBoundsException e){
                    ui.printWrongDeadlineEventFormat();
                }
            }
        }

        if (commandPhrase.equals("bye")) {
            ui.printBye();
            this.isExit = true;
            try{
                storage.saveFile(taskList);
            }catch (IOException e){
                ui.printError(e.getMessage());
            }

        } else if (commandPhrase.equals("list")) {
            try {
                ui.printList(taskList);
            } catch (IndexOutOfBoundsException e) {
                ui.printEmptyListNumber();
            }

        } else if (commandPhrase.contains("deadline")) {
            if(validDeadlineEventFormat) {
                try {
                    Deadline deadline = new Deadline(taskName, taskDetail);
                    taskList.addTask(deadline);
                    ui.printAcknowledgeMessage(deadline, taskList.getTaskListSize());
                } catch (IndexOutOfBoundsException e) {
                    ui.printHelp();
                }
            }
        } else if (commandPhrase.contains("event")) {
            if(validDeadlineEventFormat) {
                try {
                    Event event = new Event(taskName, taskDetail);
                    taskList.addTask(event);
                    ui.printAcknowledgeMessage(event, taskList.getTaskListSize());
                } catch (IndexOutOfBoundsException e) {
                    ui.printHelp();
                }
            }
        } else if (commandPhrase.contains("todo")) {
            try {
                Todo todo = new Todo(commandDetail);
                taskList.addTask(todo);
                ui.printAcknowledgeMessage(todo,taskList.getTaskListSize());
            } catch (IndexOutOfBoundsException e) {
                ui.printHelp();
            }

        } else if (commandPhrase.contains("done")) {
            try {
                int taskIndex = Integer.parseInt(commandDetail) - 1;
                taskList.getUserTasks(taskIndex).setDone();
                ui.printDone(taskList.getUserTasks(taskIndex));
            } catch (IndexOutOfBoundsException e) {
                ui.printExceedTaskNumber();
            } catch (NumberFormatException e){
                ui.printWrongFormat();
            }

        } else if (commandPhrase.contains("delete")) {
            try {
                int taskIndex = Integer.parseInt(commandDetail) - 1;
                taskList.deleteTask(taskIndex);
                ui.printList(taskList);
            } catch (IndexOutOfBoundsException e) {
                ui.printHelp();
            } catch (NumberFormatException e) {
                ui.printWrongFormat();
            }

        }else if (commandPhrase.contains("help")){
            ui.printHelp();

        } else {
            throw new InvalidCommandException("Invalid Command. \n\tType help to show the help screen");
        }

    }

    public boolean isExit() {
        return isExit;
    }
}
