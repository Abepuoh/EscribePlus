package model.DataObject;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Recordatorio  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement
	@Column(name="Id")
	private Long id;
	@Column(name="Fecha", columnDefinition = "DATE")
	private LocalDate fecha;
	@Column(name="Comentario")
	private String comentario;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="Id_Capitulo")
	private Capitulo capitulo;
	public Recordatorio(Long id, LocalDate fecha, String comentario, Capitulo capitulo) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.comentario = comentario;
		this.capitulo = capitulo;
	}
	public Recordatorio(LocalDate fecha, String comentario, Capitulo capitulo) {
		super();
		this.fecha = fecha;
		this.comentario = comentario;
		this.capitulo = capitulo;
	}
	public Recordatorio(LocalDate fecha, String comentario) {
		super();
		this.fecha = fecha;
		this.comentario = comentario;
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
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Capitulo getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(Capitulo capitulo) {
		this.capitulo = capitulo;
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
		if (capitulo == null) {
			if (other.capitulo != null)
				return false;
		} else if (!capitulo.equals(other.capitulo))
			return false;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
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
		return true;
	}
	
	
}
