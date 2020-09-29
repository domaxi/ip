package task;

import exceptions.IncompleteTaskException;

public class Deadline extends Task {

    private final String by;
    private final char type;

    public Deadline(String taskName, String by){
        super(taskName);
        this.by = by;
        this.type = 'D';
    }

    @Override
    public char getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + (this.getStatus() ? "✓" : "✗") + "] " + this.getTaskName() + " (by: " + this.by + ")";
    }

}
