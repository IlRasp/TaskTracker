# TaskTracker ✔️

**IlRasp's TLCli** is a task tracker made in jave and uses command line interface

---

## Project content 🗂️

* `out/production/TaskTracker/` -> Contains compiled files (.class)
* `src/` -> Cantains Java source code

## How to run the application 🚀

1. Open the project in your favourite txt editor
2. Import the Gson library
3. Run TaskList.java

## Manual 📖

### mhtask

> Creates a new task

Type *'mhtask'* in the command line to create a new task then type the description of the task at it will be saved on the SAVE.json file with a default status of 0 (To do)

### ls

> Shows a list of tasks

Type *'ls'* to see a list of all tasks. Displays:

`ID   Description -> Status`

##### Modifiers

* -t Shows only tasks with a To do status (0)
* -p Shows only tasks with an In progress status (1)
* -d Shows only tasks with a Done status (2)

### rm

> Removes a task based on it's id

Type *'rm'* then the task's id to delete it

### chtask

> Changes the description of a task

Type *'chtask'* then the task's id and the new description you want to replace the old one with

### chstat

> Changes the status of a task

Type *'chstat'* then the task's id and the new status (To do, In progress, Done)

### stop

> Stops the program

Simply stops the program
