package sample.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.*;
import sample.dataBase.DBQuery;
import sample.dataBase.DBTaskGetter;

import java.io.IOException;
import java.util.ArrayList;

public class MainController{

    @FXML
    private TextField textFieldForNewTask;

    @FXML
    private Button addNewTaskId;

    @FXML
    private TableView<Task> tableShowTasks;

    @FXML
    private TableColumn<Task, Boolean> setStatus;

    @FXML
    private TableColumn<Task, Integer> id;

    @FXML
    private TableColumn<Task, String> name;

    @FXML
    private TableColumn<Task, String> dateCreate;

    @FXML
    private TableColumn<Task, String> dateFinish;

    @FXML
    private Button todayTasksId;

    @FXML
    private Button weekTasksId;

    @FXML
    private Button laterTasksId;

    @FXML
    private DatePicker newTaskDataPiker;

    @FXML
    private Button deleteTaskId;

    @FXML
    private Button showAllTasksId;

    @FXML
    private Button editTaskId;

    @FXML
    private Button searchBtn;

    @FXML
    private Button CreateProjectBtn;



    public static ObservableList<Task> taskListForTable = FXCollections.observableArrayList(); //Тут храняться задачи
    private static int idTask; //Переменная для храннения ID выбраной задачи используется при удалении
    String dateOfNewTask;  //Тут будет храниться дата крайнего срока задачи
    public static TypeOfDisplayedTasks typeOfDisplayedTasks; //Это перечисления для хранения типа покзываемых задач, нужно для отображения задач после удаления

    @FXML
    private void initialize() {
        //Инициализация полей таблицы
        setStatus.setCellValueFactory(new PropertyValueFactory<Task, Boolean>("status"));
        id.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        name.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        dateCreate.setCellValueFactory(new PropertyValueFactory<Task, String>("dateCreate"));
        dateFinish.setCellValueFactory(new PropertyValueFactory<Task, String>("dateFinish"));

        //Устанавливаю в датапикер сегодняшнюю дату, это не влияет на код переджаваемый в базу
        newTaskDataPiker.setPromptText(new TaskDate().getTodayDateString());
        // заполняем данными
        // по умолчанию это задачи на сегодняшний день
        //Создаем обьект который иницализирует запрос, вызываем у него метод на получение данных, с помошью BDQurry выбираем нужный запрос
        setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString())));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.TODAY;

        // добавляем данные в таблицу
        tableShowTasks.setItems(taskListForTable);
        // Получение данных из таблицы в переменную idTask;
        TableView.TableViewSelectionModel<Task> selectionModel = tableShowTasks.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<Task>() {

            @Override
            public void changed(ObservableValue<? extends Task> observableValue, Task task, Task t1) {
                if (t1 != null) idTask = t1.getId();
            }
        });

    }

    //В данном случае позволяет передавать idTask в другие окна программы, idTask сделал static
    public int getIdTask() {
        return idTask;
    }

    //Метод позвоялющий заполнить таблицу данными из базы данны
    public static void setTaskListForTable(ArrayList<Task> tasksFromBD) {
        for (Task x : tasksFromBD
        ) {
            taskListForTable.add(x);
        }
    }

    //Метод который перерисовывает таблицу после удаления или добавления задач
    public static void refreshTable(TypeOfDisplayedTasks typeOfDisplayedTasks) {
        switch (typeOfDisplayedTasks) {
            case TODAY -> {
                taskListForTable.clear();
                setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString())));
            }
            case WEEK -> {
                taskListForTable.clear();
                setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayPlusSevenDayToString())));
            }
            case LATER -> {
                taskListForTable.clear();
                setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getAllTaskWithoutStatusFalse()));
            }
            case ALL -> {
                taskListForTable.clear();
                setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getAllTask()));
            }
        }

    }

    ;

    //СОЗДАНИЕ НОВОЙ ЗАДАЧИ
    //Установить дану новой задачи из дата пикера
    @FXML
    void setDateOfNewTask(ActionEvent event) {
        dateOfNewTask = newTaskDataPiker.getValue().toString();
    }

    //После нажатия этой кнопки добавляется новая задача
    @FXML
    void addNewTaskBtn(ActionEvent event) {
        new TaskCreator().createTask(textFieldForNewTask.getText(), dateOfNewTask);
        textFieldForNewTask.clear();
        refreshTable(typeOfDisplayedTasks);
    }

    //Показать сегодняшние задачи
    @FXML
    void showTodayTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayDateString())));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.TODAY;
    }

    //Показать задачи на неделю
    @FXML
    void showWeekTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getTasksForThisDate(new TaskDate().getTodayPlusSevenDayToString())));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.WEEK;
    }

    //Показать все дадачи без завершенных LATER
    @FXML
    void showLaterTaskBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getAllTaskWithoutStatusFalse()));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.LATER;
    }

    //Показать все задачи в том числе завершенные
    @FXML
    void showAllTasksBtn(ActionEvent event) {
        taskListForTable.clear();
        setTaskListForTable(new DBTaskGetter().getData(new DBQuery().getAllTask()));
        typeOfDisplayedTasks = TypeOfDisplayedTasks.ALL;


    }

    //Удаление задачи по id
    @FXML
    void deleteTaskBtn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        //Используя сет локейшен показываем какой ресурс мы загрузим
        loader.setLocation(getClass().getResource("/sample/fxml/delete.fxml"));
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

    //Редактирование задачи получить по id задачу затем передать ее в окно, отредактировать и сохранить
    @FXML
    void editTaskBtn(ActionEvent event) {
        //Скрываем сцену на которой находится кнопка edit
        //editTaskId.getScene().getWindow().hide();
        //Инициализируем класс который будет показывать окно
        FXMLLoader loader = new FXMLLoader();
        //Используя сет локейшен показываем какой ресурс мы загрузим
        loader.setLocation(getClass().getResource("/sample/fxml/edit.fxml"));
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

    //Кнопка поиска
    @FXML
    void search(ActionEvent event) {
        //Скрываем сцену на которой находится кнопка edit
        //editTaskId.getScene().getWindow().hide();
        //Инициализируем класс который будет показывать окно
        FXMLLoader loader = new FXMLLoader();
        //Используя сет локейшен показываем какой ресурс мы загрузим
        loader.setLocation(getClass().getResource("/sample/fxml/search.fxml"));
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

    //Создание Проекта
    @FXML
    void CreateProject(ActionEvent event) {
        //Инициализируем класс который будет показывать окно
        FXMLLoader loader = new FXMLLoader();
        //Используя сет локейшен показываем какой ресурс мы загрузим
        loader.setLocation(getClass().getResource("/sample/fxml/project.fxml"));
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

}
