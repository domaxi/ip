public class Todo extends Task{

    private char type;

    public Todo(String taskName) {
        super(taskName);
        this.type = 'T';
    }

    @Override
    public char getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "["+this.getType()+"]" + "[" + (this.getStatus()?"✓":"✗") + "] " + this.getTaskName();
    }
}
