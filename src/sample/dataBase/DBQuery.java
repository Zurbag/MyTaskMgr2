package sample.dataBase;

import sample.TaskDate;

public class DBQuery {

    //Поиск задачи по имени
    public String getTaskLikeName(String name){
        return ("SELECT * FROM `tasks` WHERE `name` LIKE '%"+name+"%'");
    }

    //Показать задачи по интексу
    public String getOneTaskForIndex(int idForQuery){
        return "SELECT * FROM tasks where id=" + idForQuery + ";";
    }

    //Показать все залачи
    public String getAllTask(){
        return "SELECT * FROM tasks";
    }

    //Показать все задачи без статуса False
    public String getAllTaskWithoutStatusFalse(){
        return "SELECT * FROM tasks where status = true";
    }

    //Показать задачи с определенной датой
    public String getTasksForThisDate(String date){
        return "SELECT * FROM tasks where (dateFinish <= \'"+date+"\') and (status = true) ;";
    }

    //Создание задачи
    public String createTask(String name, String dateFinish){
        return ("INSERT INTO tasks (status, name, dateCreate, dateFinish) values( true, '" + name + "', '" + new TaskDate().getTodayDateString() + "', '" + dateFinish + "')");
    }

    //Добавление отредактированной задачм
    public String editTask(int id,Boolean status, String name, String dateFinish){
        return ("UPDATE tasks SET name = '"+name+"', status = "+status+", dateFinish = '"+dateFinish+"' WHERE id = "+id);
    }

    //Удаление задачи по id
    public String deleteTask(int id){
        return ("DELETE FROM `tasks` WHERE `id` = " + id + ";");
    }

}
