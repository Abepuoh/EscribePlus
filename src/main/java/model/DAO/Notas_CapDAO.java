
package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.IDAO.INotas_capDAO;
import model.IDataObject.INotas_cap;

/**
 *
 * @author adryc
 */
public class Notas_CapDAO implements INotas_capDAO{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    EntityManager em = emf.createEntityManager();
    
    //Queries
    private final String getAll="Select * from Notas_Cap";

    @Override
    public void crear(INotas_cap aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void editar(INotas_cap aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void borrar(Long id) {
        INotas_cap deleted = mostrarPorId(id);
        em.getTransaction().begin();
        em.remove(deleted);
        em.getTransaction().commit();
    }

    @Override
    public List<INotas_cap> mostrarTodos() {
        List<INotas_cap> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<INotas_cap> q = em.createQuery(getAll, INotas_cap.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public INotas_cap mostrarPorId(Long id) {
        INotas_cap result = null;
        em.getTransaction().begin();
        result = em.find(INotas_cap.class, id);
        em.getTransaction().commit();
        return result;
    }
    
}
