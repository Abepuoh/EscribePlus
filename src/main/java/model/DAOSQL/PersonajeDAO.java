
package model.DAOSQL;

import java.util.List;

import model.DataObject.Personaje;
import model.DataObject.Usuario;
import model.IDAO.DAOException;
import model.IDAO.IPersonajeDAO;
import model.IDataObject.IPersonaje;

public class PersonajeDAO implements IPersonajeDAO{

	@Override
	public void crear(IPersonaje aux) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(IPersonaje aux) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IPersonaje> mostrarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPersonaje mostrarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Personaje getCharacterByName(String nombre) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Personaje> getCharactersByUser(Usuario user) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
  
}
