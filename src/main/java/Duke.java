import java.util.Scanner;
import task.*;

public class Duke {
    private static final Task[] userTasks = new Task[100];
    private static final String line = "____________________________________________________________";
    private static int taskCount = 0;

    public static void printGreetMessaage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line);
    }

    public static void printByeMessage() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void printAcknowledgeMessage(Task task) {
        System.out.println(line);
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + task.getType() + "]" + "[✗]" + task.getTaskName());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(line);
    }

    public static void addTask(Task t) {
        userTasks[taskCount] = t;
        taskCount += 1;
    }

    public static void printMarkDone(int taskId, Task[] userTasks) {
        System.out.println(line);
        System.out.println("\tNice! I've marked this task as done: \n\t" + userTasks[taskId].toString());
        System.out.println(line);
    }

    public static void printHelpMessage() {
        System.out.println(line);
        System.out.println("The command you entered is invalid");
        System.out.println("Enter one of these commands");
        System.out.println("A. ADD A TASK");
        System.out.println("\t1. Todo - Task with only the description\n\ttodo <task description>");
        System.out.println("\t2. Deadline - Task with a set deadline \n\tdeadline <task description> /by <date of completion>");
        System.out.println("\t3. Event - Task with a set date\n\tevent <task description> /at <date of event>");
        System.out.println("B. LIST THE TASK");
        System.out.println("\t list");
        System.out.println("C. MARK THE TASK AS DONE");
        System.out.println("\t done <task index>");
        System.out.println("D. END THE PROGRAM");
        System.out.println("\t bye");
        System.out.println(line);
    }

    private static void printNegativeTaskNumber() {
        System.out.println(line);
        System.out.println("\tThe index must be a positive integer");
        System.out.println(line);
    }

    private static void printExceedTaskNumber() {
        System.out.println(line);
        System.out.println("\tThe number exceeds the task number");
        System.out.println(line);
    }

    private static void printEmptyListNumber() {
        System.out.println(line);
        System.out.println("\tThere is no task in the list");
        System.out.println(line);
    }

    public static void executeListCommand(int taskCount, Task[] userTasks) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        if (taskCount == 0) {
            System.out.println("\tThe task is empty. Please add a task");
        } else {
            for (int i = 0; i < taskCount; i++) {
                System.out.println("\t" + (i + 1) + ". " + userTasks[i].toString());
            }
        }
        System.out.println(line);
    }

    private static void executeByeCommand() {
        boolean isFinished;
        //prints the bye message and exits the program
        printByeMessage();
        isFinished = true;
        return;
    }

    private static void executeDeadlineCommand(String parsedMessage) {
        String deadlineName = parsedMessage.substring(0, parsedMessage.indexOf("/") - 1);
        String deadlineBy = parsedMessage.substring(parsedMessage.indexOf("/") + 4);
        addTask(new Deadline(deadlineName, deadlineBy));
        userTasks[taskCount - 1].printAcknowledgeMessage();
    }

    private static void executeEventCommand(String parsedMessage) {
        String eventName = parsedMessage.substring(0, parsedMessage.indexOf("/") - 1);
        String eventTime = parsedMessage.substring(parsedMessage.indexOf("/") + 4);
        addTask(new Event(eventName, eventTime));
        userTasks[taskCount - 1].printAcknowledgeMessage();
    }

    private static void executeToDoCommand(String parsedMessage) {
        String todoName = parsedMessage;
        addTask(new Todo(todoName));
        userTasks[taskCount - 1].printAcknowledgeMessage();
    }

    private static void executeDoneCommand(String parsedMessage) {
        try{
            int taskNumber = Integer.parseInt(parsedMessage) - 1;
            if (taskCount == 0) {
                printEmptyListNumber();
            } else if (taskNumber > taskCount) {
                printExceedTaskNumber();
            } else if (taskNumber < 0) {
                printNegativeTaskNumber();
            } else {
                userTasks[taskNumber].setDone();
                printMarkDone(taskNumber, userTasks);
            }
        }catch (IndexOutOfBoundsException e){
            printHelpMessage();
        }
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);
        boolean isFinished = false;
        //greets the user with the greeting message
        printGreetMessaage();
        while (!isFinished) {
            //gets the input from the command line
            String inputMessage = myScanner.nextLine();
            String[] parsedMessages = inputMessage.split(" ", 2);

            if (inputMessage.equals("bye")) {
                executeByeCommand();
                break;
            } else if (inputMessage.equals("list")) {
                executeListCommand(taskCount, userTasks);
            } else if (inputMessage.contains("deadline")) {
                try {
                    executeDeadlineCommand(parsedMessages[1]);
                }catch (IndexOutOfBoundsException e){
                    printHelpMessage();
                }
            } else if (inputMessage.contains("event")) {
                try {
                    executeEventCommand(parsedMessages[1]);
                }catch (IndexOutOfBoundsException e){
                    printHelpMessage();
                }
            } else if (inputMessage.contains("todo")) {
                try {
                    executeToDoCommand(parsedMessages[1]);
                }catch (IndexOutOfBoundsException e){
                    printHelpMessage();
                }
            } else if (inputMessage.contains("done")) {
                try {
                    executeDoneCommand(parsedMessages[1]);
                }catch (IndexOutOfBoundsException e){
                    printHelpMessage();
                }
            } else {
                printHelpMessage();
            }
        }
    }
}
