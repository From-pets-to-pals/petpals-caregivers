package com.petpals.caregivers.application.mappers;

import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "cdi")
public interface CreateCaregiverMapper {
    @Mappings(
          value =  {
                    @Mapping(target = "reference", ignore = true),
                    @Mapping(target = "subscribed", ignore = true),
            }
    )
    CreateCaregiverCommand toDomain(CreateCaregiver caregiver);
}
