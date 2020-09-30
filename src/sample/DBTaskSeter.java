package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTaskSeter {

    //Отправка запроса на создание задачи
    public void updateDate(String query){
        try {
            DBConnector connector= new DBConnector();
            Class.forName(connector.getDRIVER()).newInstance();//Используем драйвер
            Connection connection = DriverManager.getConnection(connector.getURL(), connector.getUSER_BD(),
                    connector.getPASSWORD_BD());//Создаем обьект подключения
            Statement statement = connection.createStatement();//Обьект для запроса

            statement.executeUpdate(query); //Текст запроса

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
