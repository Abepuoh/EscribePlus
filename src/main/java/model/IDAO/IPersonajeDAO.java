package model.IDAO;

import java.util.List;

import model.DataObject.Personaje;
import model.DataObject.Usuario;

public interface IPersonajeDAO extends IDAO<Personaje, Long>{
    /**
     * Metodo que devuelve un personaje dado su nombre
     * @param nombre
     * @return
     */
    public Personaje getCharacterByName(String nombre);
    /**
     * Metodo que devuele una lista de personajes.
     * @return
     */
    public List<Personaje> getCharactersByUser(Usuario user);
}
