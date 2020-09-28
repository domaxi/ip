package task;

public class Task extends TaskList{
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

    public boolean getStatus() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public char getType() {
        return type;
    }


}
