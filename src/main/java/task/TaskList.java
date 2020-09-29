package task;

import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class TaskList {

    private static ArrayList<Task> userTasks  = new ArrayList<>();

    public TaskList(){
    }

    /**
     * Initializes the TaskList class
     * @param load gets the TaskList from the Storage class
     */
    public TaskList(TaskList load) {
        userTasks = load.getTaskList();
    }

    /**
     * Adds a task into the taskList
     * @param task the task to add to the taskList
     */
    public void addTask(Task task){
        userTasks.add(task);
    }

    /**
     * deletes a task from the taskList with the taskIndex as the input.
     * @param taskIndex the index of the task to be deleted.
     */
    public void deleteTask(int taskIndex){
        userTasks.remove(taskIndex);
    }

    /**
     * Gets a task from the taskList
     * @param taskIndex task index is the input to get the task from the taskList
     * @return task returns a Task from the taskList
     */
    public Task getUserTasks(int taskIndex) {
        return userTasks.get(taskIndex);
    }

    /**
     * Gets the size of the taskList
     * @return int the size of the taskList
     */
    public int getTaskListSize(){
        return userTasks.size();
    }

    /**
     * @return gets the userTask arraylist
     */
    public ArrayList<Task> getTaskList(){
        return userTasks;
    }

    /**
     * gets the search result from the taskList class
     * @param searchPhrase is a String that contains the search keyword
     * @return returns a String that contains all the task from the search
     */
    public String getSearchResult(String searchPhrase) {
        return userTasks.stream()
                .filter((s) -> s.getTaskName().contains(searchPhrase))
                .map(Object::toString)
                .collect(Collectors.joining(" \n\t\t"));
    }

}
