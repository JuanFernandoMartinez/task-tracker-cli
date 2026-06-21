# Task Tracker

Task tracker is a Command Line interface for task management. which can help you to create, update, read and delete tasks, furthermore this app allows you to move task amoung 3 statuses ***TODO*** -> ***In Progress*** -> ***Done***. all tasks keep saved in user data folder so that tasks always remaind available after running application.
## Intructions for running

```powershell
# compile project
javac -d bin .\src\main\TaskTracker.java    

# package project 
jar cfe task-tracker.jar src.main.java -C bin .
```

### Running instructions
```powershell
# Show all tasks
java -jar task-tracker.jar all

# Create a new task
java -jar task-tracker.jar add "Some task title"

# Change task status
java -jar task-tracker.jar move 0 DONE

# Update task description
java -jar task-tracker.jar update 0 "Other task title"

# Show specific task
java -jar task-tracker.jar show 0

# Delete task
java -jar task-tracker.jar remove 0
```

Project taken from https://roadmap.sh/projects/task-tracker
