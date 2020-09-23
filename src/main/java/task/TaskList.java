package task;

import storage.Storage;

import java.util.ArrayList;

public class TaskList {

    private static final ArrayList<Task> userTasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(TaskList load) {
    }

    public void addTask(Task task){
        userTasks.add(task);
    }

    public void deleteTask(int taskIndex){
        userTasks.remove(taskIndex);
    }

    public Task getUserTasks(int taskIndex) {
        return userTasks.get(taskIndex);
    }

    public int getTaskListSize(){
        return userTasks.size();
    }

    public String listTasks() {
        String message ="";
        System.lineSeparator();
        System.out.println("\tHere are the tasks in your list:");
        if (userTasks.size() == 0) {
            message = "\t\tThe task is empty. Please add a task";
            System.out.println();
        } else {
            for (int i = 0; i < userTasks.size(); i++) {
                message = message + ("\t\t" + (i + 1) + ". " + userTasks.get(i).toString()) + "\n";
            }
        }
        return message;
    }
}
