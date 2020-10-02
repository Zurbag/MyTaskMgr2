package sample.dataBase;

import sample.TaskDate;

public class DBQuery {

    public String getOneTaskForIndex(int idForQuery){
        return "SELECT * FROM tasks where id=" + idForQuery + ";";
    }

    public String getAllTask(){
        return "SELECT * FROM tasks";
    }
    public String getAllTaskWithoutStatusFalse(){
        return "SELECT * FROM tasks where status = true";
    }

    public String getTasksForThisDate(String date){
        //return "SELECT * FROM tasks where dateFinish <=" + date + ";";
        return "SELECT * FROM tasks where (dateFinish <= \'"+date+"\') and (status = true) ;";
    }

    public String createTask(String name, String dateFinish){
        return ("INSERT INTO tasks (status, name, dateCreate, dateFinish) values( true, '" + name + "', '" + new TaskDate().getTodayDateString() + "', '" + dateFinish + "')");
    }

    public String editTask(int id,Boolean status, String name, String dateFinish){
        return ("UPDATE tasks SET name = '"+name+"', status = "+status+", dateFinish = '"+dateFinish+"' WHERE id = "+id);
    }
//    public String editTask(int id,Boolean status, String name, String dateFinish){
//        return ("UPDATE tasks SET name = '"+name+"', status = "+status+", dateFinish = '"+dateFinish+"' WHERE id = "+id);
//    }

    public String deleteTask(int id){
        return ("DELETE FROM `tasks` WHERE `id` = " + id + ";");
    }

}
