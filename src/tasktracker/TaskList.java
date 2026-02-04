package tasktracker;

import java.util.Scanner;
import java.io.*;

public class TaskList {

    static Commands cmd = new Commands();
    static boolean running = true;

    public static void main(String[] args) {

        cmd.load();

        Scanner scn = new Scanner(System.in);

        System.out.println("\u001B[33m" + "Welcome To IlRasp's TLCli version: 1.3.0" + "\u001B[0m");

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
            case "chtask":
                cmd.chtask();
                break;
            case "chstat":
                cmd.chstat();
                break;
            case "stop":
                running = false;
                break;
            case "clr":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                break;
            default:
                System.out.println("\u001B[31m" + "\nInvalid Command" + "\u001B[0m");
                break;
        }
    }
}
