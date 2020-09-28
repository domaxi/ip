package task;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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

    public String getSearchResult(String searchPhrase) {
        return userTasks.stream()
                .filter((s) -> s.getTaskName().contains(searchPhrase))
                .map(Object::toString)
                .collect(Collectors.joining(" \n\t\t"));
    }
}
