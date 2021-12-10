package model.DataObject;

import java.io.Serializable;
import java.time.LocalDate;

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

import model.IDataObject.IRecordatorio;

@Entity
@Table (name = "Recordatorio")
@NamedQueries({
	@NamedQuery(name="getAllRecordatorios", query = "SELECT * FROM Recordatorio")
})
public class Recordatorio implements IRecordatorio, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name="Id")
	protected Long id;
	@Column(name="Fecha", columnDefinition = "DATE")
	protected LocalDate fecha;
	@Column(name="Comentario")
	protected String texto;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Id_Libro")
	protected Libro libro;
	
	
	public Recordatorio() {
		this(-1L,LocalDate.now(),"", new Libro());
	}
	public Recordatorio(String texto) {
		this.id = -1L;
		this.fecha = LocalDate.now();
		this.texto = texto;
		this.libro = new Libro();
	}
	public Recordatorio(String texto, Libro libro) {
		this.id = -1L;
		this.fecha = LocalDate.now();
		this.texto = texto;
		this.libro = libro;
	}
	public Recordatorio(LocalDate fecha, String texto) {
		this.id = -1L;
		this.fecha = fecha;
		this.texto = texto;
		this.libro = new Libro();
	}
	public Recordatorio(LocalDate fecha, String texto, Libro libro) {
		this.fecha = fecha;
		this.texto = texto;
		this.libro = libro;
	}
	public Recordatorio(Long id, LocalDate fecha, String texto, Libro libro) {
		this.id = id;
		this.fecha = fecha;
		this.texto = texto;
		this.libro = libro;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libro == null) ? 0 : libro.hashCode());
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recordatorio other = (Recordatorio) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (libro == null) {
			if (other.libro != null)
				return false;
		} else if (!libro.equals(other.libro))
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Recordatorio [id=" + id + ", fecha=" + fecha + ", texto=" + texto + ", libro=" + libro + "]";
	}
}