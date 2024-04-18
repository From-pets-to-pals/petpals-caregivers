package com.petpals.caregivers.application.mappers;

import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface CreateCaregiverMapper {
    CreateCaregiverCommand toDomain(CreateCaregiver caregiver);
}
