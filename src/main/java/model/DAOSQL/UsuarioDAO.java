package model.DAOSQL;

import java.util.List;

import model.DataObject.Usuario;
import model.IDAO.DAOException;
import model.IDAO.IUsuarioDAO;

public class UsuarioDAO implements IUsuarioDAO{

	@Override
	public void crear(Usuario aux) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Usuario aux) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Usuario> mostrarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario mostrarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getUserByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario getUserByEmail(String email) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean logIn(String nombre, String contraseña) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}
	
}
