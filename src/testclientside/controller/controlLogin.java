package testclientside.controller;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

import org.apache.axis2.AxisFault;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import productsw.GeekBest_ProductServiceStub;
import productsw.Login;
import productsw.LoginResponse;
import testclientside.animation.*;

public class controlLogin implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text lblRudyCom;
    @FXML 
    private Label lblClose;
    
    private GeekBest_ProductServiceStub stub;
    
    Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			stub = new GeekBest_ProductServiceStub("http://localhost:8080/axis2/services/GeekBest_ProductService");
		} catch (AxisFault e) {
			System.out.println("ControlLogin: ERROR al crear el stub del servicio GeekBest_ProductService. ");
			e.printStackTrace();
		}
        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
    	//comprobar el login
    	Login l = new Login();
    	l.setUsername(txtUsername.getText());
    	l.setPassword(txtPassword.getText());
    	int correcto = -1;
    	try {
			LoginResponse lp = stub.login(l);
			correcto = lp.get_return();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	switch (correcto) {
		case 1: //admin
			config.dialog(Alert.AlertType.INFORMATION, "Administrador: acceda desde el cliente de administración.");
			break;
		case 2: //proveedor
			config c = new config();
            c.newStage(stage, lblClose, "/testclientside/view/layout.fxml", "Test App", true, StageStyle.UNDECORATED, false, txtUsername.getText());
			break;
		case 3: //cliente
			config.dialog(Alert.AlertType.WARNING, "Error. Este cliente es solo para proveedores. Accede a la web.");
			break;
		default:
			config.dialog(Alert.AlertType.ERROR, "Error en el login, compruebe sus credenciales.");
			break;
		}
    }
    
}
