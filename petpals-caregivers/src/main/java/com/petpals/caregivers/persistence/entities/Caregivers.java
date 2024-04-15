package com.petpals.caregivers.persistence.entities;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.UniqueElements;

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
    @Column(name = "reference")
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

    @NotBlank
    @Column(name="country")
    private String country;

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
    @Column(name="appointment_duration")
    private double appointmentDuration;

    @DecimalMin(value = "0.0")
    @Column(name="price rating", columnDefinition = "numeric(1,1) default 2.5")
    private double priceRating;

    @DecimalMin(value = "0.0")
    @Column(name="service_rating", columnDefinition = "numeric(1,1) default 2.5")
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

    public Caregivers(
            String reference,
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String city,
            String zipCode,
            String country,
            String[] workingDays,
            String[] palsHandled,
            boolean homeService,
            double appointmentDuration,
            double priceRating,
            double serviceRating) {
        this.reference = reference;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
        this.workingDays = workingDays;
        this.palsHandled = palsHandled;
        this.homeService = homeService;
        this.appointmentDuration = appointmentDuration;
        this.priceRating = priceRating;
        this.serviceRating = serviceRating;
        this.isSubscribed = false;
    }

    public Caregivers createOne(String reference,
                                String firstName,
                                String lastName,
                                String email,
                                String phoneNumber,
                                String address,
                                String city,
                                String zipCode,
                                String country,
                                String[] workingDays,
                                String[] palsHandled,
                                boolean homeService,
                                double appointmentDuration,
                                double priceRating,
                                double serviceRating){
        return new Caregivers(
                reference,
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                city,
                zipCode,
                country,
                workingDays,
                palsHandled,
                homeService,
                appointmentDuration,
                priceRating,
                serviceRating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Caregivers that = (Caregivers) o;
        return caregiverId == that.caregiverId && homeService == that.homeService && Double.compare(that.appointmentDuration, appointmentDuration) == 0 && Double.compare(that.priceRating, priceRating) == 0 && Double.compare(that.serviceRating, serviceRating) == 0 && isSubscribed == that.isSubscribed && Objects.equals(firstName, that.firstName) && lastName.equals(that.lastName) && email.equals(that.email) && phoneNumber.equals(that.phoneNumber) && address.equals(that.address) && city.equals(that.city) && zipCode.equals(that.zipCode) && country.equals(that.country) && Arrays.equals(workingDays, that.workingDays) && Arrays.equals(palsHandled, that.palsHandled) && Objects.equals(clients, that.clients);
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
                "caregiverId=" + caregiverId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", workingDays=" + Arrays.toString(workingDays) +
                ", palsHandled=" + Arrays.toString(palsHandled) +
                ", homeService=" + homeService +
                ", appointmentDuration=" + appointmentDuration +
                ", priceRating=" + priceRating +
                ", serviceRating=" + serviceRating +
                ", isSubscribed=" + isSubscribed +
                ", clients=" + clients +
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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
