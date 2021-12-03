package model.IDataObject;

public interface IUsuario {
	
	public Long getId();
	public String getNombre();
	public String getEmail();
	public String getContraseña();
	public String getTelefono();
	
	public void setID(Long aux);
	public void setNombre(String aux);
	public void setEmail(String aux);
	public void setContraseña(String aux);
	public void setTelefono(String aux);

}
