package task;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> userTasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(TaskList load) {
        userTasks = load.getTaskList();
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

    public ArrayList<Task> getTaskList(){
        return userTasks;
    }
}
