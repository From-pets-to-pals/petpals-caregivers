package com.petpals.caregivers.domain.pojo;

import com.petpals.caregivers.persistence.entities.Pals;
import com.petpals.shared.entities.CaregiverTypes;

import java.util.List;

public class Groomers extends Caregivers {
    public Groomers(String reference, String firstName, String lastName, String email, String phoneNumber, String address, String city, String zipCode, String country, String[] workingDays, String[] palsHandled, boolean homeService, double appointmentDuration, double priceRating, double serviceRating, boolean isSubscribed, List<Pals> clients, CaregiverTypes caregiverType) {
        super(reference, firstName, lastName, email, phoneNumber, address, city, zipCode, country, workingDays, palsHandled, homeService, appointmentDuration, priceRating, serviceRating, isSubscribed, clients, caregiverType);
    }
}
