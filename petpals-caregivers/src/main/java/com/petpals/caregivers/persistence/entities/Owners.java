package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "owners")
public class Owners {
    @Id
    @GeneratedValue
    private Long id;

    @Email
    @Column(name = "email", nullable = false)
    private String mail;

    @NotBlank
    @Column(name = "device", nullable = false)
    private String device;
    @NotBlank
    @Column(name = "reference", nullable = false)
    private String reference;


    @NotBlank
    @Column(name = "location", nullable = false)
    private String location;

    public Owners() {
    }

    public Owners(String mail, String device, String reference, String location) {
        this.mail = mail;
        this.device = device;
        this.reference = reference;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Owners{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", device='" + device + '\'' +
                ", reference='" + reference + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owners owners = (Owners) o;
        return Objects.equals(id, owners.id) && mail.equals(owners.mail) && Objects.equals(device, owners.device) && reference.equals(owners.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, device, reference);
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
