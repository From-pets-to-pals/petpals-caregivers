package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.Entity;

@Entity
public class Groomers extends Caregivers {

    public Groomers() {
    }

    public Groomers(String reference, String firstName, String lastName, String email, String phoneNumber, String address, String city, String zipCode, String country, String[] workingDays, String[] palsHandled, boolean homeService, double appointmentDuration, double priceRating, double serviceRating) {
        super(reference, firstName, lastName, email, phoneNumber, address, city, zipCode, country, workingDays, palsHandled, homeService, appointmentDuration, priceRating, serviceRating);
    }
}
