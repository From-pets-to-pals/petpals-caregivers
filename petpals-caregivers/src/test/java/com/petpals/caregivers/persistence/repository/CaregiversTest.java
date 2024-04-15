package com.petpals.caregivers.persistence.repository;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.petpals.caregivers.persistence.entities.CaregiverTypes;
import com.petpals.caregivers.persistence.entities.Caregivers;
import com.petpals.caregivers.persistence.entities.CaregiversRepository;
import com.petpals.caregivers.persistence.entities.services.CaregiversPersistence;
import com.petpals.shared.entities.Species;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.transaction.annotations.Rollback;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

@QuarkusTest
public class CaregiversTest {

    @InjectMock
    CaregiversRepository caregiversRepositoryMock;
    @Inject
    CaregiversPersistence caregiversPersistence;

    public static Caregivers caregiver;

    ArgumentCaptor<Caregivers> caregiversArgumentCaptor = ArgumentCaptor.forClass(Caregivers.class);
    @BeforeAll
    public static void setup() {
        String[] workingDays = new String[] {
                "Monday", "Tuesday", "Wednesday", "Thursday"
        };
        String[] palsHandled = new String[] {
                Species.DOG.name(),  Species.CAT.name()
        };
        var toUse = new Caregivers(
                UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""),
                "Sid",
                "Bennaceur",
                "sa.bennaceur@gmaill.com",
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
        caregiver = toUse;
    }

    @Test
    @TestTransaction
    void shouldAddCaregiverToDb() {
        Mockito.doCallRealMethod().when(caregiversRepositoryMock).persist(caregiver);
        caregiversPersistence.saveCaregiver(caregiver);
        Mockito.verify(caregiversRepositoryMock).persist(caregiversArgumentCaptor.capture());
        Assertions.assertNotNull(caregiversArgumentCaptor.getValue().getCaregiverId());
        Assertions.assertEquals(caregiversArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }
}
