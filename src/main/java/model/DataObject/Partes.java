package model.DataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Partes")
public class Partes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement
	@Column(name="Id")
	private Long id;
	@Column(name="nombre")
	private String nombre;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_libro")
	private Libro libro;
	public Partes(Long id, String nombre, Libro libro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.libro = libro;
	}
	
	public Partes(String nombre, Libro libro) {
		super();
		this.nombre = nombre;
		this.libro = libro;
	}

	public Partes() {
		this(-1L,"Default",new Libro());
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
	
}
