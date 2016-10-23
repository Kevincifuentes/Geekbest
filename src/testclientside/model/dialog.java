package testclientside.model;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

public class dialog {

    public dialog() {
    }
    
    public static void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
}
