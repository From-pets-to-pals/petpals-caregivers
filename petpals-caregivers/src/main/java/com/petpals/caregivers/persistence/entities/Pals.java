package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "pals")
public class Pals {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "pals_generator")
    @Column(name = "pal_id")
    @SequenceGenerator(name="pals_generator", sequenceName = "pals_seq", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name")
    private String shortname;

    @NotNull
    @Length(min=15, max=15)
    @Column(name = "icadidentifier", nullable = false,columnDefinition = "bpchar(15)")
    private String icadidentifier;

    @ManyToOne(fetch = FetchType.EAGER)
    private Owners owner;

    @Column(name = "birthdate")
    private Date birthDate;
    @NotNull
    @Column(name="breed", nullable = false)
    private Short breed;

    @Column(name="has_passport")
    private boolean hasPassport;
    @NotNull
    @Column(name="is_male", nullable = false)
    private boolean isMale;

    @Column(name="is_sterilized")
    private boolean isSterilized;

    @Column(name="is_vaccinated")
    private boolean isVaccinated;

    @Column(name="next_vaccine")
    private Date nextVaccine;
    @Column(name="next_planned_app")
    private Date nextPlannedApp;

    @Column(name="reference",columnDefinition = "bpchar(36)")
    private String palReference;

    @ManyToMany(mappedBy = "clients")
    private List<Caregivers> caregivers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pals pals = (Pals) o;
        return hasPassport == pals.hasPassport && isMale == pals.isMale && isSterilized == pals.isSterilized && isVaccinated == pals.isVaccinated && Objects.equals(id, pals.id) && Objects.equals(name, pals.name) && Objects.equals(shortname, pals.shortname) && Objects.equals(icadidentifier, pals.icadidentifier) && Objects.equals(owner, pals.owner) && Objects.equals(birthDate, pals.birthDate) && Objects.equals(breed, pals.breed) && Objects.equals(nextVaccine, pals.nextVaccine) && Objects.equals(nextPlannedApp, pals.nextPlannedApp) && Objects.equals(palReference, pals.palReference) && Objects.equals(caregivers, pals.caregivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortname, icadidentifier, owner, birthDate, breed, hasPassport, isMale, isSterilized, isVaccinated, nextVaccine, nextPlannedApp, palReference, caregivers);
    }

    @Override
    public String toString() {
        return "Pals{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", ICADIdentifier='" + getICADIdentifier() + '\'' +
                ", owner=" + getOwner() +
                ", birthDate=" + getBirthDate() +
                ", breed='" + getBreed() + '\'' +
                ", hasPassport=" + isHasPassport() +
                ", isMale=" + isMale() +
                ", isSterilized=" + isSterilized() +
                ", isVaccinated=" + isVaccinated() +
                ", nextVaccine=" + getNextVaccine() +
                ", nextPlannedApp=" + getNextPlannedApp() +
                ", palReference='" + getPalReference() + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getICADIdentifier() {
        return icadidentifier;
    }

    public void setICADIdentifier(String ICADIdentifier) {
        this.icadidentifier = ICADIdentifier;
    }

    public void setOwner(Owners owner) {
        this.owner = owner;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }


    public Short getBreed() {
        return breed;
    }

    public void setBreed(Short breed) {
        this.breed = breed;
    }

    public boolean isHasPassport() {
        return hasPassport;
    }

    public void setHasPassport(boolean hasPassport) {
        this.hasPassport = hasPassport;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public boolean isSterilized() {
        return isSterilized;
    }

    public void setSterilized(boolean sterilized) {
        isSterilized = sterilized;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public Date getNextVaccine() {
        return nextVaccine;
    }

    public void setNextVaccine(Date nextVaccine) {
        this.nextVaccine = nextVaccine;
    }

    public Date getNextPlannedApp() {
        return nextPlannedApp;
    }

    public void setNextPlannedApp(Date nextPlannedApp) {
        this.nextPlannedApp = nextPlannedApp;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public Owners getOwner() {
        return owner;
    }

    public String getPalReference() {
        return palReference;
    }

    public void setPalReference(String palReference) {
        this.palReference = palReference;
    }

    public List<Caregivers> getCaregivers() {
        return caregivers;
    }

    public void setCaregivers(List<Caregivers> caregivers) {
        this.caregivers = caregivers;
    }
}
