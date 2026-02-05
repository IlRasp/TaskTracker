# TaskTracker ✔️

**IlRasp's TLCli** is a task tracker made in jave and uses command line interface

---

## Project content 🗂️

* `out/tasktracker/` -> Contains compiled files (.class)
* `src/` -> Cantains Java source code
* `TaskTracker.jar` -> Java archive of the project
* `TaskTracker.bat` -> Executable file to quickly run the program

## How to run the application 🚀

1. Download the latest version
2. Double click on TaskTracker.bat
3. Make sure you have the latest java version installed and that TaskTracker.bat is in the same folder as TaskTracker.jar and gson-2.13.2.jar

## Manual 📖

### mktask

> Creates a new task

Type *'mhtask'* in the command line to create a new task then type the description of the task at it will be saved on the SAVE.json file with a default status of 0 (To do)

##### Modifiers

* -i Creates a task with a custom id

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

##### modifiers


* -t Removes all To do tasks (0)
* -p Removes all In progress tasks (1)
* -d Removes all Done tasks (2)

### chtask

> Changes the description of a task

Type *'chtask'* then the task's id and the new description you want to replace the old one with

### chstat

> Changes the status of a task

Type *'chstat'* then the task's id and write the new status (To do, In progress, Done) or it's corresponding number (0 = To do, 1 = In progress, 2 = Done)

##### modifiers


* -t Changes the status of all To do tasks to the chosen one (0)
* -p Changes the status of all In progress tasks to the chosen one (1)
* -d Changes the status of all Done tasks to the chosen one (2)

### td

> Changes the status of a task to To do

Type *'td'* then the tasks id to change it's status to To do (0)

### prg

> Changes the status of a task to In progress

Type *'td'* then the tasks id to change it's status to In progress (1)

### dn

> Changes the status of a task to Done

Type *'td'* then the tasks id to change it's status to Done (2)

### save

> Saves the tasks

Type *'save'* to manually save all the tasks to Save.json

### load

> Loads the tasks

Type *'load'* to manually load the tasks from Save.json

### del

> Deletes all saved tasks

Type *'del'* to delete Save.json

### clr

> Clears the terminal

Type *'clr'* to clear the terminal (Only works inside CMD PowerShell and Linux terminal doesn't work in IDE's terminals)

### stop

> Stops the program

Simply stops the program
