
package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.IDAO.INotas_libDAO;
import model.IDataObject.INotas_lib;

/**
 *
 * @author adryc
 */
public class Notas_LibDAO implements INotas_libDAO{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    EntityManager em = emf.createEntityManager();
    
    //Queries
    private final String getAll="Select * from Notas_Lib";

    @Override
    public void crear(INotas_lib aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void editar(INotas_lib aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void borrar(Long id) {
        INotas_lib delete = mostrarPorId(id);
        em.getTransaction().begin();
        em.persist(delete);
        em.getTransaction().commit();
    }

    
    @Override
    public List<INotas_lib> mostrarTodos() {
        List<INotas_lib> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<INotas_lib> q = em.createQuery(getAll, INotas_lib.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public INotas_lib mostrarPorId(Long id) {
        INotas_lib result = null;
        em.getTransaction().begin();
        result = em.find(INotas_lib.class, id);
        em.getTransaction().commit();
        return result;
    }
    
}
