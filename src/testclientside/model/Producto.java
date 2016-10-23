package testclientside.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

//If you want you can define the order in which the fields are written
//Optional
@XmlType(propOrder = { "id", "nombre", "descripcion", "tipo", "precio", "urlimagen", "proveedor"})
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Producto {
	
	
	public String id;
	public String nombre;
	public String descripcion;
	public String tipo; //tablet, smartphone
	public double precio;
	public String urlimagen;
	public Date fechaModificacion;
	public String proveedor;
	
	public Producto(String id, String nombre, String descripcion, String tipo, double precio, String urlimagen, String proveedor) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.precio = precio;
		this.urlimagen = urlimagen;
		this.proveedor = proveedor;
	}
	
	public Producto() {
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getUrlimagen() {
		return urlimagen;
	}
	public void setUrlimagen(String urlimagen) {
		this.urlimagen = urlimagen;
	}
	
	@XmlTransient
	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public String toString() {
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "Producto con id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", tipo=" + tipo
				+ ", precio=" + precio + ", urlimagen=" + urlimagen
				+ ", proveedor=" + proveedor + "";
	}
	
	
	

}
