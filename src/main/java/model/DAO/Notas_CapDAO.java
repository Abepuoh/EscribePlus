package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Capitulo;
import model.DataObject.Notas_Cap;
import model.IDAO.INotas_capDAO;
import model.IDataObject.INotas_cap;
import utils.ConnectionUtil;

public class Notas_CapDAO implements INotas_capDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

	// Queries
	//private final String getAll = "Select * from Notas_Cap";
	//private String getNotasFromCapitulo = "SELECT p FROM Notas p WHERE p.id_capitlo=:idcapitulo";
	@Override
	public void crear(Notas_Cap aux) {
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
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void editar(Notas_Cap aux) {
		try {
			em.getTransaction().begin();
			em.merge(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void borrar(Long id) {
		INotas_cap deleted = getById(id);
		try {
			em.getTransaction().begin();
			em.remove(deleted);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public List<Notas_Cap> getAll() {
		List<Notas_Cap> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Notas_Cap> q = em.createNamedQuery("getAllNotas_Cap", Notas_Cap.class);
			result = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
	}

	@Override
	public Notas_Cap getById(Long id) {
		Notas_Cap result = new Notas_Cap();
		try {
			em.getTransaction().begin();
			result = em.find(Notas_Cap.class, id);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
	}
	public List<Notas_Cap> getFromCapitulos(Capitulo c){
        List <Notas_Cap> result = new ArrayList<Notas_Cap>();
		try {
			em.getTransaction().begin();
			TypedQuery<Notas_Cap> q = em.createNamedQuery("getFromCapitulos", Notas_Cap.class).setParameter("idcapitulo", c.getId());
						result = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
    }
}
