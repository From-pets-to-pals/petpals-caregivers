package com.petpals.caregivers.domain.services;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import com.petpals.caregivers.domain.ports.in.CaregiversServicePort;
import com.petpals.caregivers.domain.ports.out.CaregiversPersistencePort;
import com.petpals.shared.model.enums.UserTypes;
import com.petpals.shared.model.dto.Specie;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

@QuarkusTest
class SaveCaregiversServiceTest {
    @InjectMock
    CaregiversPersistencePort caregiversPersistencePort;

    ArgumentCaptor<CreateCaregiverCommand> createCaregiverCommandArgumentCaptor = ArgumentCaptor.forClass(CreateCaregiverCommand.class);

    @Inject
    CaregiversServicePort caregiversServicePort;

    private static CreateCaregiverCommand createCaregiverCommand;
    @BeforeAll
    static void setCreateCaregiverCommand()  {
        createCaregiverCommand = new CreateCaregiverCommand(
                null,
                "Sid",
                "Bennaceur",
                "sa.bennaceur@test-db-test.com",
                "0764017528",
                "101, rue des Acquevilles",
                "Suresnes",
                "92150",
                "France",
                new Days[]{},
                new Specie[]{},
                false,
                0.5,
                5.0,
                5.0,
                false,
                UserTypes.GROOMER
        );
    }
    @Test
    @TestTransaction
    void shouldAddGroomerToDb() {
        Mockito.doNothing().when(caregiversPersistencePort).addGroomer(Mockito.any(CreateCaregiverCommand.class));
        String uuid = caregiversServicePort.addCaregiver(createCaregiverCommand);
        Mockito.verify(caregiversPersistencePort).addGroomer(createCaregiverCommandArgumentCaptor.capture());
        Assertions.assertNotNull(uuid);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getReference(),uuid);
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getPriceRating());
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getServiceRating());
        Assertions.assertEquals(UserTypes.GROOMER, createCaregiverCommandArgumentCaptor.getValue().getUserType());

    }
    @Test
    @TestTransaction
    void shouldAddTrainerToDb() {
        createCaregiverCommand.setCaregiverType(UserTypes.TRAINER);
        createCaregiverCommand.setReference(null);
        Mockito.doNothing().when(caregiversPersistencePort).addTrainer(Mockito.any(CreateCaregiverCommand.class));
        String uuid = caregiversServicePort.addCaregiver(createCaregiverCommand);
        Mockito.verify(caregiversPersistencePort).addTrainer(createCaregiverCommandArgumentCaptor.capture());
        Assertions.assertNotNull(uuid);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getReference(),uuid);
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getPriceRating());
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getServiceRating());
        Assertions.assertEquals(UserTypes.TRAINER, createCaregiverCommandArgumentCaptor.getValue().getUserType());
    }

    @Test
    @TestTransaction
    void shouldAddVetToDb() {
        createCaregiverCommand.setCaregiverType(UserTypes.VET);
        createCaregiverCommand.setReference(null);
        Mockito.doNothing().when(caregiversPersistencePort).addTrainer(Mockito.any(CreateCaregiverCommand.class));
        String uuid = caregiversServicePort.addCaregiver(createCaregiverCommand);
        Mockito.verify(caregiversPersistencePort).addVet(createCaregiverCommandArgumentCaptor.capture());
        Assertions.assertNotNull(uuid);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getReference(),uuid);
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getPriceRating());
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getServiceRating());
        Assertions.assertEquals(UserTypes.VET, createCaregiverCommandArgumentCaptor.getValue().getUserType());
    }

    @Test
    @TestTransaction
    void shouldThrowException() {
        createCaregiverCommand.setCaregiverType(null);
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversServicePort.addCaregiver(createCaregiverCommand));
    }
}
