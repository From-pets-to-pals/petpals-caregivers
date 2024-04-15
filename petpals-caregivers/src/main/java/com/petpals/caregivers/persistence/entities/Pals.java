package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    @Column(name="specie", nullable = false)
    private String specie;
    @NotNull
    @Column(name="breed", nullable = false)
    private String breed;

    @Column(name="has_passport")
    private boolean hasPassport;
    @NotNull
    @Column(name="isMale", nullable = false)
    private boolean isMale;

    @Column(name="is_sterilized")
    private boolean isSterilized;

    @Column(name="is_vaccinated")
    private boolean isVaccinated;

    @Column(name="nextVaccine")
    private Date nextVaccine;
    @Column(name="nextPlannedApp")
    private Date nextPlannedApp;

    @Column(name="palReference")
    private String palReference;
    @ManyToMany(mappedBy = "clients")
    private List<Caregivers> caregivers;
    public Pals() {
    }

    public Pals(String name,
                @NotBlank String ICADIdentifier,
                Owners owner,
                Date birthDate,
                @NotBlank String specie,
                @NotBlank String breed,
                @NotBlank String palReference,
                boolean hasPassport,
                @NotNull boolean isMale,
                boolean isSterilized,
                boolean isVaccinated,
                Date nextVaccine,
                Date nextPlannedApp) {
        this.name = name;
        this.icadidentifier = ICADIdentifier;
        this.owner = owner;
        this.birthDate = birthDate;
        this.specie = specie;
        this.breed = breed;
        this.palReference = palReference;
        this.hasPassport = hasPassport;
        this.isMale = isMale;
        this.isSterilized = isSterilized;
        this.isVaccinated = isVaccinated;
        this.nextVaccine = nextVaccine;
        this.nextPlannedApp = nextPlannedApp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pals pals = (Pals) o;
        return hasPassport == pals.hasPassport && isMale == pals.isMale && isSterilized == pals.isSterilized && isVaccinated == pals.isVaccinated && Objects.equals(id, pals.id) && name.equals(pals.name) && icadidentifier.equals(pals.icadidentifier) && Objects.equals(owner, pals.owner) && Objects.equals(birthDate, pals.birthDate) && specie.equals(pals.specie) && breed.equals(pals.breed) && Objects.equals(nextVaccine, pals.nextVaccine) && Objects.equals(nextPlannedApp, pals.nextPlannedApp) && palReference.equals(pals.palReference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, icadidentifier, owner, birthDate, specie, breed, hasPassport, isMale, isSterilized, isVaccinated, nextVaccine, nextPlannedApp, palReference);
    }

    @Override
    public String toString() {
        return "Pals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ICADIdentifier='" + icadidentifier + '\'' +
                ", owner=" + owner +
                ", birthDate=" + birthDate +
                ", specie='" + specie + '\'' +
                ", breed='" + breed + '\'' +
                ", hasPassport=" + hasPassport +
                ", isMale=" + isMale +
                ", isSterilized=" + isSterilized +
                ", isVaccinated=" + isVaccinated +
                ", nextVaccine=" + nextVaccine +
                ", nextPlannedApp=" + nextPlannedApp +
                ", palReference='" + palReference + '\'' +
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

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
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
