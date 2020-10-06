package sample;

import sample.dataBase.DBQuery;
import sample.dataBase.DBTaskSetter;

public class TaskRemover {
    public void removeTask(int id){
        new DBTaskSetter().updateDate(new DBQuery().deleteTask(id));
    }
}
