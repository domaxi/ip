package task;

import exceptions.DukeException;
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
}
