package ui;

import task.Task;
import task.TaskList;

import java.util.Scanner;

public class Ui {
    Scanner textScanner;

    public Ui(Scanner textScanner) {
        this.textScanner = textScanner;
    }

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
        System.out.println("\tNice! I've marked this task as done: \n\t" + task.toString());
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

    public void printNegativeTaskNumber() {
        System.out.println("\tThe index must be a positive integer");
    }

    public void printExceedTaskNumber() {
        System.out.println("\tThe number exceeds the task number");
    }

    public void printAcknowledgeMessage(Task task, int taskListSize) {
        System.out.println("\tGot it. I've added this task: ");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + taskListSize + " tasks in the list.");
    }

    public void printEmptyListNumber() {
        System.out.println("\tThere is no task in the list");
    }

    public void printList(String message){
        System.out.println(message);
    }

    public void showLine(){
        System.out.println("=================================================");
    }

    public String readCommand(){
        return textScanner.nextLine();
    }

    public void showLoadingError() {
        System.out.println("\tThe file cannot be loaded into the program, please check your directory");
    }

    public void printWrongFormat() {
        System.out.println("\tPlease enter an integer");
    }
}
