import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static Task[] userTasks = new Task[100];
    private static int taskCount = 0;

    public static void printGreetMessaage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printAcknowledgeMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t["+task.getType()+"]"+"[✗]" + task.getTaskName());
        System.out.println("Now you have " + taskCount +" tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printTaskList(int taskCount, Task[] userTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        if (taskCount == 0){
            System.out.println("\tThe task is empty. Please add a task");
        }else{
            for (int i = 0; i < taskCount; i++) {
                System.out.println("\t" + (i+1) + ". " + userTasks[i].toString());
            }
        }
        System.out.println("____________________________________________________________");
    }

    public static void printMarkDone(int taskId, Task[] userTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done: \n\t" + userTasks[taskId].toString());
        System.out.println("____________________________________________________________");
    }

    public static void printHelpMessage() {
        System.out.println("____________________________________________________________");
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
        System.out.println("____________________________________________________________");
    }

    public static void addTask(Task t){
        userTasks[taskCount] = t;
        taskCount += 1;
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean isFinished = false;

        //greets the user with the greeting message
        printGreetMessaage();
        while (!isFinished) {
            //gets the input from the command line
            String inputMessage = myObj.nextLine();
            String[] parsedMessages = inputMessage.split(" ",2);

            if (inputMessage.equals("bye")) {
                //prints the bye message and exits the program
                printByeMessage();
                isFinished = true;
                break;
            } else if (inputMessage.equals("list")) {
                printTaskList(taskCount, userTasks);

            }else if(inputMessage.contains("deadline")) {
                String deadlineName = parsedMessages[1].substring(0,parsedMessages[1].indexOf("/")-1);
                String deadlineBy = parsedMessages[1].substring(parsedMessages[1].lastIndexOf(" ")+1, parsedMessages[1].length());
                addTask(new Deadline(deadlineName,deadlineBy));
                userTasks[taskCount-1].printAcknowledgeMessage();

            } else if (inputMessage.contains("event")){
                String eventName = parsedMessages[1].substring(0,parsedMessages[1].indexOf("/")-1);
                String eventTime = parsedMessages[1].substring(parsedMessages[1].lastIndexOf(" ")+1, parsedMessages[1].length());
                addTask(new Event(eventName,eventTime));
                userTasks[taskCount-1].printAcknowledgeMessage();

            } else if (inputMessage.contains("todo")){
                String todoName = parsedMessages[1];
                addTask(new Todo(todoName));
                userTasks[taskCount-1].printAcknowledgeMessage();

            } else if (inputMessage.contains("done")) {
                int taskNumber = Integer.parseInt(parsedMessages[1]) - 1;
                if (taskNumber >= taskCount) {
                    System.out.println("____________________________________________________________");
                    System.out.println("\tThe number exceeds the task number");
                    System.out.println("____________________________________________________________");

                } else if (taskNumber < 0){
                    System.out.println("____________________________________________________________");
                    System.out.println("\tThe index must be a positive integer");
                    System.out.println("____________________________________________________________");

                } else {
                    userTasks[taskNumber].setDone();
                    printMarkDone(taskNumber, userTasks);
                }

            } else {
                printHelpMessage();
            }

        }

    }
}
