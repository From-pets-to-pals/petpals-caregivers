package com.petpals.caregivers.persistence.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Groomers extends Caregivers {
}
