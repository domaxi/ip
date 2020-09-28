package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String RED_TEXT = "\u001b[31m";
    private static final String GREEN_TEXT = "\u001b[32;1m";
    private static final String RESET_TEXT = "\u001b[0m";

    Scanner textScanner;

    public Ui() {
        textScanner = new Scanner(System.in);
    }

    public void showWelcome() {
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

    public void printBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void printDone(Task task) {
        System.out.println("\tNice! I've marked this task as done: \n\t\t" + GREEN_TEXT + task.toString() + RESET_TEXT);
    }

    public void printHelp() {
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

    public void printExceedTaskNumber() {
        System.out.println(RED_TEXT + "\tThe number exceeds the task number" + RESET_TEXT);
    }

    public void printAcknowledgeMessage(Task task, int taskListSize) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println(GREEN_TEXT + "\t\t" + task.toString() + RESET_TEXT);
        System.out.println("\tNow you have " + taskListSize + " tasks in the list.");
    }

    public void printEmptyListNumber() {
        System.out.println(RED_TEXT + "\t\tThere is no task in the list" + RESET_TEXT);
    }

    public void printList(TaskList taskList) {
        String listedTasks = "";
        System.out.println("\tHere are the tasks in your list:");
        if (taskList.getTaskListSize() == 0) {
            printEmptyListNumber();
        } else {
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                listedTasks = listedTasks + (GREEN_TEXT + "\t\t" + (i + 1) + ". " + taskList.getUserTasks(i).toString() + "\n" + RESET_TEXT);
            }
        }
        System.out.println(listedTasks);
    }

    public void showLine() {
        System.out.println("=================================================");
    }

    public String readCommand() {
        return textScanner.nextLine();
    }

    public void printWrongFormat() {
        System.out.println(RED_TEXT + "Please enter an integer" + RESET_TEXT);
    }

    public void printWrongDeadlineEventFormat() {
        System.out.println(RED_TEXT + "\tPlease enter a valid task or deadline format" + RESET_TEXT);
        System.out.println("\tDeadline - Task with a set deadline \n\t\tdeadline <task description> /by <date of completion>");
        System.out.println("\tEvent - Task with a set date\n\t\tevent <task description> /at <date of event>");
    }

    public void printError(String message) {
        System.out.println("\t" + message);
    }
}
