package model.IDAO;

import model.DataObject.Usuario;
import model.IDataObject.IUsuario;

public interface IUsuarioDAO extends IDAO<IUsuario, Long>{
	
    /**
	 * Metodo que busca un usuario por su nombre 
	 * @param name Nombre del usuario
	 * @return Usuario encontrado
	 */
	public Usuario getUserByName(String name) throws DAOException;
	/**
     * Método que buscar un usuario por su email // Método opcional que no es necesario implementarlo 
     * por si queremos hacer un "recovery" de la cuenta
     * @param email Email del usuario
     * @return Usuario encontrado
     */
    public Usuario getUserByEmail(String email) throws DAOException;
    
    /**
	 * Método que devuelve true o false en función de si el usuario esta logueado o no
	 * @param nombre del usuario	
	 * @param contraseña del usuario	
	 * @return true o false si esta logged
	 * @throws DAOException
	 */
	 public boolean logIn(String nombre, String contraseña) throws DAOException;
}
