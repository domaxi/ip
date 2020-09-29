package storage;

import task.*;

import java.io.*;
import java.util.Scanner;

public class Storage {
    private static String filePath_;
    private static String fileDirectory_;
    private final File TaskListFile;

    /**
     * Initializes the storage class
     * The method will create a directory, if it doesnt exist
     * The method will also create a file, if it doesnt exist
     * @param filePath is the path in which the file exist
     */
    public Storage(String filePath) {
        //Creates the file and the directory if it doesnt exist
        Storage.filePath_ = filePath;
        Storage.fileDirectory_ = filePath.substring(0,filePath.indexOf("/"));

        TaskListFile = new File(filePath_);
        File taskDirectory = new File(fileDirectory_);

        if(!TaskListFile.exists()){
            try {
                taskDirectory.mkdirs();
                TaskListFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Loads the task from the file
     * @return returns a taskList class loaded from the task.txt file.
     */
    public TaskList loadTaskList() {
        TaskList loadedTask = new TaskList();
        try {
            Scanner taskScanner = new Scanner(TaskListFile);
            while(taskScanner.hasNext()){
                loadTask(taskScanner.nextLine(),loadedTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadedTask;
    }

    /**
     * Processes the file by separating the tasks line by line.
     * Categorizes the tasks into Deadline, Event, and Todo
     * Creates each of the object and adding it into the loadedTask class.
     * @param taskData is a line of String that contains the data of the tasks.
     * @param loadedTask is a TaskList object that will load the tasks from the file.
     */
    private void loadTask(String taskData, TaskList loadedTask) {
        Task newTask = new Task();

        String parsedTaskData = taskData.substring(7);

        char taskType = taskData.charAt(1);
        char taskDone = taskData.charAt(4);

        if(taskType == 'T'){
            newTask = new Todo(parsedTaskData);
        }else {
            String taskDescription = taskData.substring(7,taskData.indexOf("(")-1);
            String taskDetail = taskData.substring(taskData.indexOf(":") + 2,taskData.length()-1);
            if (taskType == 'D'){
                newTask = new Deadline(taskDescription,taskDetail);
            }else if (taskType == 'E'){
                newTask = new Event(taskDescription, taskDetail);
            }
        }

        if(taskDone == '✓'){
            newTask.setDone();
        }
        //loads the new task into the task.
        loadedTask.addTask(newTask);
    }

    /**
     * Writes the tasks into the file
     * @param taskList is a TaskList which contains the tasks to be saved to the file.
     */
    public void saveFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(TaskListFile);
            for (int i = 0; i < taskList.getTaskListSize(); i++) {
                fileWriter.write(taskList.getUserTasks(i).toString() + "\n");
            }
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
