import command.Command;
import exceptions.DukeException;
import storage.*;
import task.*;
import ui.*;

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = new Command(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }
            catch (DukeException e) {
                ui.printError(e.getMessage());
            }
            finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
