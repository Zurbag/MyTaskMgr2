package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.TypeOfDisplayedTasks;
import sample.dataBase.DBQuery;
import sample.dataBase.DBGetter;
import sample.Task;
import sample.dataBase.DBTaskSetter;

public class EditTaskController {

    @FXML
    private TextField editNameTextField;

    @FXML
    private Button editApplyBtn;

    @FXML
    private DatePicker editDataPiker;

    @FXML
    private Button editChangeStatusBtn;

    @FXML
    private TextField editTextField;

    //Сохдаем задачу которая будет редактироваться
    Task editTask = new Task();
    //Переменная которя принимает тип задачи(дневная, неледьная и т.д) нужна для очистки и престроения таблицы с задачами
    TypeOfDisplayedTasks typeOfDisplayedTasks = MainController.typeOfDisplayedTasks;
    @FXML
    private void initialize(){
        for (Task x:new DBGetter().getTasks(new DBQuery().getOneTaskForIndex(new MainController().getIdTask()))
             ) {
            editTask.setId(x.getId());
            editNameTextField.setText(x.getName());
            editTask.setName(x.getName());
            editDataPiker.setPromptText(x.getDateFinish());
            editTask.setDateFinish(x.getDateFinish());
            editTask.setStatus(x.getStatus());
            if(!x.getStatus()){
                editChangeStatusBtn.setText("Возобновить задачу");
            }
        }
    }

    @FXML
    void changeDate(ActionEvent event) {
        editTask.setDateFinish(editDataPiker.getValue().toString());
    }

    @FXML
    void changeStatus(ActionEvent event) {
        if(editTask.getStatus()){
            editTask.setStatus(false);
        }else{
            editTask.setStatus(true);
        }
        addTaskForDBandHideWindow();

    }

    @FXML
    void endEdit(ActionEvent event) {
        addTaskForDBandHideWindow();
    }

    //Добавление задачи в базу и скрытие окна
    private void addTaskForDBandHideWindow(){
        new DBTaskSetter().updateDate(new DBQuery().editTask(editTask.getId(),
                editTask.getStatus(),editNameTextField.getText(),editTask.getDateFinish()));
                editApplyBtn.getScene().getWindow().hide();
                new MainController().refreshTable(typeOfDisplayedTasks);
    }



}
