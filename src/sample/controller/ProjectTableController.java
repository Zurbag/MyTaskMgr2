package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Project;
import sample.dataBase.DBGetter;
import sample.dataBase.DBQuery;

import java.io.IOException;
import java.util.ArrayList;

public class ProjectTableController {

    @FXML
    private TableView<Project> projectTable;

    @FXML
    private TableColumn<Project, Boolean> status;

    @FXML
    private TableColumn<Project, Integer> id;

    @FXML
    private TableColumn<Project, String> name;

    @FXML
    private TableColumn<Project, String> dateCreate;

    @FXML
    private TableColumn<Project, String> dateFinish;

    @FXML
    private Button NewProject;

    @FXML
    private Button EditProject;

    @FXML
    private Button DeleteProject;

    //Тут хранятся проекты
    ObservableList<Project> projectListForTable = FXCollections.observableArrayList();
    @FXML
    private void initialize(){
        // добавляем данные в таблицу
        status.setCellValueFactory(new PropertyValueFactory<Project, Boolean>("status"));
        id.setCellValueFactory(new PropertyValueFactory<Project, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Project, String>("name"));
        dateCreate.setCellValueFactory(new PropertyValueFactory<Project, String>("dateCreate"));
        dateFinish.setCellValueFactory(new PropertyValueFactory<Project, String>("dateFinish"));
        //Создаем обьект который иницализирует запрос, вызываем у него метод на получение данных, с помошью BDQurry выбираем нужный запрос
        setProjectListForTable(new DBGetter().getProject(new DBQuery().getAllProject()));
        projectTable.setItems(projectListForTable);
    }

    //Метод заполняющий ObservableList
    private void setProjectListForTable(ArrayList<Project> projects) {
        for (Project x : projects
        ) {
            projectListForTable.add(x);
        }
    }

    @FXML
    void CreateNewProject(ActionEvent event) {
        //Инициализируем класс который будет показывать окно
        FXMLLoader loader = new FXMLLoader();
        //Используя сет локейшен показываем какой ресурс мы загрузим
        loader.setLocation(getClass().getResource("/sample/fxml/projectCreate.fxml"));
        //С помошью метода лоад загружем файл
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.showAndWait();
    }

    @FXML
    void DeleteSelectProject(ActionEvent event) {

    }


    @FXML
    void EditSelectProject(ActionEvent event) {

    }


}
