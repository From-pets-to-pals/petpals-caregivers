package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name="countries")
public class Countries {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "countries_generator")
	@SequenceGenerator(name = "countries_generator", sequenceName = "countries_seq", allocationSize = 1 )
	@Column(name="id")
	private Short id;
	
	@NotBlank
	@Column(name = "name",columnDefinition = "varchar(100)")
	private String name;
	
	@NotBlank
	@Column(name = "code",columnDefinition = "bpchar(2)")
	private String code;
	
	@NotBlank
	@Column(name = "number",columnDefinition = "bpchar(3)")
	private String number;

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
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Countries countries = (Countries) o;
		return Objects.equals(id, countries.id) && Objects.equals(name, countries.name) && Objects.equals(code, countries.code) && Objects.equals(number, countries.number);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, code, number);
	}

	@Override
	public String toString() {
		return "Countries{" +
				"id=" + id +
				", name='" + name + '\'' +
				", code='" + code + '\'' +
				", number='" + number + '\'' +
				'}';
	}
}
