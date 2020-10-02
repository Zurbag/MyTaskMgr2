package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.DataBase.DBQuery;
import sample.DataBase.DBTaskGeter;
import sample.Task;
import sample.TaskDate;

import java.util.ArrayList;

public class EditController {

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
    @FXML
    private void initialize(){

        for (Task x:new DBTaskGeter().getData(new DBQuery().getOneTaskForIndex(new MainController().getIdTask()))
             ) {
            editNameTextField.setText(x.getName());
            editTask.setName(x.getName());
            editDataPiker.setPromptText(x.getDateFinish());
            editTask.setDateFinish(x.getDateFinish());
            if(!x.getStatus()){
                editChangeStatusBtn.setText("Возобновить задачу");
            }
        }
    }

    @FXML
    void changeDate(ActionEvent event) {

    }

    @FXML
    void changeStatus(ActionEvent event) {

    }

    @FXML
    void endEdit(ActionEvent event) {

    }

}
