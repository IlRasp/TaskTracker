package tasktracker;

import java.lang.StringBuilder;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import com.google.gson.Gson;
import java.time.*;
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
            System.out.println("\u001B[31m" + "An error occurred"  + "\u001B[0m");
        }
    }

    public void save() {
        try (FileWriter flw = new FileWriter("Save.json")){
            flw.write(gsn.toJson(tsk));
        } catch (IOException e) {
            try {
                save.createNewFile();
            } catch (IOException p) {
                System.out.println("\u001B[31m" + "An error occurred"  + "\u001B[0m");
            }
        }
    }

    public void del() {
        System.out.println("\nAre you sure you want to delete the save file? (y/n)");

        if(Objects.equals(scn.nextLine(), "y")) {
            save.delete();
            System.out.println("Save deleted");
        } else if (Objects.equals(scn.nextLine(), "n")) {
            return;
        } else {
            del();
        }
        load();
        i = 1;
    }

    public void help() {
        System.out.println("\nHere is a list of available commands:");
        System.out.println("\u001B[36m" + "\nmktask" + "\u001B[0m" + ": makes new task (add -i to choose a custom id for the task)");
        System.out.println("\u001B[36m" + "ls" + "\u001B[0m" + ": lists all tasks (add -t, -p, -d to filter between To do In progress and Done tasks)");
        System.out.println("\u001B[36m" + "more" + "\u001B[0m" + ": more information about a task");
        System.out.println("\u001B[36m" + "rm" + "\u001B[0m" + ": removes a task (add -t -p -d to delete all tasks with the chosen status)");
        System.out.println("\u001B[36m" + "chtask" + "\u001B[0m" + ": Change the content of a task");
        System.out.println("\u001B[36m" + "chstat" + "\u001B[0m" + ": Sets a task to the To do state (add -t -p -d to change the status of all the tasks with the chosen status)");
        System.out.println("\u001B[36m" + "td" + "\u001B[0m" + ": sets a task to 'To do'");
        System.out.println("\u001B[36m" + "prg" + "\u001B[0m" + ": sets a task to 'In progress'");
        System.out.println("\u001B[36m" + "dn" + "\u001B[0m" + ": sets a task to 'Done'");
        System.out.println("\u001B[36m" + "save" + "\u001B[0m" + ": manually saves");
        System.out.println("\u001B[36m" + "load" + "\u001B[0m" + ": manually loads");
        System.out.println("\u001B[36m" + "del" + "\u001B[0m" + ": deletes the save file");
        System.out.println("\u001B[36m" + "clr" + "\u001B[0m" + ": clears the console");
        System.out.println("\u001B[36m" + "stop" + "\u001B[0m" + ": terminates the program");
    }

    public void mktask() {

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = time.format(form);

        StringBuilder id = new StringBuilder();
        int zero = 4 - String.valueOf(i).length();
        for (int j = 0; j < zero; j++) {
            id.append("0");
        }
        id.append(String.valueOf(i));

        String description;
        System.out.println("\nType the task's description: ");
        description = scn.nextLine();

        tsk[0] = new Task();
        tsk[i] = new Task();
        tsk[i].id = id.toString();
        tsk[i].status = 0;
        tsk[i].description = description;
        tsk[i].creationTime = formattedTime;
        tsk[i].modTime = "No modifications made";
        tsk[0].status = i;

        save();

        i++;
    }

    public void mktaski() {

        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = time.format(form);

        System.out.println("\nType the task's id: ");
        try {
            int inputId = scn.nextInt();
            int zero = 4 - String.valueOf(inputId).length();
            StringBuilder id = new StringBuilder();

            if (inputId >= 1 && inputId <= 9999) {
                for (int j = 0; j < zero; j++) {
                    id.append("0");
                }
                id.append(String.valueOf(inputId));
            }

            String description;
            scn.nextLine();
            System.out.println("\nType the task's description: ");
            description = scn.nextLine();

            tsk[0] = new Task();
            tsk[inputId] = new Task();
            tsk[inputId].id = id.toString();
            tsk[inputId].status = 0;
            tsk[inputId].description = description;
            tsk[inputId].creationTime = formattedTime;
            tsk[inputId].modTime = "No modifications made";
            tsk[0].status = Math.max(inputId, i) + 1;
            i = Math.max(inputId, i) + 1;

            save();

        } catch (InputMismatchException e) {
            System.out.println("\u001B[31mInvalid id\u001B[0m");
            mktaski();
        }
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
                    status = "\u001B[31m" + "To do" + "\u001B[0m";
                    break;
                case 1:
                    status = "\u001B[33m" + "In progress" + "\u001B[0m";
                    break;
                case 2:
                    status = "\u001B[32m" + "Done" + "\u001B[0m";
                default:
                    break;
            }
            System.out.println("\u001B[90m" + tsk[j].id + "\u001B[0m" + "  " + tsk[j].description + "\u001B[90m" + " -> " + "\u001B[0m" + status);
        }
    }

    public void lst() {
        System.out.println();
        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j] != null && tsk[j].status == 0) {
                System.out.println("\u001B[90m" + tsk[j].id + "\u001B[0m" + "  " + tsk[j].description);
            }
        }
    }

    public void lsp() {
        System.out.println();
        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j] != null && tsk[j].status == 1) {
                System.out.println("\u001B[90m" + tsk[j].id + "\u001B[0m" + "  " + tsk[j].description);
            }
        }
    }

    public void lsd() {
        System.out.println();
        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j] != null && tsk[j].status == 2) {
                System.out.println("\u001B[90m" + tsk[j].id + "\u001B[0m" + "  " + tsk[j].description);
            }
        }
    }

    public void more() {
        System.out.println("\nType the id of the task you want check: ");
        int id = Integer.parseInt(scn.nextLine());
        String status = "";
        if (tsk[id] != null) {
            switch (tsk[id].status) {
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
            System.out.println("\n" + tsk[id].id + "    " + tsk[id].description + " -> " + status + " (" + tsk[id].status + ")  |  " + tsk[id].creationTime + " -- " + tsk[id].modTime);
        } else {
            System.out.println("\u001B[31m" + "The task doesn't exist" + "\u001B[0m");
        }
    }

    public void rm() {
        System.out.println("\nType the id of the task you want to delete: ");
        int id = Integer.parseInt(scn.nextLine());
        tsk[id] = null;
        System.out.println("Task successfully removed");
        save();
    }

    public void rmt() {
        System.out.println("\nAre you sure you want to delete all" + "\u001B[31m" + " 'To do' " + "\u001B[0m" + "tasks? (y/n)");

        if(Objects.equals(scn.nextLine(), "y")) {

            for (int j = 1; j <= tsk[0].status; j++) {
                if (tsk[j] != null && tsk[j].status == 0) {
                    tsk[j] = null;
                    System.out.println("Tasks successfully removed");
                }
            }

        } else if (Objects.equals(scn.nextLine(), "n")) {

            return;

        } else {

            rmt();

        }
        save();
    }

    public void rmp() {
        System.out.println("\nAre you sure you want to delete all" + "\u001B[33m" + " 'In progress' " + "\u001B[0m" + "tasks? (y/n)");

        if(Objects.equals(scn.nextLine(), "y")) {

            for (int j = 1; j <= tsk[0].status; j++) {
                if (tsk[j] != null && tsk[j].status == 1) {
                    tsk[j] = null;
                    System.out.println("Tasks successfully removed");
                }
            }

        } else if (Objects.equals(scn.nextLine(), "n")) {

            return;

        } else {

            rmp();

        }
        save();
    }

    public void rmd() {
        System.out.println("\nAre you sure you want to delete all" + "\u001B[32m" + " 'Done' " + "\u001B[0m" + "tasks? (y/n)");

        if(Objects.equals(scn.nextLine(), "y")) {

            for (int j = 1; j <= tsk[0].status; j++) {
                if (tsk[j] != null && tsk[j].status == 2) {
                    tsk[j] = null;
                    System.out.println("Tasks successfully removed");
                }
            }

        } else if (Objects.equals(scn.nextLine(), "n")) {

            return;

        } else {

            rmd();

        }
        save();
    }

    public void chtask() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = time.format(form);

        System.out.println("\nType the id of the task you want to change the content of: ");
        int id = Integer.parseInt(scn.nextLine());

        if (tsk[id] != null) {
            System.out.println("\nType the new description: ");
            tsk[id].description = scn.nextLine();
            tsk[id].modTime = formattedTime;
        } else {
            System.out.println("\u001B[31m" + "The task doesn't exist" + "\u001B[0m");
        }
        save();
    }

    public void chstat() {
        System.out.println("\nType the id of the task you want to change the status of: ");
        int id = Integer.parseInt(scn.nextLine());

        if (tsk[id] != null) {
            System.out.println("\nType the new status (To do, In progress, Done): ");

            boolean valid = true;

            switch (scn.nextLine()) {
                case "0", "To do":
                    tsk[id].status = 0;
                    break;
                case "1", "In progress":
                    tsk[id].status = 1;
                    break;
                case "2", "Done":
                    tsk[id].status = 2;
                    break;
                default:
                    System.out.println("\u001B[31mInvalid Status\u001B[0m");
                    valid = false;
            }

            if (valid) {
                System.out.println("\nStatus changed successfully");
            }

        } else {
            System.out.println("\u001B[31mThe task doesn't exist\u001B[0m");
        }

        save();
    }

    public void chstatt() {

        System.out.println("\nType the new status (To do, In progress, Done): ");
        String status = scn.nextLine();
        boolean valid = true;

        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j] != null && tsk[j].status == 0) {
                switch (status) {
                    case "0", "To do":
                        tsk[j].status = 0;
                        break;
                    case "1", "In progress":
                        tsk[j].status = 1;
                        break;
                    case "2", "Done":
                        tsk[j].status = 2;
                        break;
                    default:
                        System.out.println("\u001B[31mInvalid Status\u001B[0m");
                        valid = false;
                }
            }
        }
        if (valid) {
            System.out.println("\nStatus changed successfully");
        }
        save();
    }

    public void chstatp() {
        System.out.println("\nType the new status (To do, In progress, Done): ");
        String status = scn.nextLine();
        boolean valid = true;

        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j] != null && tsk[j].status == 1) {
                switch (status) {
                    case "0", "To do":
                        tsk[j].status = 0;
                        break;
                    case "1", "In progress":
                        tsk[j].status = 1;
                        break;
                    case "2", "Done":
                        tsk[j].status = 2;
                        break;
                    default:
                        System.out.println("\u001B[31mInvalid Status\u001B[0m");
                        valid = false;
                }
            }
        }
        if (valid) {
            System.out.println("\nStatus changed successfully");
        }
        save();
    }

    public void chstatd() {
        System.out.println("\nType the new status (To do, In progress, Done): ");
        String status = scn.nextLine();
        boolean valid = true;

        for (int j = 1; j <= tsk[0].status; j++) {
            if (tsk[j] != null && tsk[j].status == 2) {
                switch (status) {
                    case "0", "To do":
                        tsk[j].status = 0;
                        break;
                    case "1", "In progress":
                        tsk[j].status = 1;
                        break;
                    case "2", "Done":
                        tsk[j].status = 2;
                        break;
                    default:
                        System.out.println("\u001B[31mInvalid Status\u001B[0m");
                        valid = false;
                }
            }
        }
        if (valid) {
            System.out.println("\nStatus changed successfully");
        }
        save();
    }

    public void td() {
        System.out.println("\nType the id of the task you want to change to the 'To do' status: ");
        int id = Integer.parseInt(scn.nextLine());

        if (tsk[id] != null) {
            tsk[id].status = 0;
        } else {
            System.out.println("\u001B[31mThe task doesn't exist\u001B[0m");
        }
    }

    public void prg() {
        System.out.println("\nType the id of the task you want to change to the 'In progress' status: ");
        int id = Integer.parseInt(scn.nextLine());

        if (tsk[id] != null) {
            tsk[id].status = 1;
        } else {
            System.out.println("\u001B[31mThe task doesn't exist\u001B[0m");
        }
    }

    public void dn() {
        System.out.println("\nType the id of the task you want to change to the 'Done' status: ");
        int id = Integer.parseInt(scn.nextLine());

        if (tsk[id] != null) {
            tsk[id].status = 2;
        } else {
            System.out.println("\u001B[31mThe task doesn't exist\u001B[0m");
        }
    }

}
