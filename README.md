# TaskTracker ✔️

**IlRasp's TLCli** is a task tracker made in jave and uses command line interface

---

## Project content 🗂️

* `out/production/TaskTracker/` -> Contains compiled files (.class)
* `src/` -> Cantains Java source code

## How to run the application 🚀

1. Download the latest version
2. Run TaskTracker.jar using this command: `java -jar TaskTracker.jar`
3. if any error occur make sure you are in the folder were you have installed TaskTracker.jar and make sure gson-2.13.2.jar is also present

## Manual 📖

### mhtask

> Creates a new task

Type *'mhtask'* in the command line to create a new task then type the description of the task at it will be saved on the SAVE.json file with a default status of 0 (To do)

### ls

> Shows a list of tasks

Type *'ls'* to see a list of all tasks. Displays:

`ID  Description -> Status`

##### Modifiers

* -t Shows only tasks with a To do status (0)
* -p Shows only tasks with an In progress status (1)
* -d Shows only tasks with a Done status (2)

### more

> Show all the attributes of a tasks based on it's id

Type *'more'* then the tasks id. Displays:

`ID  Description -> Status (Status numerical value)  Creation date and time | Last modification date and time`

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
