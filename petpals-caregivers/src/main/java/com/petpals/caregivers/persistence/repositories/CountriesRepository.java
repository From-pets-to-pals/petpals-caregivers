package com.petpals.caregivers.persistence.repositories;

import com.petpals.caregivers.persistence.entities.Countries;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CountriesRepository implements PanacheRepository<Countries> {
    public Short findIdByName(String name) {
        return find("name", name).firstResult().getId();
    }
}
