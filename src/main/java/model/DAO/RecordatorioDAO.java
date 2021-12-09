package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.DataObject.Recordatorio;
import model.IDAO.IRecordatorioDAO;
import model.IDataObject.IRecordatorio;

public class RecordatorioDAO implements IRecordatorioDAO{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    EntityManager em = emf.createEntityManager();
    
	@Override
	public void crear(Recordatorio aux) {
		em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();		
	}

	@Override
	public void editar(Recordatorio aux) {
		em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();		
	}

	@Override
	public void borrar(Long id) {
		IRecordatorio deleted = mostrarPorId(id);
        em.getTransaction().begin();
        em.remove(deleted);
        em.getTransaction().commit();		
	}

	@Override
	public List<Recordatorio> mostrarTodos() {
		List<Recordatorio> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<Recordatorio> q = em.createQuery("getAll", Recordatorio.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
	}

	@Override
	public Recordatorio mostrarPorId(Long id) {
		Recordatorio result = null;
        em.getTransaction().begin();
        result = em.find(Recordatorio.class, id);
        em.getTransaction().commit();
        return result;
	}

}
