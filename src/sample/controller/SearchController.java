package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.TypeOfDisplayedTasks;
import sample.dataBase.DBQuery;
import sample.dataBase.DBTaskGetter;

public class SearchController {

    @FXML
    private Button searchBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField taskSearchText;


    //Переменная которя принимает тип задачи(дневная, неледьная и т.д) нужна для очистки и престроения таблицы с задачами
    TypeOfDisplayedTasks typeOfDisplayedTasks = MainController.typeOfDisplayedTasks;

    @FXML
    void cancel(ActionEvent event) {
        cancelBtn.getScene().getWindow().hide();
    }

    @FXML
    void search(ActionEvent event) {

        //Очишаем таблицу в классе MainController
        new MainController().taskListForTable.clear();
        //Вызываем статические метод для добавлнеия запроса
        new MainController().setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getTaskLikeName(taskSearchText.getText())));

        searchBtn.getScene().getWindow().hide();

    }

}
