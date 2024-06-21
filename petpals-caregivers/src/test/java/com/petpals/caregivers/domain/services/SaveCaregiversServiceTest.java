package com.petpals.caregivers.domain.services;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
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
                new Species[]{},
                false,
                0.5,
                5.0,
                5.0,
                false,
                CaregiverTypes.GROOMER
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
        Assertions.assertEquals(CaregiverTypes.GROOMER, createCaregiverCommandArgumentCaptor.getValue().getCaregiverType());

    }
    @Test
    @TestTransaction
    void shouldAddTrainerToDb() {
        createCaregiverCommand.setCaregiverType(CaregiverTypes.TRAINER);
        createCaregiverCommand.setReference(null);
        Mockito.doNothing().when(caregiversPersistencePort).addTrainer(Mockito.any(CreateCaregiverCommand.class));
        String uuid = caregiversServicePort.addCaregiver(createCaregiverCommand);
        Mockito.verify(caregiversPersistencePort).addTrainer(createCaregiverCommandArgumentCaptor.capture());
        Assertions.assertNotNull(uuid);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getReference(),uuid);
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getPriceRating());
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getServiceRating());
        Assertions.assertEquals(CaregiverTypes.TRAINER, createCaregiverCommandArgumentCaptor.getValue().getCaregiverType());
    }

    @Test
    @TestTransaction
    void shouldAddVetToDb() {
        createCaregiverCommand.setCaregiverType(CaregiverTypes.VET);
        createCaregiverCommand.setReference(null);
        Mockito.doNothing().when(caregiversPersistencePort).addTrainer(Mockito.any(CreateCaregiverCommand.class));
        String uuid = caregiversServicePort.addCaregiver(createCaregiverCommand);
        Mockito.verify(caregiversPersistencePort).addVet(createCaregiverCommandArgumentCaptor.capture());
        Assertions.assertNotNull(uuid);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getReference(),uuid);
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getPriceRating());
        Assertions.assertEquals(5.0, createCaregiverCommandArgumentCaptor.getValue().getServiceRating());
        Assertions.assertEquals(CaregiverTypes.VET, createCaregiverCommandArgumentCaptor.getValue().getCaregiverType());
    }

    @Test
    @TestTransaction
    void shouldThrowException() {
        createCaregiverCommand.setCaregiverType(null);
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversServicePort.addCaregiver(createCaregiverCommand));
    }
}
