public class Task {
    private String taskName;
    private boolean isDone;

    private static int numDone = 0;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public Task() {
        this.taskName = "";
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getStatus() {
        return isDone;
    }
}
