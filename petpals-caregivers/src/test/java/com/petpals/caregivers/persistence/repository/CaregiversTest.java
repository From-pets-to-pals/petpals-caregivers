package com.petpals.caregivers.persistence.repository;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.domain.pojo.Caregivers;
import com.petpals.caregivers.domain.ports.out.CaregiversPersistencePort;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.errorhandling.DBPersistenceException;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import com.petpals.shared.entities.CaregiverTypes;
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

import java.util.ArrayList;

@QuarkusTest

class CaregiversTest {

    @InjectMock
    GroomersRepository groomersRepositoryMock;
    @Inject
    CaregiversPersistencePort caregiversPersistencePort;

    public static Caregivers caregiver;

    ArgumentCaptor<Groomers> caregiversArgumentCaptor = ArgumentCaptor.forClass(Groomers.class);

    @BeforeAll
    public static void setup() {
        Days[] workingDays = new Days[] {
                Days.MONDAY, Days.TUESDAY, Days.THURSDAY, Days.SATURDAY
        };
        Species[] palsHandled = new Species[] {
                Species.DOG,  Species.CAT
        };
        caregiver = new Caregivers(
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
                0.0,
                true,
                new ArrayList<>(),
                CaregiverTypes.GROOMER
        );
    }

    @Test
    @TestTransaction
    void shouldAddGroomerToDb() {
        Mockito.doCallRealMethod().when(groomersRepositoryMock).persistAndFlush(Mockito.any(Groomers
                .class));
        caregiversPersistencePort.addGroomer(caregiver);
        Mockito.verify(groomersRepositoryMock).persistAndFlush(caregiversArgumentCaptor.capture());
        Mockito.doCallRealMethod().when(groomersRepositoryMock).find("email","sa.bennaceur@test-db.com");
        Groomers caregiverFromDb = groomersRepositoryMock.find("email","sa.bennaceur@test-db.com").firstResult();
        Assertions.assertNotNull(caregiverFromDb);
        Assertions.assertEquals(caregiversArgumentCaptor.getValue().getEmail(), caregiver.getEmail());
        Assertions.assertEquals(caregiversArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }

    @Test
    @TestTransaction
    void shouldThrowConstraintViolationExceptionWhenAddCaregiverToDb() {
        Mockito.doThrow(ConstraintViolationException.class).when(groomersRepositoryMock).persistAndFlush(Mockito.any(Groomers.class));
        Assertions.assertThrows(DBPersistenceException.class, () -> caregiversPersistencePort.addGroomer(caregiver));
        Mockito.verify(groomersRepositoryMock).persistAndFlush(caregiversArgumentCaptor.capture());
        Assertions.assertEquals(caregiversArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }
}
