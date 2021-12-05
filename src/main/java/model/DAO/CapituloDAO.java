/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.DataObject.Capitulo;
import model.IDAO.ICapituloDAO;
import model.IDataObject.ICapitulo;

/**
 *
 * @author adryc
 */
public class CapituloDAO implements ICapituloDAO{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    EntityManager em = emf.createEntityManager();
    
    //Queries
    private final String getAll="Select * from Capitulo";

    @Override
    public void crear(ICapitulo aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void editar(ICapitulo aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void borrar(Long id) {
        ICapitulo deleted = mostrarPorId(id);
        em.getTransaction().begin();
        em.remove(deleted);
        em.getTransaction().commit();
    }

    @Override
    public List<ICapitulo> mostrarTodos() {
        List<ICapitulo> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<ICapitulo> q = em.createQuery(getAll, ICapitulo.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public ICapitulo mostrarPorId(Long id) {
        Capitulo result = null;
        em.getTransaction().begin();
        result = em.find(Capitulo.class, id);
        em.getTransaction().commit();
        return result;
    }
}
