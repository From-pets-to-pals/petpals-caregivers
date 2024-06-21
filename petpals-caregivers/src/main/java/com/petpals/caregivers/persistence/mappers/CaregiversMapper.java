package com.petpals.caregivers.persistence.mappers;

import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.caregivers.persistence.entities.Vets;
import com.petpals.shared.utils.EnumMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = EnumMapper.class, componentModel = "cdi")

public interface CaregiversMapper {
    @Mappings(
            value =  {
                    @Mapping(target = "caregiverId", ignore = true),
                    @Mapping(target = "clients", ignore = true),
            }
    )
    Groomers toGroomer(CreateCaregiverCommand caregiver);
    @Mappings(
            value =  {
                    @Mapping(target = "caregiverId", ignore = true),
                    @Mapping(target = "clients", ignore = true),
            }
    )
    Vets toVet(CreateCaregiverCommand caregiver);
    @Mappings(
            value =  {
                    @Mapping(target = "caregiverId", ignore = true),
                    @Mapping(target = "clients", ignore = true),
            }
    )
    Trainers toTrainer(CreateCaregiverCommand caregiver);

}
