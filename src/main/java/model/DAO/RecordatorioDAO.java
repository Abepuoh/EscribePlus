package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Recordatorio;
import model.DataObject.Usuario;
import model.IDAO.IRecordatorioDAO;
import utils.ConnectionUtil;

public class RecordatorioDAO implements IRecordatorioDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

	@Override
	public void crear(Recordatorio aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new EntityExistsException("El recordatorio ya existe");
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void editar(Recordatorio aux) {
		try {
			em.getTransaction().begin();
			em.merge(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void borrar(Long id) {
		Recordatorio delete = getById(id);
		try {
			em.getTransaction().begin();
			em.remove(delete);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public List<Recordatorio> getAll() {
		List<Recordatorio> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Recordatorio> q = em.createNamedQuery("getAllRecordatorios", Recordatorio.class);
			result = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
	}

	@Override
	public Recordatorio getById(Long id) {
		Recordatorio result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Recordatorio> q = em.createNamedQuery("getRecordatorioById", Recordatorio.class).setParameter("id", id);
			result = q.getResultList().get(0);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public Recordatorio getRecordatorioByBook(String bookName) {
		Recordatorio result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Recordatorio> q = em.createNamedQuery("getRecordatorioByBook", Recordatorio.class).setParameter("bookName", bookName);
			result = q.getResultList().get(0);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

}