package model.DAOSQL;


import java.util.List;

import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.DataObject.Usuario;
import model.IDAO.DAOException;
import model.IDAO.ILibroDAO;


public class LibroDAO implements ILibroDAO{

	@Override
	public void crear(Libro aux) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Libro aux) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void borrar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Libro> mostrarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libro mostrarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Libro getBookByName(String name) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Libro> getBooksByAuthor(Usuario author) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Notas_Lib> getNotes() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notas_Lib getNote(Long id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
