package com.petpals.caregivers.domain.pojo;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.persistence.entities.Pals;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;

import java.util.List;

public class Groomers extends Caregivers {
    public Groomers(String reference, String firstName, String lastName, String email, String phoneNumber, String address, String city, String zipCode, String country, Days[] workingDays, Species[] palsHandled, boolean homeService, double appointmentDuration, double priceRating, double serviceRating, boolean isSubscribed, List<Pals> clients, CaregiverTypes caregiverType) {
        super(reference, firstName, lastName, email, phoneNumber, address, city, zipCode, country, workingDays, palsHandled, homeService, appointmentDuration, priceRating, serviceRating, isSubscribed, clients, caregiverType);
    }
}
