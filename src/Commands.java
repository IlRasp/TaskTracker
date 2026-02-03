import java.lang.StringBuilder;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class Commands {

    File save = new File("Save.json");

    Scanner scn = new Scanner(System.in);
    Task[] tsk = new Task[9999];
    int i = 1;
    StringBuilder stb = new StringBuilder();
    Gson gsn = new Gson();

    public void load() {

        tsk[0] = new Task();
        tsk[i] = new Task();

        if (!save.exists()) {
            return;
        }

        try (FileReader flr = new FileReader(save)) {
            Task[] loaded = gsn.fromJson(flr, Task[].class);
            if (loaded != null) {
                tsk = loaded;
                if (tsk[0] != null) {
                    i = tsk[0].status + 1;
                } else {
                    i = 1;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    public void save() {
        try (FileWriter flw = new FileWriter("Save.json")){
            flw.write(gsn.toJson(tsk));
        } catch (IOException e) {
            try {
                save.createNewFile();
            } catch (IOException p) {
                System.out.println("An error occurred");
            }
        }
    }

    public void help() {
        System.out.println("\nHere is a list of available Commands:");
        System.out.println("mktask: makes new task");
        System.out.println("ls: lists all tasks (add -t, -p, -d to filter between To do In progress and Done tasks)");
        System.out.println("rm: removes a task");
        System.out.println("chtask: Change the content of a task");
        System.out.println("chstat: Sets a task to the To do state");
        System.out.println("stop: Terminates the program");
    }

    public void mktask() {

        StringBuilder id = new StringBuilder();
        int zero = 4 - String.valueOf(i).length();
        for (int j = 0; j < zero; j++) {
            id.append("0");
        }
        id.append(String.valueOf(i));

        String description;
        System.out.println("\nType the task's description");
        description = scn.nextLine();

        tsk[0] = new Task();
        tsk[i] = new Task();
        tsk[i].id = id.toString();
        tsk[i].status = 0;
        tsk[i].description = description;
        tsk[0].status = i;

        save();

        i++;
    }

    public void ls() {
        System.out.println();
        String status = "";
        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j] == null) {
                continue;
            }
            switch (tsk[j].status) {
                case 0:
                    status = "To do";
                    break;
                case 1:
                    status = "In progress";
                    break;
                case 2:
                    status = "Done";
                default:
                    break;
            }
            System.out.println(tsk[j].id + "    " + tsk[j].description + " -> " + status);
        }
    }

    public void lst() {
        System.out.println();
        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j].status == 0 && tsk[j] != null) {
                System.out.println(tsk[j].id + "    " + tsk[j].description);
            }
        }
    }

    public void lsp() {
        System.out.println();
        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j].status == 1 && tsk[j] != null) {
                System.out.println(tsk[j].id + "    " + tsk[j].description);
            }
        }
    }

    public void lsd() {
        System.out.println();
        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j].status == 2 && tsk[j] != null) {
                System.out.println(tsk[j].id + "    " + tsk[j].description);
            }
        }
    }

    public void rm() {
        System.out.println("\nType the id of the task you want to delete");
        int id = Integer.parseInt(scn.nextLine());
        tsk[id] = null;
        save();
    }

    public void chtask() {
        System.out.println("\nType the id of the task you want to change the content of");
        int id = Integer.parseInt(scn.nextLine());
        System.out.println("\nType the new description");
        tsk[id].description = scn.nextLine();
        save();
    }

    public void chstat() {
        System.out.println("\nType the id of the task you want to change the status of");
        int id = Integer.parseInt(scn.nextLine());
        System.out.println("\nType the new status (To do, In progress, Done)");
        switch (scn.nextLine()) {
            case "To do":
                tsk[id].status = 0;
                System.out.println("\nStatus changed successfully");
                break;
            case "In progress":
                tsk[id].status = 1;
                System.out.println("\nStatus changed successfully");
                break;
            case "Done":
                tsk[id].status = 2;
                System.out.println("\nStatus changed successfully");
                break;
            default:
                System.out.println("Invalid Status");
                break;
        }
    }
}
