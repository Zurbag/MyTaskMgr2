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

    //Создание проекта
//    public String createProject(String name, String dateFinish){
//        return ("INSERT INTO project (status, name, dateCreate, dateFinish) values( true, '" + name + "', '"  "', '" + dateFinish + "')");
//    }

    //Создание проекта
    public String createProject(String name, String dateFinish, String description){
        return ("INSERT INTO project (status, name, dateCreate, dateFinish, description) values( true, '" +name+"'," +
                " '"+ new TaskDate().getTodayDateString() +"', '"+dateFinish+"', '"+description+"')");
    }




    //Добавление отредактированной задачм
    public String editTask(int id,Boolean status, String name, String dateFinish){
        return ("UPDATE tasks SET name = '"+name+"', status = "+status+", dateFinish = '"+dateFinish+"' WHERE id = "+id);
    }

    //Удаление задачи по id
    public String deleteTask(int id){
        return ("DELETE FROM `tasks` WHERE `id` = " + id + ";");
    }

    //Показать все проекты
    public String getAllProject(){
        return "SELECT * FROM project";
    }

    //Отобразить задучу и проект LEFT OUTER JOIN
    public String getAllTaskWithProject(){
        return "SELECT tasks.id, tasks.status, tasks.name, tasks.dateCreate, tasks.dateFinish, project.name FROM tasks LEFT OUTER JOIN project ON project.id = tasks.project_id";
    }
}
