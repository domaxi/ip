package command;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import storage.Storage;
import task.*;
import ui.Ui;

import java.util.ArrayList;

public class Command {
    private boolean isExit;
    private boolean validDeadlineEventFormat;
    private final String fullCommand;
    private String taskDetail;
    private String taskName;
    private String commandDetail;


    public Command(String fullCommand) {
        //makes the commands not case sensitive
        this.fullCommand = fullCommand.toLowerCase();
        this.isExit = false;
    }

    /**
     * Executes the command given by the user.
     * The function parses the function and executes command according to the task
     *
     * @param taskList  takes in the taskList class and perform operations based on the taskList
     * @param ui handles the input and output from and to the user.
     * @param storage handles the File output to save the updated taskList after the command.
     * @throws DukeException if there are invalid command structures.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        // Parses the command with whitespace as the regex
        String[] parsedCommand = this.fullCommand.split(" ", 2);
        // Command phrase is the first phrase of the command, it denotes the command type
        String commandPhrase = parsedCommand[0];

        // To differentiate a one worded command to a multi-worded command.
        if (parsedCommand.length > 1) {
            commandDetail = parsedCommand[1];
            if (fullCommand.contains("deadline")||fullCommand.contains("event")) {
                try{
                    taskName = commandDetail.substring(0, commandDetail.indexOf("/") - 1);
                    taskDetail = commandDetail.substring(commandDetail.indexOf("/") + 4);
                    validDeadlineEventFormat = true;
                }catch (IndexOutOfBoundsException e){
                    ui.printIncompleteCommandError();
                }
            }
        }

        // If statement to discern the commands given by the user
        if (commandPhrase.equals("bye")) {
            ui.printByeMessage();
            this.isExit = true;
            storage.saveFile(taskList);

        } else if (commandPhrase.equals("list")) {
            try {
                ui.printTaskList(taskList);
            } catch (IndexOutOfBoundsException e) {
                ui.printEmptyListNumberError();
            }

        } else if (commandPhrase.contains("deadline")) {
            if(validDeadlineEventFormat) {
                try {
                    Deadline deadline = new Deadline(taskName, taskDetail);
                    taskList.addTask(deadline);
                    ui.printAcknowledgeMessage(deadline, taskList.getTaskListSize());
                } catch (IndexOutOfBoundsException e) {
                    ui.printHelpMessage();
                }
            }
            else{
                ui.printIncompleteCommandError();
            }
        } else if (commandPhrase.contains("event")) {
            if(validDeadlineEventFormat) {
                try {
                    Event event = new Event(taskName, taskDetail);
                    taskList.addTask(event);
                    ui.printAcknowledgeMessage(event, taskList.getTaskListSize());
                } catch (IndexOutOfBoundsException e) {
                    ui.printHelpMessage();
                }
            }else{
                ui.printIncompleteCommandError();
            }
        } else if (commandPhrase.contains("todo")) {
            try {
                Todo todo = new Todo(commandDetail);
                taskList.addTask(todo);
                ui.printAcknowledgeMessage(todo,taskList.getTaskListSize());
            } catch (IndexOutOfBoundsException e) {
                ui.printHelpMessage();
            }

        } else if (commandPhrase.contains("done")) {
            try {
                int taskIndex = Integer.parseInt(commandDetail) - 1;
                taskList.getUserTasks(taskIndex).setDone();
                ui.printDoneMessage(taskList.getUserTasks(taskIndex));
            } catch (IndexOutOfBoundsException e) {
                ui.printExceedTaskNumberError();
            } catch (NumberFormatException e){
                ui.printWrongFormatError();
            }

        }else if (commandPhrase.contains("find")){
            try {
                String searchResult = taskList.getSearchResult(commandDetail);
                ui.printSearchResult(searchResult,commandDetail);
            } catch (NumberFormatException e) {
                ui.printWrongFormatError();
            }

        } else if (commandPhrase.contains("delete")) {
            try {
                int taskIndex = Integer.parseInt(commandDetail) - 1;
                taskList.deleteTask(taskIndex);
                ui.printTaskList(taskList);
            } catch (IndexOutOfBoundsException e) {
                ui.printExceedTaskNumberError();
            } catch (NumberFormatException e) {
                ui.printWrongFormatError();
            }

        } else if (commandPhrase.contains("help")){
            ui.printHelpMessage();

        } else {
            throw new InvalidCommandException("Invalid Command. \n\tType help to show the help screen");
        }

    }

    // Check the status of the isExit variable.
    public boolean isExit() {
        return isExit;
    }
}
