package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.DataObject.Usuario;
import model.IDAO.ILibroDAO;
import utils.ConnectionUtil;

public class LibroDAO implements ILibroDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();
	// Queries
//	private final String getAll = "SELECT * FROM Book";
//	private String getById = "SELECT p FROM Book p WHERE p.id = :idLibro";
//	private String getByName = "SELECT p FROM Book p WHERE p.nombre = :nombreLibro";
//	private String getAllNotes = "SELECT p.book_notes FROM Book p ";
//	private String getFromAuthor = "SELECT p FROM Partes p WHERE p.id_libro=:idlibro";

	@Override
	public void crear(Libro aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new EntityExistsException("El usuario ya existe");
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public void editar(Libro aux) {
		try {
			em.getTransaction().begin();
			em.merge(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}

	}

	@Override
	public void borrar(Long id) {
		Libro delete = getById(id);
		try {
			em.getTransaction().begin();
			em.remove(delete);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}

	}

	@Override
	public List<Libro> getAll() {
		List<Libro> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Libro> q = em.createNamedQuery("getAllBooks", Libro.class);
			result = q.getResultList();
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public Libro getById(Long id) {
		Libro result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Libro> q = em.createNamedQuery("getBookById", Libro.class).setParameter("idLibro", id);
			result = q.getResultList().get(0);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public Libro getBookByName(String title) {
		Libro result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Libro> q = em.createNamedQuery("getBookByName", Libro.class).setParameter("titleLibro", title);
			result = q.getResultList().get(0);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public List<Libro> getBooksByAuthor(Usuario author) {
		List<Libro> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Libro> q = em.createNamedQuery("getBookFromAuthor", Libro.class).setParameter("author", author.getId());
			result = q.getResultList();
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}


	public List<Notas_Lib> getNotesOfBook(String title) {
		List<Notas_Lib> result = new ArrayList<Notas_Lib>();
		try {
			em.getTransaction().begin();
			TypedQuery<Notas_Lib> q = em.createNamedQuery("getAllBookNotes", Notas_Lib.class).setParameter("titleLibro", title);;
			result = q.getResultList();
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public Notas_Lib getNoteOfBook(Long id) {
		/*Notas_Lib result = null;
		try {
			List<Notas_Lib> list = getNotesOfBook();
			for (Notas_Lib notas_Lib : list) {
				if (notas_Lib.getId() == id) {
					result = notas_Lib;
				}
			}
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}*/
		return null;
	}
}
