package model.DataObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.IDataObject.IPartes;

@Entity
@Table(name="Partes")
@NamedQueries({
	@NamedQuery(name="getAllPartes", query = "SELECT p FROM Partes p"),
	@NamedQuery(name="getParteFromBook", query = "SELECT p FROM Partes p WHERE p.libro.id=:idlibro ")
})
public class Partes implements IPartes, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="Id")
	protected Long id;
	@Column(name="nombre")
	protected String nombre;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_libro")
	protected Libro libro;
	@OneToMany(mappedBy = "parte", cascade = { CascadeType.ALL }, orphanRemoval = true)
	protected List<Capitulo> capitulos = new ArrayList<Capitulo>();
	
	public Partes() {
		this(-1L,"",new Libro());
	}
	public Partes(String nombre, Libro libro, List<Capitulo> capitulos) {
		this.id = -1L;
		this.nombre = nombre;
		this.libro = libro;
		this.capitulos = capitulos;
	}
	public Partes(Long id, String nombre, Libro libro, List<Capitulo> capitulos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.libro = libro;
		this.capitulos = capitulos;
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
	
	public void addCapitulo(Capitulo capitulo) {
		this.capitulos.add(capitulo);
	}
	
	public boolean removeCapitulo(Capitulo capitulo) {
		return this.capitulos.remove(capitulo);
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
	public List<Capitulo> getCapitulos() {
		return capitulos;
	}
	public void setCapitulos(List<Capitulo> capitulos) {
		this.capitulos = capitulos;
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
		return nombre;
	}

	
}
