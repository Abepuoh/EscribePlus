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
import model.DataObject.Personaje;
import model.DataObject.Usuario;
import model.IDAO.ILibroDAO;
import utils.ConnectionUtil;

public class LibroDAO implements ILibroDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();


	@Override
	public void crear(Libro aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			e.printStackTrace();
			throw new EntityExistsException("El usuario ya existe");
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public Libro getBookByName(String title) {
		Libro result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Libro> q = em.createNamedQuery("getBookByName", Libro.class).setParameter("titleLibro", title);
			em.getTransaction().commit();
			if(q.getResultList().size()>0) {
			result = q.getResultList().get(0);
			}else {
				result = null;
			}
			return result;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
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
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	public void addCharactertoBook(Personaje p, Libro l) {
		try {
			em.getTransaction().begin();
			l.addCharacter(p);
			em.merge(l);
			em.getTransaction().commit();
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}	
	
	public void deleteCharacterFromBook(Personaje p, Libro l) {
		try { 
			List aux; 
			em.getTransaction().begin();
			aux = l.getPersonajes();
			aux.remove(p);
			l.setPersonajes(aux);
			em.merge(l);
			em.getTransaction().commit();			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}
}
