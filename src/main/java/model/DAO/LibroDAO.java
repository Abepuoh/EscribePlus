package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.DataObject.Usuario;
import model.IDAO.DAOException;
import model.IDAO.ILibroDAO;
import model.IDataObject.ILibro;

public class LibroDAO implements ILibroDAO{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
	EntityManager em = emf.createEntityManager();

	// Queries
	private final String getAll = "SELECT * FROM Book";
	private String getById = "SELECT p FROM Book p WHERE p.id = :idLibro";
	private String getByName = "SELECT p FROM Book p WHERE p.nombre = :nombreLibro";
	private String getAllNotes = "SELECT p.book_notes FROM Book p ";
	private String getNote = "SELECT p.book_notes FROM Book p WHERE p.bo";
    private String getFromAuthor="SELECT p FROM Partes p WHERE p.id_libro=:idlibro";


	@Override
	public void crear(Libro aux) {
		em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
	}

	@Override
	public void editar(Libro aux) {
		em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
		
	}

	@Override
	public void borrar(Long id) {
		Libro delete = mostrarPorId(id);
		em.getTransaction().begin();
		em.remove(delete);
		em.getTransaction().commit();
		
	}

	@Override
	public List<Libro> mostrarTodos() {
		List<Libro> result = new ArrayList<>();
		em.getTransaction().begin();
		TypedQuery<Libro> q = em.createQuery(getAll, Libro.class);
		result = q.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public Libro mostrarPorId(Long id) {
		Libro result = null;
		em.getTransaction().begin();
		TypedQuery<Libro> q = em.createQuery(getById, Libro.class).setParameter("idLibro", id);
		result = q.getResultList().get(0);
		em.getTransaction().commit();
		return result;
	}

	@Override
	public Libro getBookByName(String name) throws DAOException {
		Libro result = null;
		em.getTransaction().begin();
		TypedQuery<Libro> q = em.createQuery(getByName, Libro.class).setParameter("nombreLibro", name);
		result = q.getResultList().get(0);
		em.getTransaction().commit();
		return result;
	}

	@Override
	public List<Libro> getBooksByAuthor(Usuario author) throws DAOException {
		List<Libro> result = new ArrayList<>();
		em.getTransaction().begin();
		TypedQuery<Libro> q = em.createQuery(getFromAuthor, Libro.class).setParameter("author", author.getId());
		result = q.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public List<Notas_Lib> getNotes() throws DAOException {
		List<Notas_Lib> result=new ArrayList<Notas_Lib>();
		em.getTransaction().begin();
		TypedQuery<Notas_Lib> q=em.createQuery(getAllNotes, Notas_Lib.class);
		result=q.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public Notas_Lib getNote(Long id) throws DAOException {
		Notas_Lib result = null;
		List<Notas_Lib> list=getNotes();
		for (Notas_Lib notas_Lib : list) {
			if (notas_Lib.getId()==id) {
				result=notas_Lib;
			}
		}
		return result;
	}

}
