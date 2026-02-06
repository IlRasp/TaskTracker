package tasktracker;

import java.util.Scanner;
import java.io.*;

public class TaskList {

    static Commands cmd = new Commands();
    static boolean running = true;

    public static void main(String[] args) {

        cmd.load();

        Scanner scn = new Scanner(System.in);

        System.out.println("\u001B[33m" + "Welcome To IlRasp's TLCli version: 1.7.4" + "\u001B[0m");

        while (running) {

            System.out.println("\n'help' for a list of commands");
            System.out.print("> \u001b[32m");
            currentCmd(scn.nextLine());
        }
    }

    static void currentCmd(String command) {

        switch (command.trim()) {
            case "help":
                System.out.print("\u001b[0m");
                cmd.help();
                break;
            case "mktask":
                System.out.print("\u001b[0m");
                cmd.mktask();
                break;
            case "mktask -i":
                System.out.print("\u001b[0m");
                cmd.mktaski();
                break;
            case "ls":
                System.out.print("\u001b[0m");
                cmd.ls();
                break;
            case "ls -t":
                System.out.print("\u001b[0m");
                cmd.lst();
                break;
            case "ls -p":
                System.out.print("\u001b[0m");
                cmd.lsp();
                break;
            case "ls -d":
                System.out.print("\u001b[0m");
                cmd.lsd();
                break;
            case "more":
                System.out.print("\u001b[0m");
                cmd.more();
                break;
            case "rm":
                System.out.print("\u001b[0m");
                cmd.rm();
                break;
            case "rm -t":
                System.out.print("\u001b[0m");
                cmd.rmt();
                break;
            case "rm -p":
                System.out.print("\u001b[0m");
                cmd.rmp();
                break;
            case "rm -d":
                System.out.print("\u001b[0m");
                cmd.rmd();
                break;
            case "chtask":
                System.out.print("\u001b[0m");
                cmd.chtask();
                break;
            case "chstat":
                System.out.print("\u001b[0m");
                cmd.chstat();
                break;
            case "chstat -t":
                System.out.print("\u001b[0m");
                cmd.chstatt();
                break;
            case "chstat -p":
                System.out.print("\u001b[0m");
                cmd.chstatp();
                break;
            case "chstat -d":
                System.out.print("\u001b[0m");
                cmd.chstatd();
                break;
            case "td":
                System.out.print("\u001b[0m");
                cmd.td();
                break;
            case "prg":
                System.out.print("\u001b[0m");
                cmd.prg();
                break;
            case "dn":
                System.out.print("\u001b[0m");
                cmd.dn();
                break;
            case "save":
                System.out.print("\u001b[0m");
                cmd.save();
                break;
            case "load":
                System.out.print("\u001b[0m");
                cmd.load();
                break;
            case "del":
                System.out.print("\u001b[0m");
                cmd.del();
                break;
            case "exit":
                System.out.print("\u001b[0m");
                running = false;
                break;
            case "clr", "clear", "cls":
                System.out.print("\u001b[0m");
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
                    for (int i = 0; i < 50; i++) {
                        System.out.println();
                    }
                }
                break;
            default:
                System.out.print("\u001b[0m");
                System.out.println("\u001B[31m" + "\nInvalid Command" + "\u001B[0m");
                break;
        }
    }
}
