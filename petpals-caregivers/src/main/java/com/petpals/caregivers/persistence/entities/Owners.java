package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Objects;

@Entity
@Table(name = "owners")
public class Owners {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owners_generator")
    @SequenceGenerator(name = "owners_generator", sequenceName = "owners_seq", allocationSize = 1 )
    private Long id;

    @Email
    @Column(name = "email", nullable = false)
    @UniqueElements
    private String mail;

    @NotBlank
    @Column(name = "device", nullable = false)
    private String device;
    @NotBlank
    @Column(name = "reference", nullable = false, columnDefinition = "bpchar(36)")
    @UniqueElements
    private String reference;


    @NotBlank
    @Column(name = "location", nullable = false)
    private String location;

    public Owners() {
    }

    @Override
    public String toString() {
        return "Owners{" +
                "id=" + getId() +
                ", mail='" + getMail() + '\'' +
                ", device='" + getDevice() + '\'' +
                ", reference='" + getReference() + '\'' +
                ", location='" + getLocation() + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owners owners = (Owners) o;
        return Objects.equals(id, owners.id) && Objects.equals(mail, owners.mail) && Objects.equals(device, owners.device) && Objects.equals(reference, owners.reference) && Objects.equals(location, owners.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, device, reference, location);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
