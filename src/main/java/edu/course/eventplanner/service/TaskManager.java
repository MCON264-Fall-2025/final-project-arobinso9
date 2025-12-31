package edu.course.eventplanner.service;

import edu.course.eventplanner.model.Task;
import java.util.*;

public class TaskManager {
    private final Queue<Task> upcoming = new LinkedList<>();
    private final Stack<Task> completed = new Stack<>();
    public void addTask(Task task) {
        if(task!=null){
            upcoming.add(task);
        }
    }
    public Task executeNextTask() {
        if(upcoming.isEmpty()) {
            return null;
        }
        //execute the next task in line, and then add it to the completed stack
        // so we have undo functionality for later
        Task removed= upcoming.remove();
        completed.push(removed);
        return removed;
    }
    public Task undoLastTask() {
        if(completed.isEmpty())
            return null;
        //SHOULD I ADD IT TO THE END OF THE QUEUE OR NOT???
        // undo the most recent task and then add it to the end of the upcoming task to do list.
        Task taskUndone= completed.pop();
        upcoming.add(taskUndone);
        return taskUndone;
    }
    public int remainingTaskCount() {
        return upcoming.size();
    }
}
