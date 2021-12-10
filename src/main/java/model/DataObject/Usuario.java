package model.DataObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="User")
@NamedQueries({
	@NamedQuery(name="getAllUsers", query = "SELECT u FROM User u"),
	@NamedQuery(name="getUserById", query = "SELECT p FROM User p WHERE p.id = :idUsuario"),
	@NamedQuery(name="getUserByName", query = "SELECT p FROM User p WHERE p.nombre = :nombreUsuario"),
	@NamedQuery(name="getUserByEmail", query = "SELECT p FROM User p WHERE p.correo=:emailUsuario")
})
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;
    @Column(name="name")
    protected String name;
    @Column(name="email")
    protected String email;
    @Column(name="password")
    protected String password;
    @Column(name="phone")
    protected int phone;
    @OneToMany(mappedBy = "User", cascade = { CascadeType.ALL }, orphanRemoval = true)
    protected List<Libro> books = new ArrayList<Libro>();

    public Usuario() {
        this(-1L,"","","",-1, new ArrayList<Libro>());
    }
    public Usuario(String name, String email, String password) {
        this.id = -1L;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = -1;
        this.books = new ArrayList<Libro>();
    }
    public Usuario(String name, String email, String password, int phone) {
        this.id = -1L;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone =-1;
        this.books = new ArrayList<Libro>();
    }
    public Usuario(Long id, String name, String email, String password, int phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = -1;
        this.books = new ArrayList<Libro>();
    }
    public Usuario(Long id, String name, String email, String password, int phone, List<Libro> books) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = -1;
        this.books = books;  
    }
    
    public Usuario(String email, String password) {
    	this(-1L,"",email,password,-1, new ArrayList<Libro>());
	}

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phone
     */
    public int getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(int phone) {
        this.phone = phone;
    }

    /**
     * @return the books
     */
    public List<Libro> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<Libro> books) {
        this.books = books;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.getId());
        return hash;
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
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
    }
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", phone="
				+ phone + ", books=" + books + "]";
	}
}

