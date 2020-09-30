package sample;

public class TaskRemover {
    public void removeTask(int id){
        new DBTaskSeter().updateDate(new DBQuery().deleteTask(id));
    }
}
