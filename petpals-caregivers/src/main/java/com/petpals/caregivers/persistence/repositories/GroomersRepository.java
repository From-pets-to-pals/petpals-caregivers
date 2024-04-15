package com.petpals.caregivers.persistence.repositories;

import com.petpals.caregivers.persistence.entities.Groomers;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GroomersRepository implements PanacheRepository<Groomers> {
}
