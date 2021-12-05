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
import model.DataObject.Libro;
import model.IDAO.IPartesDAO;
import model.IDataObject.IPartes;

/**
 *
 * @author adryc
 */
public class PartesDAO implements IPartesDAO{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    EntityManager em = emf.createEntityManager();
    
    //Queries
    private final String getAll="SELECT * FROM Partes";
    private String getFromBook="SELECT p FROM Partes p WHERE p.id_libro=:idlibro";

    @Override
    public void crear(IPartes aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void editar(IPartes aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void borrar(Long id) {
        IPartes delete = mostrarPorId(id);
        em.getTransaction().begin();
        em.persist(delete);
        em.getTransaction().commit();
    }

    @Override
    public List<IPartes> mostrarTodos() {
        List<IPartes> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<IPartes> q = em.createQuery(getAll, IPartes.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public IPartes mostrarPorId(Long id) {
        IPartes result = null;
        em.getTransaction().begin();
        result = em.find(IPartes.class, id);
        em.getTransaction().commit();
        return result;
    }
    
    public List<IPartes> mostrarPorLibro(Libro l) {
        List<IPartes> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<IPartes> q = em.createQuery(getFromBook, IPartes.class).setParameter("idlibro", l.getId());
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }
    
}
