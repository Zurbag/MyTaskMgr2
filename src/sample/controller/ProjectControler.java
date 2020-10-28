package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sample.Project;
import sample.TaskDate;
import sample.dataBase.DBQuery;
import sample.dataBase.DBTaskSetter;

import java.sql.SQLOutput;

public class ProjectControler {

    @FXML
    private TextField nameProject;

    @FXML
    private Button editApplyBtn;

    @FXML
    private DatePicker projectDataPiker;

    @FXML
    private Button editChangeStatusBtn;

    @FXML
    private TextField projectTextField;

    Project project = new Project();
    @FXML
    private void initialize(){
        //Устанавливаю в датапикер сегодняшнюю дату, это не влияет на код переджаваемый в базу
        projectDataPiker.setPromptText(new TaskDate().getTodayDateString());
        project.setDateFinish(new TaskDate().getTodayDateString());
    }

    @FXML
    void changeDate(ActionEvent event) {
        project.setDateFinish(projectDataPiker.getValue().toString());
    }

    @FXML
    void CreateProject(ActionEvent event) {
       if (nameProject.getText().equals("")){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("Ошибка");
           alert.setHeaderText(null);
           alert.setContentText("Поле текст задачи должно быть заполнено");
           alert.showAndWait();
           return;
       } else {
           project.setName(nameProject.getText());
           project.setDescription(projectTextField.getText());
       }

        new DBTaskSetter().updateDate(new DBQuery().createProject(project.getName(), project.getDateFinish(),project.getDescription()));
//        System.out.println("Название"+project.getName());
//        System.out.println("Дата создания"+project.getDateCreate());
//        System.out.println("Дата завершения"+project.getDateFinish());

    }

}
