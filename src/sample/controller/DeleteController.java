package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import sample.Task;
import sample.TaskRemover;
import sample.TypeOfDisplayedTasks;

public class DeleteController {

    @FXML
    private Button deleteBtn;

    @FXML
    private Button cancelBtn;


    Task deleteTask = new Task();
    //Переменная которя принимает тип задачи(дневная, неледьная и т.д) нужна для очистки и престроения таблицы с задачами
    TypeOfDisplayedTasks typeOfDisplayedTasks = MainController.typeOfDisplayedTasks;

    @FXML
    void cancel(ActionEvent event) {
        deleteBtn.getScene().getWindow().hide();
    }

    @FXML
    void delete(ActionEvent event) {
        new TaskRemover().removeTask(new MainController().getIdTask());
        deleteBtn.getScene().getWindow().hide();
        new MainController().refreshTable(typeOfDisplayedTasks);
    }



}
