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
import model.DataObject.Personaje;
import model.DataObject.Usuario;
import model.IDAO.DAOException;
import model.IDAO.IPersonajeDAO;
import model.IDataObject.IPersonaje;

/**
 *
 * @author adryc
 */
public class PersonajeDAO implements IPersonajeDAO{
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    EntityManager em = emf.createEntityManager();
    
    //Queries
    private final String getAll="SELECT * FROM Personaje";
    private String getByName="SELECT p FROM Personaje p WHERE p.nombre = :nombrepersonaje";
    private String getFromBook="SELECT p FROM Personaje p WHERE p.id_libro=:idlibro";
    private String getFromUser="SELECT p FROM Personaje p JOIN Book b JOIN PersonajeLibro pl WHERE pl.personajeid = p.id AND pl.libroid = b.id AND b.id_user=:iduser"; //A la espera de usar la base de datos, para saber cual es el nombre
                                                                                                                  //de las variables idpersonaje e idlibro

    @Override
    public void crear(IPersonaje aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void editar(IPersonaje aux) {
        em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
    }

    @Override
    public void borrar(Long id) {
        IPersonaje delete = mostrarPorId(id);
        em.getTransaction().begin();
        em.persist(delete);
        em.getTransaction().commit();
    }

    @Override
    public List<IPersonaje> mostrarTodos() {
        List<IPersonaje> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<IPersonaje> q = em.createQuery(getAll, IPersonaje.class);
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public IPersonaje mostrarPorId(Long id) {
        IPersonaje result = null;
        em.getTransaction().begin();
        result = em.find(IPersonaje.class, id);
        em.getTransaction().commit();
        return result;
    }
    
    public List<IPersonaje> mostrarPorLibro(Libro l) {
        List<IPersonaje> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<IPersonaje> q = em.createQuery(getFromBook, IPersonaje.class).setParameter("idlibro", l.getId());
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Personaje getCharacterByName(String nombre) throws DAOException {
        Personaje result = null;
        em.getTransaction().begin();
        TypedQuery<Personaje> q = em.createQuery(getByName, Personaje.class).setParameter("nombrepersonaje", nombre);
        result = q.getResultList().get(0);
        em.getTransaction().commit();
        return result;
    }

    @Override
    public List<Personaje> getCharactersByUser(Usuario user) throws DAOException {
        List<Personaje> result = new ArrayList<>();
        em.getTransaction().begin();
        TypedQuery<Personaje> q = em.createQuery(getFromUser, Personaje.class).setParameter("iduser", user.getId());
        result = q.getResultList();
        em.getTransaction().commit();
        return result;
    }
    
}
