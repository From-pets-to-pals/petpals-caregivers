package com.petpals.caregivers.application.dto;



import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;

import java.util.Arrays;
import java.util.Objects;

public record CreateCaregiver(String reference, String firstName, String lastName, String email, String phoneNumber, String address,
                              String city, String zipCode, String country, Days[] workingDays, SpeciesEnum[] palsHandled,
                              boolean homeService, double appointmentDuration,
                              PalsFriendsTypes caregiverType, boolean isSubscribed, double serviceRating,
                              double priceRating) {
    @Override
    public String toString() {
        return "CreateCaregiver{" +
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
                ", caregiverType=" + caregiverType +
                ", isSubscribed=" + isSubscribed +
                ", serviceRating=" + serviceRating +
                ", priceRating=" + priceRating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateCaregiver that = (CreateCaregiver) o;
        return Double.compare(priceRating, that.priceRating) == 0 && homeService == that.homeService && isSubscribed == that.isSubscribed && Double.compare(serviceRating, that.serviceRating) == 0 && Double.compare(appointmentDuration, that.appointmentDuration) == 0 && Objects.equals(city, that.city) && Objects.equals(email, that.email) && Objects.equals(address, that.address) && Objects.equals(zipCode, that.zipCode) && Objects.equals(country, that.country) && Objects.equals(lastName, that.lastName) && Objects.equals(reference, that.reference) && Objects.equals(firstName, that.firstName) && Objects.equals(phoneNumber, that.phoneNumber) && Objects.deepEquals(workingDays, that.workingDays) && Objects.deepEquals(palsHandled, that.palsHandled) && caregiverType == that.caregiverType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference, firstName, lastName, email, phoneNumber, address, city, zipCode, country, Arrays.hashCode(workingDays), Arrays.hashCode(palsHandled), homeService, appointmentDuration, caregiverType, isSubscribed, serviceRating, priceRating);
    }
}
