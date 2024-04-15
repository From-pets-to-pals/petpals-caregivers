package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VETS")
public class Vets extends Caregivers {
}
