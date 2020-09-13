import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static final ArrayList<Task> userTasks = new ArrayList<>();
    private static final String line = "____________________________________________________________";
    private static int taskCount = 0;

    public static void printGreetMessage() {
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

    public static void addTask(Task t) {
        userTasks.add(t);
        taskCount += 1;
    }

    public static void printMarkDone(int taskId) {
        System.out.println(line);
        System.out.println("\tNice! I've marked this task as done: \n\t" + userTasks.get(taskId).toString());
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
        System.out.println("\t done <task number>");
        System.out.println("D. DELETE THE TASK");
        System.out.println("\t delete <task number>");
        System.out.println("E. END THE PROGRAM");
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

    private static void printSuccessfulSave(boolean isFileExist, boolean isDirectoryCreated, boolean isFileCreated) {
        System.out.println(line);
        if (isFileExist) {
            System.out.println("\tThe file already exist. Overwriting the file");
        } else {
            System.out.println("\tThe file doesnt exist. Creating a new file");
            if (isDirectoryCreated && isFileCreated) {
                System.out.println("\tThe new directory and file has ben created successfully");
            }
        }
        System.out.println("\tThe file has been saved successfully");
        System.out.println(line);
    }

    public static void executeListCommand() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        if (userTasks.size() == 0) {
            System.out.println("\tThe task is empty. Please add a task");
        } else {
            for (int i = 0; i < userTasks.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + userTasks.get(i).toString());
            }
        }
        System.out.println(line);
    }

    private static void executeByeCommand() {
        printByeMessage();
    }

    private static void executeDeadlineCommand(String parsedMessage) {
        String deadlineName = parsedMessage.substring(0, parsedMessage.indexOf("/") - 1);
        String deadlineBy = parsedMessage.substring(parsedMessage.indexOf("/") + 4);
        addTask(new Deadline(deadlineName, deadlineBy));
        userTasks.get(taskCount - 1).printAcknowledgeMessage();
    }

    private static void executeEventCommand(String parsedMessage) {
        String eventName = parsedMessage.substring(0, parsedMessage.indexOf("/") - 1);
        String eventTime = parsedMessage.substring(parsedMessage.indexOf("/") + 4);
        addTask(new Event(eventName, eventTime));
        userTasks.get(taskCount - 1).printAcknowledgeMessage();
    }

    private static void executeToDoCommand(String parsedMessage) {
        addTask(new Todo(parsedMessage));
        userTasks.get(taskCount - 1).printAcknowledgeMessage();
    }


    private static void executeDeleteCommand (String parsedMessage){
        int taskNumber = Integer.parseInt(parsedMessage);
        userTasks.remove(taskNumber -1);
    }

    private static void executeDoneCommand(String parsedMessage) {
        int taskNumber = Integer.parseInt(parsedMessage) - 1;
        if (taskCount == 0) {
            printEmptyListNumber();
        } else if (taskNumber > taskCount) {
            printExceedTaskNumber();
        } else if (taskNumber < 0) {
            printNegativeTaskNumber();
        } else {
            userTasks.get(taskNumber).setDone();
            printMarkDone(taskNumber);
        }
    }

    public static void writeFile(String fileDirectory) throws IOException {
        FileWriter fileWriter = new FileWriter(fileDirectory);
        for (Task userTask : userTasks) {
            fileWriter.write(userTask.toString() + "\n");
        }
        fileWriter.close();
    }

    private static void saveTask() {
        String outputDirectoryName = "data";
        String outputFileName = "data/duke.txt";
        File outputDirectory = new File(outputDirectoryName);
        File outputFile = new File(outputFileName);
        boolean isFileExist;
        boolean isCreateDirectorySuccess = false, isCreateFileSuccess = false;
        try {
            if (!outputFile.exists()) {
                isFileExist = false;
                isCreateDirectorySuccess = outputDirectory.mkdir();
                isCreateFileSuccess = outputFile.createNewFile();
            } else {
                isFileExist = true;
            }
            writeFile(outputFileName);
            printSuccessfulSave(isFileExist, isCreateDirectorySuccess, isCreateFileSuccess);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        //greets the user with the greeting message
        printGreetMessage();
        while (true) {
            //gets the input from the command line
            String inputMessage = myScanner.nextLine();
            String[] parsedMessages = inputMessage.split(" ", 2);

            if (inputMessage.equals("bye")) {
                executeByeCommand();
                break;
            } else if (inputMessage.equals("list")) {
                try {
                    executeListCommand();
                } catch (IndexOutOfBoundsException e) {

                    printEmptyListNumber();
                }
            } else if (inputMessage.contains("deadline")) {
                try {
                    executeDeadlineCommand(parsedMessages[1]);
                } catch (IndexOutOfBoundsException e) {
                    printHelpMessage();
                }
            } else if (inputMessage.contains("event")) {
                try {
                    executeEventCommand(parsedMessages[1]);
                } catch (IndexOutOfBoundsException e) {
                    printHelpMessage();
                }
            } else if (inputMessage.contains("todo")) {
                try {
                    executeToDoCommand(parsedMessages[1]);
                } catch (IndexOutOfBoundsException e) {
                    printHelpMessage();
                }
            } else if (inputMessage.contains("done")) {
                try {
                    executeDoneCommand(parsedMessages[1]);
                } catch (IndexOutOfBoundsException e) {
                    printHelpMessage();
                }
            } else if (inputMessage.contains("delete")) {
                try {
                    executeDeleteCommand(parsedMessages[1]);
                    executeListCommand();
                } catch (IndexOutOfBoundsException e) {
                    printHelpMessage();
                }
            } else if (inputMessage.contains("save")) {
                saveTask();
            } else {
                printHelpMessage();
            }
        }
    }
}
