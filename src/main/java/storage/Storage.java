package storage;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String dataPath = "/data/";
    private static String filePath = "data/tasks.txt";
    File taskDirectory;
    File taskFile;

    FileWriter fileWriter;

    public TaskList load(){
        File readFile = new File(filePath);
        TaskList loadedTask = new TaskList();
        try{
            Scanner fileScanner = new Scanner(readFile);
            while(fileScanner.hasNext()){
                loadTask(fileScanner.nextLine(),loadedTask);
            }
        }catch (FileNotFoundException e){
            createFile(filePath);
        }
        return loadedTask;
    }

    private void loadTask(String task, TaskList loadedTask) {
        String[] taskParsed = task.split(" ");
        Task newTask = new Task();

        char taskType = task.charAt(1);
        char taskDone = task.charAt(4);
        String taskDescription = taskParsed[1];
        String taskDetail = task.substring(task.indexOf(":"));

        if(taskType == 'T'){
            newTask = new Todo(taskDescription);
        }else if (taskType == 'D'){
            newTask = new Deadline(taskDescription,taskDetail);
        }else if (taskType == 'E'){
            newTask = new Event(taskDescription, taskDetail);
        }

        if(taskDone == '✓'){
            newTask.setDone();
        }
        //loads the new task into the task.
        loadedTask.addTask(newTask);
    }

    public Storage(String filePath){
        this.
        taskDirectory = new File(dataPath);
        taskFile = new File(filePath);

        if(!taskDirectory.exists()){
            createDirectory(dataPath);
        }
        if(!taskFile.exists()){
            createFile(filePath);
        }

        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDirectory(String directoryPath){
        File outputDirectory = new File(directoryPath);
        outputDirectory.mkdirs();
    }

    public static void createFile(String filePath){
        File fileDirectory = new File(filePath);
        try{
            fileDirectory.createNewFile();
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveFile(TaskList task) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for(int i=0; i<task.getTaskListSize(); i++){
            fileWriter.write(task.getUserTasks(i).toString() + "\n");
        }
        fileWriter.close();
    }
}
