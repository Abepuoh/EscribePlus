package model.IDAO;

import model.DataObject.Usuario;

public interface IUsuarioDAO extends IDAO<Usuario, Long> {

	/**
	 * Metodo que busca un usuario por su nombre
	 * 
	 * @param name Nombre del usuario
	 * @return Usuario encontrado
	 */
	public Usuario getUserByName(String name);

	/**
	 * Método que buscar un usuario por su email // Método opcional que no es
	 * necesario implementarlo por si queremos hacer un "recovery" de la cuenta
	 * 
	 * @param email Email del usuario
	 * @return Usuario encontrado
	 */
	public Usuario getUserByEmail(String email);

	/**
	 * Método que devuelve true o false en función de si el usuario esta logueado o
	 * no
	 * 
	 * @param nombre     del usuario
	 * @param contraseña del usuario
	 * @return true o false si esta logged
	 * @throws DAOException
	 */
	public boolean logIn(String nombre, String contraseña);

	/**
	 * Método que devuelve el usuario mediante dos parámetros que introduzcamos
	 * 
	 * @param nAux es el nombre del usuario
	 * @param cAux es la contraseña del usuario
	 * @throws DAOException
	 */
	Usuario getUsuarioByNombreContraseña(String nAux, String cAux);
}
