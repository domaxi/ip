package task;

public class Event extends Task {
    private final String time;
    private final char type;

    public Event(String taskName, String time) {
        super(taskName);
        this.time = time;
        this.type = 'E';
    }

    @Override
    public char getType() {
        return this.type;
    }

    @Override
    public void printAcknowledgeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + this.getType() + "]" + "[✗]" + this.getTaskName() + " (at: " + this.time + ")");
        System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + (this.getStatus() ? "✓" : "✗") + "] " + this.getTaskName() + " (at: " + this.time + ")";
    }

}