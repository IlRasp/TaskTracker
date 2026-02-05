package tasktracker;

import java.util.Scanner;
import java.io.*;

public class TaskList {

    static Commands cmd = new Commands();
    static boolean running = true;

    public static void main(String[] args) {

        cmd.load();

        Scanner scn = new Scanner(System.in);

        System.out.println("\u001B[33m" + "Welcome To IlRasp's TLCli version: 1.7.0" + "\u001B[0m");

        while (running) {

            System.out.println("\n'help' for a list of commands");
            System.out.print("> ");
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
            case "mktask -i":
                cmd.mktaski();
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
            case "more":
                cmd.more();
                break;
            case "rm":
                cmd.rm();
                break;
            case "rm -t":
                cmd.rmt();
                break;
            case "rm -p":
                cmd.rmp();
                break;
            case "rm -d":
                cmd.rmd();
                break;
            case "chtask":
                cmd.chtask();
                break;
            case "chstat":
                cmd.chstat();
                break;
            case "chstat -t":
                cmd.chstatt();
                break;
            case "chstat -p":
                cmd.chstatp();
                break;
            case "chstat -d":
                cmd.chstatd();
                break;
            case "td":
                cmd.td();
                break;
            case "prg":
                cmd.prg();
                break;
            case "dn":
                cmd.dn();
                break;
            case "save":
                cmd.save();
                break;
            case "load":
                cmd.load();
                break;
            case "del":
                cmd.del();
                break;
            case "stop":
                running = false;
                break;
            case "clr":
                try {
                    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                        new ProcessBuilder("powershell", "-Command", "Clear-Host")
                                .inheritIO()
                                .start()
                                .waitFor();
                    } else {
                        new ProcessBuilder("clear")
                                .inheritIO()
                                .start()
                                .waitFor();
                    }
                } catch (Exception e) {
                    // fallback
                    for (int i = 0; i < 50; i++) {
                        System.out.println();
                    }
                }
                break;
            default:
                System.out.println("\u001B[31m" + "\nInvalid Command" + "\u001B[0m");
                break;
        }
    }
}
