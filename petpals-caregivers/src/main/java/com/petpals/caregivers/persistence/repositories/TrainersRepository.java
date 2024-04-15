package com.petpals.caregivers.persistence.repositories;


import com.petpals.caregivers.persistence.entities.Trainers;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TrainersRepository implements PanacheRepository<Trainers> {
}
