
package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Capitulo;
import model.DataObject.Libro;
import model.DataObject.Partes;
import model.IDAO.ICapituloDAO;
import utils.ConnectionUtil;


public class CapituloDAO implements ICapituloDAO {

	public static EntityManager createEM () {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

	// Queries
	//private final String getAll = "Select * from Capitulo";
	//private final String getCapituloFromParte = "SELECT p FROM Capitulo p WHERE p.id_partes=:idpartes";
	@Override
	public void crear(Capitulo aux) {
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
	public void editar(Capitulo aux) {
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
		Capitulo deleted = getById(id);
		try {
			em.getTransaction().begin();
			em.remove(deleted);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public List<Capitulo> getAll() {
		List<Capitulo> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Capitulo> q = em.createNamedQuery("getAll", Capitulo.class);
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
	public Capitulo getById(Long id) {
		Capitulo result = new Capitulo();
		try {
			em.getTransaction().begin();
			result = em.find(Capitulo.class, id);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}
	public List<Capitulo> getByParte(Partes p) {
		List<Capitulo> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Capitulo> q = em.createNamedQuery("getCapituloFromParte", Capitulo.class).setParameter("idpartes", p.getId());
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
