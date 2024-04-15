package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TRAINERS")
public class Trainers extends Caregivers {
}
