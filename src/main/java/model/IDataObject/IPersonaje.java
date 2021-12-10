package model.IDataObject;

import java.util.List;

import model.DataObject.Libro;

public interface IPersonaje {
	
	public Long getId();
	public String getNombre();
	public int getEdad();
	public String getDescripcion();
	public String getAlineamiento();
	public String getFoto();
	public List<Libro> getLibroRef();
	
	public void setId(Long aux);
	public void setNombre(String aux);
	public void setEdad(int aux);
	public void setDescripcion(String aux);
	public void setAlineamiento(String aux);
	public void setFoto(String aux);
	public void setLibroRef(List<Libro> aux);
}
