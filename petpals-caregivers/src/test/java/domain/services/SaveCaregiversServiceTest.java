package domain.services;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import com.petpals.caregivers.domain.ports.in.CaregiversServicePort;
import com.petpals.caregivers.domain.ports.out.CaregiversPersistencePort;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import com.petpals.shared.errorhandling.ApplicationExceptions;
import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Set;

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
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getPriceRating(),5.0);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getServiceRating(),5.0);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getCaregiverType(),CaregiverTypes.GROOMER);

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
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getPriceRating(),5.0);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getServiceRating(),5.0);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getCaregiverType(),CaregiverTypes.TRAINER);
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
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getPriceRating(),5.0);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getServiceRating(),5.0);
        Assertions.assertEquals(createCaregiverCommandArgumentCaptor.getValue().getCaregiverType(),CaregiverTypes.VET);
    }

    @Test
    @TestTransaction
    void shouldThrowException() {
        createCaregiverCommand.setCaregiverType(null);
        Assertions.assertThrows(ApplicationExceptions.class, () -> caregiversServicePort.addCaregiver(createCaregiverCommand));
    }
}
