package model.IDAO;

import java.util.List;

import model.DataObject.Personaje;
import model.IDataObject.IPersonaje;

public interface IPersonajeDAO extends IDAO<IPersonaje, Long>{
    /**
     * Metodo que devuelve un personaje dado su nombre
     * @param nombre
     * @return
     */
    public Personaje getCharacterByName(String nombre) throws DAOException;
    /**
     * Metodo que devuele una lista de personajes.
     * @return
     */
    public List<Personaje> getCharactersByUser(String user) throws DAOException;
}
