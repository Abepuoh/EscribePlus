
package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Notas_Cap;
import model.IDAO.INotas_capDAO;
import model.IDataObject.INotas_cap;
import utils.ConnectionUtil;


public class Notas_CapDAO implements INotas_capDAO{
    
	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstace();
		return emf.createEntityManager();
	}
	EntityManager em = createEM();
    
    //Queries
    private final String getAll="Select * from Notas_Cap";

    @Override
    public void crear(Notas_Cap aux) {
        try {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
        } catch (EntityExistsException e) {
			throw new EntityExistsException("El usuario ya existe");
		} catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public void editar(Notas_Cap aux) {
        try {
    	em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
        } catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public void borrar(Long id) {
        INotas_cap deleted = mostrarPorId(id);
        try {
        em.getTransaction().begin();
        em.remove(deleted);
        em.getTransaction().commit();
        } catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public List<Notas_Cap> mostrarTodos() {
        List<Notas_Cap> result = new ArrayList<>();
        try {
        em.getTransaction().begin();
        TypedQuery<Notas_Cap> q = em.createQuery(getAll, Notas_Cap.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
        } catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }

    @Override
    public Notas_Cap mostrarPorId(Long id) {
    	Notas_Cap result = new Notas_Cap();
        try {
        em.getTransaction().begin();
        result = em.find(Notas_Cap.class, id);
        em.getTransaction().commit();
        return result;
        } catch	(IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion"); 
		}
    }  
}
