public class Task {
    private String taskName;
    private boolean isDone;
    private char type;

    private static int numTask = 0;
    private static int numDone = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
        this.type = 'T';
        numTask ++;//increments the number of task
    }

    public Task() {
        this.taskName = "";
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
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

    public static int getNumTask() {
        return numTask;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void printAcknowledgeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + this.getType()+"]"+"[✗]" + this.getTaskName());
        System.out.println("Now you have " + numTask +" tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
