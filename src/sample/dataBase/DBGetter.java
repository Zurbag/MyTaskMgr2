package sample.dataBase;
import sample.Project;
import sample.Task;

import java.sql.*;
import java.util.ArrayList;

public class DBGetter {
    //Получить строки в массив
    public ArrayList<Task> getTasks(String query){
            ArrayList<Task> taskArrayList = new ArrayList<>();

            try {

                DBConnector connectionBD = new DBConnector();//Создали класс конекта
                Class.forName(connectionBD.getDRIVER()).newInstance();//Используем драйвер
                Connection connection = DriverManager.getConnection(connectionBD.getURL(), connectionBD.getUSER_BD(),
                        connectionBD.getPASSWORD_BD());//Создаем обьект подключения
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);//Запрос
                while (resultSet.next()) { //Перебор результата
                    Integer id = Integer.parseInt(resultSet.getString("id"));

                    Boolean status;
                    if (resultSet.getString("status").equals("1")) {
                        status = true;
                    } else {
                        status = false;
                    }
                    String name = resultSet.getString("name");
                    String dateCreate = (resultSet.getString("dateCreate"));
                    String dateFinish = (resultSet.getString("dateFinish"));

                    taskArrayList.add(new Task(id, status, name, dateCreate, dateFinish));
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return taskArrayList;

        }

    //Получить проекты в массив
    public ArrayList<Project> getProject(String query){
        ArrayList<Project> projectArrayList = new ArrayList<>();

        try {

            DBConnector connectionBD = new DBConnector();//Создали класс конекта
            Class.forName(connectionBD.getDRIVER()).newInstance();//Используем драйвер
            Connection connection = DriverManager.getConnection(connectionBD.getURL(), connectionBD.getUSER_BD(),
                    connectionBD.getPASSWORD_BD());//Создаем обьект подключения
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);//Запрос
            while (resultSet.next()) { //Перебор результата
                Integer id = Integer.parseInt(resultSet.getString("id"));

                Boolean status;
                if (resultSet.getString("status").equals("1")) {
                    status = true;
                } else {
                    status = false;
                }
                String name = resultSet.getString("name");
                String dateCreate = (resultSet.getString("dateCreate"));
                String dateFinish = (resultSet.getString("dateFinish"));

                projectArrayList.add(new Project(id, status, name, dateCreate, dateFinish));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return projectArrayList;

    }
    public ArrayList<Task> getTasksWithProject(String query){
        ArrayList<Task> taskArrayList = new ArrayList<>();

        try {

            DBConnector connectionBD = new DBConnector();//Создали класс конекта
            Class.forName(connectionBD.getDRIVER()).newInstance();//Используем драйвер
            Connection connection = DriverManager.getConnection(connectionBD.getURL(), connectionBD.getUSER_BD(),
                    connectionBD.getPASSWORD_BD());//Создаем обьект подключения
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);//Запрос
            while (resultSet.next()) { //Перебор результата
                Integer id = Integer.parseInt(resultSet.getString("tasks.id"));

                Boolean status;
                if (resultSet.getString("tasks.status").equals("1")) {
                    status = true;
                } else {
                    status = false;
                }
                String name = resultSet.getString("tasks.name");
                String dateCreate = (resultSet.getString("tasks.dateCreate"));
                String dateFinish = (resultSet.getString("tasks.dateFinish"));
                String projectName = (resultSet.getString("project.name"));


                taskArrayList.add(new Task(id, status, name, dateCreate, dateFinish, projectName));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return taskArrayList;

    }

    }



