package ui;

import exceptions.DukeException;
import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String RED_TEXT = "\u001b[31m";
    private static final String GREEN_TEXT = "\u001b[32;1m";
    private static final String RESET_TEXT = "\u001b[0m";

    Scanner textScanner;

    /**
     * Initializes the Ui by creating the Scanner instance.
     */
    public Ui() {
        textScanner = new Scanner(System.in);
    }

    // Reads the line and returns the string of the command given by the user.
    public String readCommand() {
        return textScanner.nextLine();
    }

    // Prints a line divider
    public void showLine() {
        System.out.println("=================================================");
    }

    // Prints the welcome message when the user executes the program
    public void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    // Prints bye message after the user exits the program
    public void printByeMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    // Prints the done message to acknowledge a task to be turned to done
    public void printDoneMessage(Task task) {
        System.out.println("\tNice! I've marked this task as done: \n\t\t" + GREEN_TEXT + task.toString() + RESET_TEXT);
    }

    // Prints the help message that shows how to properly type commands //
    public void printHelpMessage() {
        System.out.println("The command you entered is invalid");
        System.out.println("Enter one of these commands");
        System.out.println("A. ADD A TASK");
        System.out.println("\t1. Todo - Task with only the description\n\t\ttodo <task description>");
        System.out.println("\t2. Deadline - Task with a set deadline \n\t\tdeadline <task description> /by <date of completion>");
        System.out.println("\t3. Event - Task with a set date\n\t\tevent <task description> /at <date of event>");
        System.out.println("B. LIST THE TASK");
        System.out.println("\t list");
        System.out.println("C. MARK THE TASK AS DONE");
        System.out.println("\t done <task number>");
        System.out.println("D. DELETE THE TASK");
        System.out.println("\t delete <task number>");
        System.out.println("E. END THE PROGRAM");
        System.out.println("\t bye");
    }

    // Print the acknowledge message after adding a task.
    public void printAcknowledgeMessage(Task task, int taskListSize) {
        System.out.println("\tGot it. I've added this task");
        System.out.println(GREEN_TEXT + "\t\t" + task.toString() + RESET_TEXT);
        System.out.println("\tNow you have " + taskListSize + " tasks in the list.");
    }

    /**
     * Prints all of the task in the task list.
     * If there is no task on the list, it will notify that the list is empty
     * @param taskList takes in the TaskList.
     */
    public void printTaskList(TaskList taskList) {
        String listedTasks = "";
        System.out.println("\tHere are the tasks in your list:");
        if (taskList.getTaskListSize() == 0) {
            printEmptyListNumberError();
        } else {
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                listedTasks = listedTasks + (GREEN_TEXT + "\t\t" + (i + 1) + ". " + taskList.getUserTasks(i).toString() + "\n" + RESET_TEXT);
            }
        }
        System.out.println(listedTasks);
    }

    // Prints an error message of the command detail exceeds the task number.
    public void printExceedTaskNumberError() {
        System.out.println(RED_TEXT + "\tThe number exceeds the task number" + RESET_TEXT);
    }

    // Prints an error message if there is not task in the taskList.
    public void printEmptyListNumberError() {
        System.out.println(RED_TEXT + "\t\tThere is no task in the list" + RESET_TEXT);
    }

    // Prints an error message if the user inputs a string to the done or delete command
    public void printWrongFormatError() {
        System.out.println(RED_TEXT + "Please enter an integer" + RESET_TEXT);
    }

    // Prints an error message if the Event or Deadline command is incomplete
    public void printIncompleteCommandError() {
        System.out.println(RED_TEXT + "\tPlease enter a valid task or deadline format" + RESET_TEXT);
        System.out.println("\tDeadline - Task with a set deadline \n\t\tdeadline <task description> /by <date of completion>");
        System.out.println("\tEvent - Task with a set date\n\t\tevent <task description> /at <date of event>");
    }

    /**
     * Prints the search result from the searchResult variable
     * @param searchResult is the String that contains the search result
     * @param searchPhrase is the search keyword
     */
    public void printSearchResult(String searchResult, String searchPhrase){
        System.out.print("\tHere is what I found for \""+ searchPhrase +"\"\n\t\t");
        if(searchResult.length() > 1){
            System.out.println(GREEN_TEXT + searchResult + RESET_TEXT);
        }else{
            System.out.println(RED_TEXT + "I'm sorry, I cannot find \"" + searchPhrase +"\" in the list" + RESET_TEXT);
        }
    }

    /**
     * Prints the error message to the console.
     * @param message The error message
     */
    public void printError(String message) {
        System.out.println("\t" + message);
    }

}
