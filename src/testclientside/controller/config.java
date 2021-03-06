package testclientside.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import testclientside.animation.FadeInRightTransition;

public class config {

    public config() {
    }
    
    public static void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
    
    public void newStage(Stage stage, Label lb, String load, String judul, boolean resize, StageStyle style, boolean maximized, String username){
       try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/testclientside/view/layout.fxml"));
            Scene scene = new Scene(loader.load());
            layoutController controller = 
            	    loader.<layoutController>getController();
            controller.initData(username, st);
            st.setTitle("GeekBest Product Administrator");
            st.setScene(scene);
            st.initStyle(StageStyle.UNDECORATED);
            st.show();
            stage.close();
        } catch (Exception e) {
        	e.printStackTrace();
        } 
    }
    
    public void newStage2(Stage stage, Button lb, String load, String judul, boolean resize, StageStyle style, boolean maximized){
       try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(judul);
            
            st.show();
            stage.close();
        } catch (Exception e) {
        } 
    }
    
    public void loadAnchorPane(AnchorPane ap, String a){
        try {
            AnchorPane p = FXMLLoader.load(getClass().getResource("/herudi/view/"+a));
            ap.getChildren().setAll(p);
        } catch (IOException e) {
        }   
    }
    
    public static void setModelColumn(TableColumn tb,String a){
        tb.setCellValueFactory(new PropertyValueFactory(a));
    }
}
