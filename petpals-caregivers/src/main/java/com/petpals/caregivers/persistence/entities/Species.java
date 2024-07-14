package com.petpals.caregivers.persistence.entities;

import com.petpals.caregivers.persistence.entities.Breeds;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name="species")
public class Species {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "species_generator")
	@SequenceGenerator(name = "species_generator", sequenceName = "species_seq", allocationSize = 1 )
	@Column(name="id")
	private Short id;

	@Column(name = "name", columnDefinition = "bpchar(3)")
	private String name;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "specie_id")
	private List<Breeds> breed;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Species species = (Species) o;
		return Objects.equals(id, species.id) && Objects.equals(name, species.name) && Objects.equals(breed, species.breed);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, breed);
	}

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Breeds> getBreed() {
		return breed;
	}

	public void setBreed(List<Breeds> breed) {
		this.breed = breed;
	}

	@Override
	public String toString() {
		return "Species{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
