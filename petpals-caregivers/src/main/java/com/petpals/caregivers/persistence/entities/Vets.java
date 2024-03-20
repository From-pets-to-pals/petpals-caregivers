package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("0")
public class Vets extends Caregivers {
}
