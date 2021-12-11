package model.IDAO;

import java.util.List;

public interface IDAO <T,K> {
    /*
     * Method to create new Object
     * @param aux object
     */
     void crear (T aux);
    /*
     * Method to modify an Object
     * @param aux object
     */
    void editar (T aux);
    
    /*
     * Method to delete an Object
     * @param aux object
     */
    void borrar (K id);
    
    /*
     * Method to getAll Objects we want
     */
    List<T>getAll();
    /**
     * Method get an Objects by id
     * @param id variable type long
     */
    T getById (K id);

}
