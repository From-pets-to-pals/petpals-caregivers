package com.petpals.caregivers.persistence.entities;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Type;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="caregivers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="caregiver_type",
        discriminatorType = DiscriminatorType.STRING)
public class Caregivers {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "caregivers_generator")
    @SequenceGenerator(name = "caregivers_generator", sequenceName = "caregivers_seq", allocationSize = 1 )
    @Column(name="caregiver_id")
    private Long caregiverId;

    @NotBlank
    @Column(name = "reference",columnDefinition = "bpchar(36)")
    private String reference;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name="last_name")
    private String lastName;

    @Email
    @Column(name="email")
    private String email;

    @NotBlank
    @Column(name="phone_number")
    private String phoneNumber;

    @NotBlank
    @Column(name="address")
    private String address;

    @NotBlank
    @Column(name="city")
    private String city;

    @NotBlank
    @Column(name="zip_code")
    private String zipCode;

    @OneToOne
    @JoinColumn(name = "country")
    private Countries country;

    @Type(StringArrayType.class)
    @Column(
            name = "working_days",
            columnDefinition = "text[]"
    )
    private String[] workingDays;

    @Type(StringArrayType.class)
    @Column(
            name = "pals_handled",
            columnDefinition = "text[]"
    )
    private String[] palsHandled;

    @NotNull
    @Column(name = "home_service")
    private boolean homeService;

    @DecimalMin(value = "0.25")
    @Column(name="appointment_duration", columnDefinition = "numeric(1,1) default 0.0")
    private double appointmentDuration;

    @DecimalMin(value = "0.0")
    @Column(name="price_rating", columnDefinition = "numeric(1,1) default 5.0")
    private double priceRating;

    @DecimalMin(value = "0.0")
    @Column(name="service_rating", columnDefinition = "numeric(1,1) default 5.0")
    private double serviceRating;

    @NotNull
    @Column(name="is_subscribed")
    private boolean isSubscribed;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "caregivers_pals",
            joinColumns = { @JoinColumn(name = "caregiver_id") },
            inverseJoinColumns = { @JoinColumn(name = "pal_id") }
    )
    List<Pals> clients;

    public Caregivers() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caregivers that = (Caregivers) o;
        return homeService == that.homeService && Double.compare(appointmentDuration, that.appointmentDuration) == 0 && Double.compare(priceRating, that.priceRating) == 0 && Double.compare(serviceRating, that.serviceRating) == 0 && isSubscribed == that.isSubscribed && Objects.equals(caregiverId, that.caregiverId) && Objects.equals(reference, that.reference) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(zipCode, that.zipCode) && Objects.equals(country, that.country) && Objects.deepEquals(workingDays, that.workingDays) && Objects.deepEquals(palsHandled, that.palsHandled) && Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(caregiverId, firstName, lastName, email, phoneNumber, address, city, zipCode, country, homeService, appointmentDuration, priceRating, serviceRating, isSubscribed, clients);
        result = 31 * result + Arrays.hashCode(workingDays);
        result = 31 * result + Arrays.hashCode(palsHandled);
        return result;
    }

    @Override
    public String toString() {
        return "Caregivers{" +
                "caregiverId=" + getCaregiverId() +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", city='" + getCity() + '\'' +
                ", zipCode='" + getZipCode() + '\'' +
                ", country='" + getCountry() + '\'' +
                ", workingDays=" + Arrays.toString(getWorkingDays()) +
                ", palsHandled=" + Arrays.toString(getPalsHandled()) +
                ", homeService=" + isHomeService() +
                ", appointmentDuration=" + getAppointmentDuration() +
                ", priceRating=" + getPriceRating() +
                ", serviceRating=" + getServiceRating() +
                ", isSubscribed=" + isSubscribed +
                ", clients=" + getClients() +
                '}';
    }

    public Long getCaregiverId() {
        return caregiverId;
    }

    public void setCaregiverId(Long caregiverId) {
        this.caregiverId = caregiverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String[] getWorkingDays() {
        return workingDays;
    }

    public void setWorkingDays(String[] workingDays) {
        this.workingDays = workingDays;
    }

    public String[] getPalsHandled() {
        return palsHandled;
    }

    public void setPalsHandled(String[] palsHandled) {
        this.palsHandled = palsHandled;
    }

    public boolean isHomeService() {
        return homeService;
    }

    public void setHomeService(boolean homeService) {
        this.homeService = homeService;
    }

    public double getAppointmentDuration() {
        return appointmentDuration;
    }

    public void setAppointmentDuration(double appointmentDuration) {
        this.appointmentDuration = appointmentDuration;
    }

    public double getPriceRating() {
        return priceRating;
    }

    public void setPriceRating(double priceRating) {
        this.priceRating = priceRating;
    }

    public double getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(double serviceRating) {
        this.serviceRating = serviceRating;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public List<Pals> getClients() {
        return clients;
    }

    public void setClients(List<Pals> clients) {
        this.clients = clients;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
