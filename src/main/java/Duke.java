import java.util.Scanner;

public class Duke {

    public static void printGreetMessaage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void printByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printRelayMessage(String word) {
        System.out.println("____________________________________________________________");
        System.out.println("added: " + word);
        System.out.println("____________________________________________________________");
    }

    public static void printTaskList(int inputSize, Task[] userTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < inputSize; i++) {
            if (userTasks[i].getStatus()) {
                System.out.println(i + "." + "[✓] " + userTasks[i].getTaskName());
            } else {
                System.out.println(i + "." + "[✗] " + userTasks[i].getTaskName());
            }
        }
        System.out.println("____________________________________________________________");
    }

    public static void printMarkDone(int taskId, Task[] userTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done: \n" + "[✓] "+ userTasks[taskId].getTaskName());
        System.out.println("____________________________________________________________");
    }


    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean isFinished = false;
        int inputCounter = 0;
        Task[] userTasks = new Task[100];

        //greets the user with the greeting message
        printGreetMessaage();
        while (!isFinished) {
            //gets the input from the command line
            String inputMessage = myObj.nextLine();

            if (inputMessage.equals("bye")) {
                //prints the bye message and exits the program
                printByeMessage();
                isFinished = true;
                break;
            } else if (inputMessage.equals("list")) {
                printTaskList(inputCounter, userTasks);
            } else if (inputMessage.contains("done")) {
                int taskNumber = inputMessage.charAt(5) - 48; // converts the char into integer
                if (taskNumber >= inputCounter) {
                    System.out.println("____________________________________________________________");
                    System.out.println("The number exceeds the task number");
                    System.out.println("____________________________________________________________");
                } else {
                    userTasks[taskNumber].setDone();
                    printMarkDone(taskNumber,userTasks);
                }
            } else {
                userTasks[inputCounter] = new Task(inputMessage);
                printRelayMessage(inputMessage);
                inputCounter++;
            }

        }

    }
}
