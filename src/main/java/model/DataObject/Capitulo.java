package model.DataObject;

public class Capitulo {

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import model.IDataObject.ICapitulo;

public class Capitulo implements ICapitulo,Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;
	@Column(name="name")
	protected String name;	
	@Column(name="text")
	protected String text;
	@OneToMany(mappedBy = "capitulo",cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<Notas_Cap> parts;
	@OneToMany(mappedBy = "recordatorio",cascade = CascadeType.ALL, orphanRemoval = true)
	protected Set<Recordatorio> reminders;
	
	public Capitulo() {
		this(-1L,"","",new HashSet<Notas_Cap>(),new HashSet<Recordatorio>());
	}
	public Capitulo(String name, String text) {
		this(-1L,name,text,new HashSet<Notas_Cap>(),new HashSet<Recordatorio>());
	}
	public Capitulo(String name, String text, Notas_Cap Note_cap) {
		this.id = -1L;
		this.name = name;
		this.text = text;
		this.parts = new HashSet<Notas_Cap>();
		this.parts.add(Note_cap);
		this.reminders = new HashSet<Recordatorio>();
	}
	public Capitulo(String name, String text, Set<Notas_Cap> part) {
		this.id = -1L;
		this.name = name;
		this.text = text;
		this.parts = part;
		this.reminders = new HashSet<Recordatorio>();
	}
	public Capitulo(Long id, String name, String text) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.parts = new HashSet<Notas_Cap>();
		this.reminders = new HashSet<Recordatorio>();
	}
	public Capitulo(Long id, String name, String text, Notas_Cap Note_cap) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.parts = new HashSet<Notas_Cap>();
		this.parts.add(Note_cap);
		this.reminders = new HashSet<Recordatorio>();
	}
	public Capitulo(Long id, String name, String text, Set<Notas_Cap> part, Set<Recordatorio> remiders) {
		this.id = id;
		this.name = name;
		this.text = text;
		this.parts = part;
		this.reminders = remiders;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Capitulo other = (Capitulo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Capitulo [id=" + id + ", name=" + name + ", text=" + text + ", parts=" + parts + ", reminders="
				+ reminders +
	
	
}