package testclientside.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.*;


@XmlRootElement(name="listaProductos")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListadoProductos {
	
	//Dentro de una etiqueta book
	// XmlElement sets the name of the entities
	@XmlElement(name = "producto")
	private ArrayList<Producto> listaProductos;
	
	
	public void setListaProductos(ArrayList<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	
	public ArrayList<Producto> getListaProductos() {
		return listaProductos;
	}
	
	

}
