package sample;

import sample.DataBase.DBQuery;
import sample.DataBase.DBTaskSeter;

public class TaskRemover {
    public void removeTask(int id){
        new DBTaskSeter().updateDate(new DBQuery().deleteTask(id));
    }
}
