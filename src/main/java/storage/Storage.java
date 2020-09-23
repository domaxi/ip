package storage;

import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String dataPath = "/data/";
    public String filePath;

    public Storage(){

    }

    public TaskList load(){
        /**
         * reads from a @storage item.
         */
        File readFile = new File(this.filePath);
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
        String[] taskParsed = task.split("|");
        Task newTask = new Task();
        String taskType = taskParsed[0];
        String taskDone = taskParsed[1];
        String taskDescription = taskParsed[2];
        String taskDetail = taskParsed[3];

        if(taskType.equals("T")){
            newTask = new Todo(taskDescription);
        }else if (taskType.equals("D")){
            newTask = new Deadline(taskDescription,taskDetail);
        }else if (taskType.equals("E")){
            newTask = new Event(taskDescription, taskDetail);
        }

        /**
         * sets the @task.isDone to true if the data input is Done.
         */
        if(taskDone.equals("✓")){
            newTask.setDone();
        }
        //loads the new task into the task.
        loadedTask.addTask(newTask);
    }

    public Storage(String filePath) {
        this.filePath = filePath;
        File taskDirectory = new File(dataPath);
        File taskFile = new File(filePath);
        /**
         * Creates a new directory and file if the directory (and) or file doesnt exist.
         */
        if(!taskDirectory.exists()){
            createDirectory(dataPath);
        }
        if(!taskFile.exists()){
            createFile(filePath);
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

    public static void writeFile(String fileDirectory, TaskList task) throws IOException {
        FileWriter fileWriter = new FileWriter(fileDirectory);
        fileWriter.close();
    }

    private static void printSuccessfulSave(boolean isFileExist, boolean isDirectoryCreated, boolean isFileCreated) {
        System.lineSeparator();
        if (isFileExist) {
            System.out.println("\tThe file already exist. Overwriting the file");
        } else {
            System.out.println("\tThe file doesnt exist. Creating a new file");
            if (isDirectoryCreated && isFileCreated) {
                System.out.println("\tThe new directory and file has ben created successfully");
            }
        }
        System.out.println("\tThe file has been saved successfully");
        System.lineSeparator();
    }

}
