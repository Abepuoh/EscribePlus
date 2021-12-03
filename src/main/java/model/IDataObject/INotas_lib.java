package model.IDataObject;
import model.DataObject.Libro;

public interface INotas_lib {
	
	public Long getId();
	public String getTexto();
	public Libro getLibro();
	
	public void setId(Long aux);
	public void setTexto(String aux);
	public void setLibro(Libro aux);
	
}
