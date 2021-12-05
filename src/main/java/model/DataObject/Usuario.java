package model.DataObject;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.IDataObject.IUsuario;

@Entity
@Table(name="usuario")

public class Usuario implements IUsuario, Serializable {

	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @Column(name="email")
    private String email;
    @Column(name="contraseña")
    private String contraseña;
    @Column(name="phone")
    private String phone;
    @OneToMany(mappedBy = "usuario", cascade = { CascadeType.ALL }, orphanRemoval = true)
    private Set<Libro> books = new HashSet<Libro>();

    public Usuario() {
        this(-1L,"","","","", new HashSet<Libro>());
    }

    public Usuario(Long id, String nombre, String email, String contraseña, String phone) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.phone = phone;
    }
    
    public Usuario(String nombre, String email, String contraseña, String phone) {
        this.id = -1L;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.phone = phone;
    }
    
    public Usuario(Long id, String nombre, String email, String contraseña, String phone, Set<Libro> books) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.phone = phone;
        this.books = books;
    }
    
    public Usuario(String nombre, String email, String contraseña, String phone, Set<Libro> books) {
        this.id = -1L;
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.phone = phone;
        this.books = books;
    }

    /**
     * @return the id
     */
    @Override
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setID(Long id) {
        this.id = id;
    }

    /**
     * @return the nombre
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contraseña
     */
    @Override
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    @Override
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the phone
     */
    @Override
    public String getTelefono() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    @Override
    public void setTelefono(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    /**
     * @return the books
     */
    public Set<Libro> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(Set<Libro> books) {
        this.books = books;
    }
   

}
