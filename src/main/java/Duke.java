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

    public static void printRelayMessage(String word){
        System.out.println("____________________________________________________________");
        System.out.println(word);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean isFinished = false;

        //greets the user with the greeting message
        printGreetMessaage();
        while (!isFinished) {
            //gets the input from the command line
            String userInput = myObj.nextLine();
            if (userInput.equals("bye")) {
                //prints the bye message and exits the program
                printByeMessage();
                isFinished = true;
                break;
            } else {
                printRelayMessage(userInput);
            }
        }

    }
}
