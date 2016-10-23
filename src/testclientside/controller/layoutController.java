package testclientside.controller;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.axis2.AxisFault;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.*;
import javafx.util.Callback;
import productsw.AnadirNuevoProducto;
import productsw.AnadirNuevoProductoResponse;
import productsw.BorrarProducto;
import productsw.EditarProducto;
import productsw.EditarProductoResponse;
import productsw.GeekBest_ProductServiceStub;
import productsw.ObtenerProductosProveedor;
import productsw.ObtenerProductosProveedorResponse;
import testclientside.animation.FadeInUpTransition;
import testclientside.animation.FadeOutUpTransition;
import classes.xsd.*;
import testclientside.model.ListadoProductos;
import testclientside.model.dialog;

public class layoutController implements Initializable {
    @FXML
    private Label lblStatus;
    @FXML
    private ProgressBar bar;
    @FXML
    private Hyperlink RefID;
    @FXML
    private AnchorPane paneInput;
    @FXML
    private AnchorPane paneXML;
    @FXML
    private Label lblClose, lblClose2, lblId;
    @FXML
    private TextField txtNombre, txtId, txtTipo, txtPrecio, txtUrl;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btnSave;
    @FXML
    private TableView<Producto> tableData;
    @FXML
    private TableColumn<Producto, String> colId, colIdAdd;
    @FXML
    private TableColumn<Producto, String> colNama, colNamaAdd;
    @FXML
    private TableColumn<Producto, String> colDesc;
    @FXML
    private TableColumn<Producto, String> colTipo;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Date> colFecha;
    @FXML
    private TableColumn colAction;
    @FXML
    private Button btnAdd, btnRefresh, btnFichero;
    
    Integer statusAction;
    public static Integer statusConnection;
    private GeekBest_ProductServiceStub stub;
    private String username;
    private Stage stage;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	lblClose2.setOnMouseClicked((MouseEvent event) -> {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Cerrando aplicación");
    		alert.setHeaderText("El sistema está apunto de cerrarse");
    		alert.setContentText("¿Está seguro de que desea cerrar el programa?");

    		Optional<ButtonType> result = alert.showAndWait();
    		if (result.get() == ButtonType.OK){
    			Platform.exit();
                System.exit(0);
    		} else {
    		}
            
        });
    	
    	try {
			stub = new GeekBest_ProductServiceStub("http://localhost:8080/axis2/services/GeekBest_ProductService");
		} catch (AxisFault e) {
			System.out.println("LayoutController: ERROR al crear el stub del servicio GeekBest_ProductService. ");
			e.printStackTrace();
		}
    	
        statusAction = 0;
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNama.setCellValueFactory(new PropertyValueFactory("nombre"));
        colIdAdd.setCellValueFactory(new PropertyValueFactory("id"));
        colNamaAdd.setCellValueFactory(new PropertyValueFactory("nombre"));
        colDesc.setCellValueFactory(new PropertyValueFactory("descripcion"));
        colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        colFecha.setCellValueFactory(new PropertyValueFactory("fechaModificacion"));
        colAction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
 
