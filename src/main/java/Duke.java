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
        System.out.println("added: " + word);
        System.out.println("____________________________________________________________");
    }

    public static void printWordList(int inputSize, String[] userInputs){
        System.out.println("____________________________________________________________");
        for( int i=0; i<inputSize; i++){
            System.out.println(i + ". " + userInputs[i]);
        }
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean isFinished = false;
        int inputCounter = 0;
        String[] userInputs = new String[100];

        //greets the user with the greeting message
        printGreetMessaage();
        while (!isFinished) {
            //gets the input from the command line
            String inputMessage = myObj.nextLine();
            userInputs[inputCounter] = inputMessage;

            if (inputMessage.equals("bye")) {
                //prints the bye message and exits the program
                printByeMessage();
                isFinished = true;
                break;
            } else if (inputMessage.equals("list")){
                printWordList(inputCounter,userInputs);
            }
            else {
                printRelayMessage(inputMessage);
            }

            inputCounter ++;
        }

    }
}
