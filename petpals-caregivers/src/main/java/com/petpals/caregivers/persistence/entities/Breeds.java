package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name="breeds")
public class Breeds {
	@NotBlank
	@Column(name = "name", columnDefinition = "varchar(100)")
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "breeds_generator")
	@SequenceGenerator(name = "breeds_generator", sequenceName = "breeds_seq", allocationSize = 1)
	@Column(name = "id")
	public Short id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "specie_id", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "breeds_species_id_fk", value = ConstraintMode.PROVIDER_DEFAULT))
	private Species specie;
	
	@Override
	public String toString() {
		return "Breeds{" +
					   "name='" + name + '\'' +
					   ", id=" + id +
					   ", specie=" + specie +
					   '}';
	}
	
	@Override
	public boolean equals(Object o) {
		
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Breeds breeds = (Breeds) o;
		return Objects.equals(name, breeds.name) && Objects.equals(id, breeds.id) && Objects.equals(specie, breeds.specie);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, id, specie);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Short getId() {
		return id;
	}
	
	public void setId(Short id) {
		this.id = id;
	}
	
	public Species getSpecie() {
		return specie;
	}
	
	public void setSpecie(Species specie) {
		this.specie = specie;
	}
}