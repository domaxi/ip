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
    public String toString() {
        return "[" + this.getType() + "]" + "[" + (this.getStatus() ? "✓" : "✗") + "] " + this.getTaskName() + " (at: " + this.time + ")";
    }

}