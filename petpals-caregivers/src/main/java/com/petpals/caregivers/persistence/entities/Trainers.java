package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Trainers extends Caregivers {
}
