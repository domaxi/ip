public class Deadline extends Task {

    private final String by;
    private final char type;

    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
        this.type = 'D';
    }

    @Override
    public char getType() {
        return this.type;
    }

    @Override
    public void printAcknowledgeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: ");
        System.out.println("\t[" + this.getType() + "]" + "[✗]" + this.getTaskName() + " (by: " + this.by + ")");
        System.out.println("Now you have " + Task.getNumTask() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    @Override
    public String toString() {
        return "[" + this.getType() + "]" + "[" + (this.getStatus() ? "✓" : "✗") + "] " + this.getTaskName() + " (by: " + this.by + ")";
    }

}
