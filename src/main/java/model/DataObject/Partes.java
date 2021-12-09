package model.DataObject;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import model.IDataObject.IPartes;

@Entity
@Table(name="Partes")
@NamedQueries({
	@NamedQuery(name="getAll", query = "SELECT * FROM Partes"),
	@NamedQuery(name="getFromBook", query = "SELECT p FROM Partes p WHERE p.id_libro=:idlibro")
})
public class Partes implements IPartes, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="Id")
	protected Long id;
	@Column(name="nombre")
	protected String nombre;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_libro")
	protected Libro libro;
	
	public Partes() {
		this(-1L,"",new Libro());
	}
	
	public Partes(String nombre, Libro libro) {
		this.id = -1L;
		this.nombre = nombre;
		this.libro = libro;
	}
	
	public Partes(Long id, String nombre, Libro libro) {
		this.id = id;
		this.nombre = nombre;
		this.libro = libro;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partes other = (Partes) obj;
		if (id != other.id)
			return false;
		if (libro == null) {
			if (other.libro != null)
				return false;
		} else if (!libro.equals(other.libro))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Partes [id=" + id + ", nombre=" + nombre + ", libro=" + libro + "]";
	}
}
