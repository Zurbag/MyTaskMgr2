package sample;

import javafx.scene.control.Alert;
import sample.DataBase.DBQuery;
import sample.DataBase.DBTaskSeter;

public class TaskCreator {

    public void createTask(String textNewTask, String dateFinishString){
        String nameTask = null;
        //Окно ошибки если не введен текст
        if (textNewTask.equals("")) {//setNewTaskTextField.getText()
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Поле текст задачи должно быть заполнено");
            alert.showAndWait();
            return;
        } else {
            nameTask = textNewTask;
        }

        //Если не установлена дата установить сегодняшюю дату
        String dataFinish;
        if (dateFinishString == null) {
            dataFinish = new TaskDate().getTodayDateString();
        } else {
            dataFinish = dateFinishString;
        }

        //Запрос на удаление задачи
        new DBTaskSeter().updateDate(new DBQuery().createTask(nameTask, dataFinish));

    }

}
