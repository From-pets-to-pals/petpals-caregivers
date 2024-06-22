package com.petpals.caregivers.application.mappers;

import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.caregivers.persistence.entities.Vets;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface CaregiversMapper {
    @Mappings(
            value =  {
                    @Mapping(target = "caregiverId", ignore = true),
                    @Mapping(target = "clients", ignore = true),
                    @Mapping(source = "country", target = "country.name")
            }
    )
    Groomers toGroomer(CreateCaregiver caregiver);
    @Mappings(
            value =  {
                    @Mapping(target = "caregiverId", ignore = true),
                    @Mapping(target = "clients", ignore = true),
                    @Mapping(source = "country", target = "country.name")

            }
    )
    Vets toVet(CreateCaregiver caregiver);
    @Mappings(
            value =  {
                    @Mapping(target = "caregiverId", ignore = true),
                    @Mapping(target = "clients", ignore = true),
                    @Mapping(source = "country", target = "country.name")
            }
    )
    Trainers toTrainer(CreateCaregiver caregiver);

}
