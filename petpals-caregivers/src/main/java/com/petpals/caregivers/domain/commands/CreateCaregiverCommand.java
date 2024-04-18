package com.petpals.caregivers.domain.commands;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.persistence.entities.Pals;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CreateCaregiverCommand {
    private String reference;
    @NotBlank
    private final String firstName;
    @NotBlank
    private final String lastName;
    @Email
    private final String email;
    @NotBlank
    @Length(min = 10, max = 10)
    private final String phoneNumber;
    @NotBlank
    private final String address;
    @NotBlank
    private final String city;
    @NotBlank
    @Length(min = 5, max = 5)
    private final String zipCode;
    @NotBlank
    private final String country;
    @NotNull
    private final Days[] workingDays;
    @NotNull
    private final Species[] palsHandled;
    @NotNull
    private final boolean homeService;
    @Positive
    private final double appointmentDuration;
    private double priceRating;
    private double serviceRating;
    private boolean isSubscribed;
    @NotNull
    private final CaregiverTypes caregiverType;

    public CreateCaregiverCommand(
            String reference,
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String city,
            String zipCode,
            String country,
            Days[] workingDays,
            Species[] palsHandled,
            boolean homeService,
            double appointmentDuration,
            double priceRating,
            double serviceRating,
            boolean isSubscribed,
            List<Pals> clients,
            CaregiverTypes caregiverType
    ) {
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
        this.isSubscribed = isSubscribed;
        this.caregiverType = caregiverType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCaregiverCommand that = (CreateCaregiverCommand) o;
        return Double.compare(priceRating, that.priceRating) == 0 && homeService == that.homeService && Double.compare(serviceRating, that.serviceRating) == 0 && isSubscribed == that.isSubscribed && Double.compare(appointmentDuration, that.appointmentDuration) == 0 && Objects.equals(city, that.city) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(zipCode, that.zipCode) && Objects.equals(country, that.country) && Objects.equals(lastName, that.lastName) && Objects.equals(reference, that.reference) && Objects.equals(firstName, that.firstName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.deepEquals(workingDays, that.workingDays) && Objects.deepEquals(palsHandled, that.palsHandled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, firstName, lastName, email, phoneNumber, address, city, zipCode, country, Arrays.hashCode(workingDays), Arrays.hashCode(palsHandled), homeService, appointmentDuration, priceRating, serviceRating, isSubscribed);
    }

    @Override
    public String toString() {
        return "Caregivers{" +
                "reference='" + reference + '\'' +
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
                '}';
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public Days[] getWorkingDays() {
        return workingDays;
    }

    public Species[] getPalsHandled() {
        return palsHandled;
    }

    public boolean isHomeService() {
        return homeService;
    }

    public double getAppointmentDuration() {
        return appointmentDuration;
    }

    public double getPriceRating() {
        return priceRating;
    }

    public double getServiceRating() {
        return serviceRating;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }


    public CaregiverTypes getCaregiverType() {
        return caregiverType;
    }

    public void setPriceRating(double priceRating) {
        this.priceRating = priceRating;
    }

    public void setServiceRating(double serviceRating) {
        this.serviceRating = serviceRating;
    }
}
