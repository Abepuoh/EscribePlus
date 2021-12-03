package model.IDataObject;
import java.time.LocalDate;

public interface IRecordatorio {
	

	public Long getId();
	public LocalDate getFecha();
	public String getTexto();
	
	public void setId(Long aux);
	public void setFecha(LocalDate aux);
	public void setTexto(String aux);
}
