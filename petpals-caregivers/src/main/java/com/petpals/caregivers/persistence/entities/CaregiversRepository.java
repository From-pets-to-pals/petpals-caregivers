package com.petpals.caregivers.persistence.entities;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaregiversRepository implements PanacheRepository<Caregivers> {
}
