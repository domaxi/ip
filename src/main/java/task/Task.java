package task;

public class Task {
    private static int numTask = 0;
    private static final int numDone = 0;
    private String taskName;
    private boolean isDone;
    private char type;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.type = 'T';
        numTask++;//increments the number of task
    }

    public Task() {
        this.taskName = "";
        this.isDone = false;
    }

    public static int getNumTask() {
        return numTask;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getStatus() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public char getType() {
        return type;
    }

    public void printAcknowledgeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + this.getType() + "]" + "[✗]" + this.getTaskName());
        System.out.println("Now you have " + numTask + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
