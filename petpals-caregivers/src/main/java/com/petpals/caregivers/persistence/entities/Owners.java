package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "owners")
public class Owners {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "owners_generator")
    @SequenceGenerator(name = "owners_generator", sequenceName = "owners_seq", allocationSize = 1 )
    private Long id;

    @Column(name = "email", nullable = false, unique=true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "salt", nullable = false)
    private String salt;

    @Column(name = "device", nullable = false)
    private String deviceId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "reference", nullable = false, columnDefinition = "bpchar(36)", unique = true)
    private String reference;


    @Column(name = "location", nullable = false)
    private String location;

    @OneToMany(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private List<Pals> pals;

    public Owners() {
    }

    public Owners(String email, String password, String salt, String deviceId, String reference, String location, String username) {
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.deviceId = deviceId;
        this.reference = reference;
        this.location = location;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Owners{" +
                "id=" + id +
                ", mail='" + email + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", username='" + username + '\'' +
                ", reference='" + reference + '\'' +
                ", location='" + location + '\'' +
                ", palsList=" + pals +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owners owners = (Owners) o;
        return Objects.equals(id, owners.id) && Objects.equals(email, owners.email) && Objects.equals(deviceId, owners.deviceId) && Objects.equals(username, owners.username) && Objects.equals(reference, owners.reference) && Objects.equals(location, owners.location) && Objects.equals(pals, owners.pals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, deviceId, username, reference, location, pals);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String device) {
        this.deviceId = device;
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

    public List<Pals> getPals() {
        return pals;
    }

    public void setPals(List<Pals> palsList) {
        this.pals = palsList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