        colAction.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(tableData);
            }
        });
        lblClose.setCursor(Cursor.HAND);
        lblClose2.setCursor(Cursor.HAND);
        lblClose.setOnMouseClicked((MouseEvent event) -> {
        	new FadeOutUpTransition(paneInput).play();
            selectData();
            clear();
            
        });
    }
    
    @FXML
    private void aksiAdd(ActionEvent ev){
        //Añadir uno nuevo
        
    }
    
    @FXML
    private void aksiRefresh(ActionEvent ev){
        clear();
        selectData();
    }
    
    @FXML
    private void aksiSave(ActionEvent ev){
        if (btnSave.getText().equals("Añadir")) {
            if (txtId.getText().isEmpty()) {
                dialog.dialog(Alert.AlertType.INFORMATION, "El identificador no se puede dejar vacio.");
            }else if (txtNombre.getText().isEmpty()) {
                dialog.dialog(Alert.AlertType.INFORMATION, "El nombre no se puede dejar vacio.");
            }else if(txtDescripcion.getText().isEmpty()){
                dialog.dialog(Alert.AlertType.INFORMATION, "La descripción no se puede dejar vacia.");
            }else if(txtTipo.getText().isEmpty()){
                dialog.dialog(Alert.AlertType.INFORMATION, "El tipo no se puede dejar vacio.");
            }else if(txtPrecio.getText().isEmpty()){
                dialog.dialog(Alert.AlertType.INFORMATION, "El precio no se puede dejar vacio.");
            }else{
                Producto p = new Producto();
                p.setId(txtId.getText());
                p.setNombre(txtNombre.getText());
                p.setDescripcion(txtDescripcion.getText());
                p.setTipo(txtTipo.getText());
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                p.setUrlimagen(txtUrl.getText());
                p.setProveedor(username);
                
                AnadirNuevoProducto anp = new AnadirNuevoProducto();
                anp.setP(p);
                try {
					AnadirNuevoProductoResponse anpr = stub.anadirNuevoProducto(anp);
					if(anpr.get_return())
					{
						clear();
		                selectData();
						dialog.dialog(Alert.AlertType.INFORMATION, "Se ha guardado correctamente el nuevo producto.");
						new FadeOutUpTransition(paneInput).play();
					}
					else{
						clear();
		                selectData();
						dialog.dialog(Alert.AlertType.INFORMATION, "No se ha podido almacenar el nuevo producto.");
					}
					
					
				} catch (RemoteException e) {
					dialog.dialog(Alert.AlertType.ERROR, "Error al guardar el producto. Intentelo más tarde.");
					new FadeOutUpTransition(paneInput).play();
					e.printStackTrace();
				}
                
                
            }
        }else{
        	if (txtId.getText().isEmpty()) {
                dialog.dialog(Alert.AlertType.INFORMATION, "El identificador no se puede dejar vacio.");
            }else if (txtNombre.getText().isEmpty()) {
                dialog.dialog(Alert.AlertType.INFORMATION, "El nombre no se puede dejar vacio.");
            }else if(txtDescripcion.getText().isEmpty()){
                dialog.dialog(Alert.AlertType.INFORMATION, "La descripción no se puede dejar vacia.");
            }else if(txtTipo.getText().isEmpty()){
                dialog.dialog(Alert.AlertType.INFORMATION, "El tipo no se puede dejar vacio.");
            }else if(txtPrecio.getText().isEmpty()){
                dialog.dialog(Alert.AlertType.INFORMATION, "El precio no se puede dejar vacio.");
            }else{
                Producto p = new Producto();
                p.setId(txtId.getText());
                p.setNombre(txtNombre.getText());
                p.setDescripcion(txtDescripcion.getText());
                p.setTipo(txtTipo.getText());
                p.setPrecio(Double.parseDouble(txtPrecio.getText()));
                p.setUrlimagen(txtUrl.getText());
                
                EditarProducto ep = new EditarProducto();
                ep.setId(p.getId());
                ep.setP(p);
                try {
					EditarProductoResponse epr = stub.editarProducto(ep);
					if(epr.get_return())
					{
						clear();
		                new FadeOutUpTransition(paneInput).play();
		                dialog.dialog(Alert.AlertType.INFORMATION, "Se ha actualizado correctamente el producto.");
		                selectData();
					}
					else{
						dialog.dialog(Alert.AlertType.ERROR, "No se ha podido actualizar el nuevo producto.");
					}
				} catch (RemoteException e) {
					dialog.dialog(Alert.AlertType.ERROR, "Error al actualizar el producto. Intentelo más tarde.");
					new FadeOutUpTransition(paneInput).play();
					e.printStackTrace();
				}
            }
        } 
    }
    
    @FXML
    private void klikTableData(MouseEvent es){
        if (statusAction==1) {
            try {
            	Producto p = tableData.getSelectionModel().getSelectedItem();
                txtId.setText(p.getId());
                txtNombre.setText(p.getNombre());
                txtDescripcion.setText(p.getDescripcion());
                txtTipo.setText(p.getTipo());
                txtPrecio.setText(p.getPrecio()+"");
                txtUrl.setText(p.getUrlimagen());
            } catch (Exception e) {
            }
        }
    }
    
    @FXML
    private void abrirAnadir(ActionEvent es){
    	btnSave.setText("Añadir");
        new FadeInUpTransition(paneInput).play();
    }
    
    @FXML
    private void abrirFichero(ActionEvent es){
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Elegir fichero xml...");
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
    	fileChooser.getExtensionFilters().add(extFilter);
    	File f = fileChooser.showOpenDialog(stage);
    	try {
    		
	        // This cast is possible because BusinessCard is annotated as @XmlRootElement
	        // Otherwise, JAXBElement<BusinessCard> and getValue() method should be used
    		JAXBContext jc = JAXBContext.newInstance(ListadoProductos.class);

    		Unmarshaller unmarshaller = jc.createUnmarshaller();
			ListadoProductos listaProd = (ListadoProductos) unmarshaller.unmarshal(f);
			System.out.println(listaProd.getListaProductos());
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Inserción de productos mediante xml");
			alert.setHeaderText("¿Deseas añadir los siguientes "+ listaProd.getListaProductos().size()+" productos?");

			Label label = new Label("Los productos son:");
			String mostrar = "";
			for (testclientside.model.Producto p : listaProd.getListaProductos())
			{
				mostrar = mostrar + p.toString() + "\n";
			}
			TextArea textArea = new TextArea(mostrar);
			textArea.setEditable(false);
			textArea.setWrapText(true);

			textArea.setMaxWidth(Double.MAX_VALUE);
			textArea.setMaxHeight(Double.MAX_VALUE);
			GridPane.setVgrow(textArea, Priority.ALWAYS);
			GridPane.setHgrow(textArea, Priority.ALWAYS);

			GridPane expContent = new GridPane();
			expContent.setMaxWidth(Double.MAX_VALUE);
			expContent.add(label, 0, 0);
			expContent.add(textArea, 0, 1);

			// Set expandable Exception into the dialog pane.
			alert.getDialogPane().setExpandableContent(expContent);
			alert.getDialogPane().setExpanded(true);
			alert.setX(500.0);
			alert.setY(250.0);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK){
			    System.out.println("OK");
				for (testclientside.model.Producto p : listaProd.getListaProductos())
				{
					AnadirNuevoProducto anp = new AnadirNuevoProducto();
					Producto anadir = new Producto();
					anadir.setDescripcion(p.getDescripcion());
					anadir.setFechaModificacion(new Date());
					anadir.setId(p.getId());
					anadir.setNombre(p.getNombre());
					anadir.setPrecio(p.getPrecio());
					anadir.setProveedor(p.getProveedor());
					anadir.setTipo(p.getTipo());
					anadir.setUrlimagen(p.getUrlimagen());
	                anp.setP(anadir);
	                AnadirNuevoProductoResponse anpr = null;
					try {
						anpr = stub.anadirNuevoProducto(anp);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					if(!anpr.get_return())
					{
						dialog.dialog(Alert.AlertType.ERROR, "Ha ocurrido un error mientras se guardaban uno de los productos del XML. Se abortó la ejecución. Compruebe el XML");
						break;
					}
				}
				clear();
                selectData();
				
			} else {
			    
				System.out.println("NO");
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    private void login(MouseEvent es){
        if (statusAction==1) {
            try {
                Producto p = tableData.getSelectionModel().getSelectedItem();
                txtId.setText(p.getId());
                txtNombre.setText(p.getNombre());
                txtDescripcion.setText(p.getDescripcion());
                txtTipo.setText(p.getTipo());
                txtPrecio.setText(p.getPrecio()+"");
                txtUrl.setText(p.getUrlimagen());
            } catch (Exception e) {
            }
        }
    }
    
    private void selectData(){
        Service<ObservableList<Producto>> service = new Service<ObservableList<Producto>>() {
            @Override
            protected Task<ObservableList<Producto>> createTask() {
                return new Task<ObservableList<Producto>>() {           
                    @Override
                    protected ObservableList<Producto> call() throws Exception {
                    	ObtenerProductosProveedor opp = new ObtenerProductosProveedor();
                    	System.out.println(">>>>"+username);
                    	opp.setUsername(username);
                    	ObtenerProductosProveedorResponse oppr = stub.obtenerProductosProveedor(opp);
                    	System.out.println("Aqui llego!");
                        ObservableList<Producto> listTask = FXCollections.observableArrayList(Arrays.asList(oppr.get_return()));
                        
                        tableData.setItems(listTask);
                        Integer max = listTask.size();
                        updateProgress(0, max);
                        int contador =0;
                        for (int k = 0; k <= max; k++) {
                            try {
                                Thread.sleep(100);
                                updateProgress(k + 1, max);
                                updateMessage("Encontrados : " + ((k + 1)-1) + " productos");
                                contador = k;
                            } catch (InterruptedException ex) {
                                Logger.getLogger(layoutController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        updateMessage("Total : " + (contador) + " productos");
                        return listTask;
                    }
                };
            }
        };
        lblStatus.textProperty().bind(service.messageProperty());
        bar.progressProperty().bind(service.progressProperty());
        service.start();
    }
    
    private void clear(){
        txtNombre.clear();
        txtDescripcion.setText("");
        txtTipo.clear();
        txtUrl.clear();
        txtPrecio.clear();
        txtId.clear();
    }
    
    private class ButtonCell extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonDelete = new Hyperlink("Borrar");
        final Hyperlink cellButtonEdit = new Hyperlink("Editar");
        final Hyperlink cellButtonVer = new Hyperlink("Ver");
        final HBox hb = new HBox(cellButtonVer, cellButtonDelete,cellButtonEdit);
        ButtonCell(final TableView tblView){
            hb.setSpacing(4);
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                statusAction = 1;
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                klikTableData(null);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "¿Estas seguro que quieres eliminar "+txtNombre.getText()+" ?");
                alert.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    BorrarProducto bp = new BorrarProducto();
                    bp.setId(txtId.getText());
                    try {
						stub.borrarProducto(bp);
					} catch (Exception e) {
						System.out.println("LayoutControl: ERROR al borrar producto.");
						e.printStackTrace();
					}
                    clear();
                    selectData();
                }else{
                    clear();
                    selectData();
                }
                statusAction = 0;
            });
            cellButtonEdit.setOnAction((ActionEvent event) -> {
                statusAction = 1;
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                klikTableData(null);
                btnSave.setText("Actualizar");
                new FadeInUpTransition(paneInput).play();
                txtNombre.requestFocus();
                statusAction = 0;
            });
            
            cellButtonVer.setOnAction((ActionEvent event) -> {
                statusAction = 1;
                int row = getTableRow().getIndex();
                tableData.getSelectionModel().select(row);
                klikTableData(null);
                try {
                	statusAction = 0;
                    Desktop.getDesktop().browse(new URI(txtUrl.getText()));
                } catch (IOException e1) {
                	dialog.dialog(Alert.AlertType.ERROR, "Error al acceder a la URL.");
                } catch (URISyntaxException e1) {
                	dialog.dialog(Alert.AlertType.ERROR, "Error al acceder a la URL.");
                }
                statusAction = 0;
            });
        }

        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
    }

	public void initData(String username, Stage st) {
		// TODO Auto-generated method stub
		System.out.println(username);
		this.username = username;
		this.stage = st;
        selectData();
	}

}
