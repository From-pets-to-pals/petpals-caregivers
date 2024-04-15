package com.petpals.caregivers.persistence.repository;

import com.petpals.caregivers.persistence.entities.Caregivers;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import com.petpals.caregivers.persistence.services.CaregiversPersistence;
import com.petpals.shared.entities.Species;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import io.quarkus.test.InjectMock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

@QuarkusTest
public class CaregiversTest {

    @InjectMock
    GroomersRepository groomersRepositoryMock;
    @Inject
    CaregiversPersistence caregiversPersistence;

    public static Groomers caregiver;

    ArgumentCaptor<Groomers> caregiversArgumentCaptor = ArgumentCaptor.forClass(Groomers.class);

    @BeforeAll
    public static void setup() {
        String[] workingDays = new String[] {
                "Monday", "Tuesday", "Wednesday", "Thursday"
        };
        String[] palsHandled = new String[] {
                Species.DOG.name(),  Species.CAT.name()
        };
        caregiver = new Groomers(
                UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""),
                "Sid",
                "Bennaceur",
                "sa.bennaceur@test-db.com",
                "0764017528",
                "101, rue des Acquevilles",
                "Suresnes",
                "92150",
                "France",
                workingDays,
                palsHandled,
                false,
                0.25,
                0.0,
                0.0
        );
    }

    @Test
    @TestTransaction
    void shouldAddCaregiverToDb() {
        Mockito.doCallRealMethod().when(groomersRepositoryMock).persistAndFlush(caregiver);
        caregiversPersistence.saveGroomer(caregiver);
        Mockito.verify(groomersRepositoryMock).persistAndFlush(caregiversArgumentCaptor.capture());
        Mockito.doCallRealMethod().when(groomersRepositoryMock).find("email","sa.bennaceur@test-db.com");
        Caregivers caregiverFromDb = groomersRepositoryMock.find("email","sa.bennaceur@test-db.com").firstResult();
        Assertions.assertNotNull(caregiverFromDb);
        Assertions.assertEquals(caregiversArgumentCaptor.getValue().getEmail(), caregiver.getEmail());
        Assertions.assertEquals(caregiversArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }



    @Test
    @TestTransaction
    void shouldThrowConstraintViolationExceptionWhenAddCaregiverToDb() {
        Mockito.doThrow(ConstraintViolationException.class).when(groomersRepositoryMock).persistAndFlush(caregiver);
        Assertions.assertThrows(RuntimeException.class, () -> caregiversPersistence.saveGroomer(caregiver));
        Mockito.verify(groomersRepositoryMock).persistAndFlush(caregiversArgumentCaptor.capture());
        Assertions.assertEquals(caregiversArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }
}
