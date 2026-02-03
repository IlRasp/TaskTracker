import java.util.Scanner;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TaskList {

    static Commands cmd = new Commands();
    static boolean running = true;

    public static void main(String args[]) {

        cmd.load();

        Scanner scn = new Scanner(System.in);

        System.out.println("Welcome To IlRasp's TLCli version: 1.0.1");

        while (running) {

            System.out.println("\n'help' for a list of commands");
            currentCmd(scn.nextLine());
        }
    }

    static void currentCmd(String command) {

        switch (command) {
            case "help":
                cmd.help();
                break;
            case "mktask":
                cmd.mktask();
                break;
            case "ls":
                cmd.ls();
                break;
            case "ls -t":
                cmd.lst();
                break;
            case "ls -p":
                cmd.lsp();
                break;
            case "ls -d":
                cmd.lsd();
                break;
            case "rm":
                cmd.rm();
                break;
            case "chtask":
                cmd.chtask();
                break;
            case "chstat":
                cmd.chstat();
                break;
            case "stop":
                running = false;
                break;
            default:
                System.out.println("\nInvalid Command");
                break;
        }
    }
}
