package com.petpals.caregivers.persistence.repositories;

import com.petpals.caregivers.persistence.entities.Vets;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VetsRepository implements PanacheRepository<Vets> {

}
