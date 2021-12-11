
package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Libro;
import model.DataObject.Partes;
import model.IDAO.IPartesDAO;
import utils.ConnectionUtil;

public class PartesDAO implements IPartesDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

//	// Queries
//	private final String getAllPartes = "SELECT * FROM Partes";
//	private String getParteFromBook = "SELECT p FROM Partes p WHERE p.id_libro=:idlibro";

	@Override
	public void crear(Partes aux) {
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
	public void editar(Partes aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public void borrar(Long id) {
		Partes delete = mostrarPorId(id);
		em.getTransaction().begin();
		em.remove(delete);
		em.getTransaction().commit();
	}

	@Override
	public List<Partes> mostrarTodos() {
		List<Partes> result = new ArrayList<>();
		em.getTransaction().begin();
		TypedQuery<Partes> q = em.createNamedQuery("getAllPartes", Partes.class);
		result = q.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public Partes mostrarPorId(Long id) {
		Partes result = new Partes();
		try {
			em.getTransaction().begin();
			result = em.find(Partes.class, id);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	public List<Partes> mostrarPorLibro(Libro l) {
		List<Partes> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Partes> q = em.createNamedQuery("getParteFromBook", Partes.class).setParameter("idlibro", l.getId());
			result = q.getResultList();
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

}
