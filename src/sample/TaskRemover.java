package sample;

import sample.dataBase.DBQuery;
import sample.dataBase.DBTaskSeter;

public class TaskRemover {
    public void removeTask(int id){
        new DBTaskSeter().updateDate(new DBQuery().deleteTask(id));
    }
}
