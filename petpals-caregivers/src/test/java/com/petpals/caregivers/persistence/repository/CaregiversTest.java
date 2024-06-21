package com.petpals.caregivers.persistence.repository;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.caregivers.persistence.entities.Vets;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import com.petpals.caregivers.persistence.repositories.TrainersRepository;
import com.petpals.caregivers.persistence.repositories.VetsRepository;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import com.petpals.shared.errorhandling.PetPalsExceptions;
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

class CaregiversTest {

    @InjectMock
    GroomersRepository groomersRepositoryMock;
    @InjectMock
    VetsRepository vetsRepositoryMock;
    @InjectMock
    TrainersRepository trainersRepositoryMock;
    @Inject
    CaregiversPersistencePort caregiversPersistencePort;

    public static CreateCaregiverCommand caregiver;

    ArgumentCaptor<Groomers> groomersArgumentCaptor = ArgumentCaptor.forClass(Groomers.class);
    ArgumentCaptor<Vets> vetsArgumentCaptor = ArgumentCaptor.forClass(Vets.class);
    ArgumentCaptor<Trainers> trainersArgumentCaptor = ArgumentCaptor.forClass(Trainers.class);

    @BeforeAll
    public static void setup() {
        Days[] workingDays = new Days[] {
                Days.MONDAY, Days.TUESDAY, Days.THURSDAY, Days.SATURDAY
        };
        Species[] palsHandled = new Species[] {
                Species.DOG,  Species.CAT
        };
        caregiver = new CreateCaregiverCommand(
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
                CaregiverTypes.GROOMER
        );
    }

    @Test
    @TestTransaction
    void shouldAddGroomerToDb() {
        Mockito.doCallRealMethod().when(groomersRepositoryMock).persistAndFlush(Mockito.any(Groomers
                .class));
        caregiversPersistencePort.addGroomer(caregiver);
        Mockito.verify(groomersRepositoryMock).persistAndFlush(groomersArgumentCaptor.capture());
        Groomers toReturn = groomersArgumentCaptor.getValue();
        Mockito.when(groomersRepositoryMock.findOne("email","sa.bennaceur@test-db-groomer.com")).thenReturn(toReturn);
        Groomers caregiverFromDb = groomersRepositoryMock.findOne("email","sa.bennaceur@test-db-groomer.com");
        Mockito.verify(groomersRepositoryMock).findOne("email","sa.bennaceur@test-db-groomer.com");
        Assertions.assertNotNull(caregiverFromDb);
        Assertions.assertEquals(groomersArgumentCaptor.getValue().getEmail(), caregiver.getEmail());
        Assertions.assertEquals(groomersArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }


    @Test
    @TestTransaction
    void shouldAddVetToDb() {
        Mockito.doCallRealMethod().when(vetsRepositoryMock).persistAndFlush(Mockito.any(Vets
                .class));
        caregiversPersistencePort.addVet(caregiver);
        Mockito.verify(vetsRepositoryMock).persistAndFlush(vetsArgumentCaptor.capture());
        Vets toReturn = vetsArgumentCaptor.getValue();
        Mockito.when(vetsRepositoryMock.findOne("email","sa.bennaceur@test-db-vet.com")).thenReturn(toReturn);
        Vets caregiverFromDb = vetsRepositoryMock.findOne("email","sa.bennaceur@test-db-vet.com");
        Mockito.verify(vetsRepositoryMock).findOne("email","sa.bennaceur@test-db-vet.com");
        Assertions.assertNotNull(caregiverFromDb);
        Assertions.assertEquals(vetsArgumentCaptor.getValue().getEmail(), caregiver.getEmail());
        Assertions.assertEquals(vetsArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }

    @Test
    @TestTransaction
    void shouldAddTrainerToDb() {

        caregiver.setCaregiverType(CaregiverTypes.TRAINER);
        Mockito.doCallRealMethod().when(trainersRepositoryMock).persistAndFlush(Mockito.any(Trainers
                .class));
        caregiversPersistencePort.addTrainer(caregiver);
        Mockito.verify(trainersRepositoryMock).persistAndFlush(trainersArgumentCaptor.capture());
        Trainers toReturn = trainersArgumentCaptor.getValue();
        Mockito.when(trainersRepositoryMock.findOne("email","sa.bennaceur@test-db-trainer.com")).thenReturn(toReturn);
        Trainers caregiverFromDb = trainersRepositoryMock.findOne("email","sa.bennaceur@test-db-trainer.com");
        Mockito.verify(trainersRepositoryMock).findOne("email","sa.bennaceur@test-db-trainer.com");
        Assertions.assertNotNull(caregiverFromDb);
        Assertions.assertEquals(trainersArgumentCaptor.getValue().getEmail(), caregiver.getEmail());
        Assertions.assertEquals(trainersArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }

    @Test
    @TestTransaction
    void shouldThrowConstraintViolationExceptionWhenAddCaregiverToDb() {
        //Groomers
        Mockito.doThrow(ConstraintViolationException.class).when(groomersRepositoryMock).persistAndFlush(Mockito.any(Groomers.class));
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversPersistencePort.addGroomer(caregiver));
        Mockito.verify(groomersRepositoryMock).persistAndFlush(groomersArgumentCaptor.capture());
        Assertions.assertEquals(groomersArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }

    @Test
    @TestTransaction
    void shouldThrowConstraintViolationExceptionWhenAddVetsToDb() {
        //Groomers
        Mockito.doThrow(ConstraintViolationException.class).when(vetsRepositoryMock).persistAndFlush(Mockito.any(Vets.class));
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversPersistencePort.addVet(caregiver));
        Mockito.verify(vetsRepositoryMock).persistAndFlush(vetsArgumentCaptor.capture());
        Assertions.assertEquals(vetsArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }

    @Test
    @TestTransaction
    void shouldThrowConstraintViolationExceptionWhenAddTrainerToDb() {
        //Groomers
        Mockito.doThrow(ConstraintViolationException.class).when(trainersRepositoryMock).persistAndFlush(Mockito.any(Trainers.class));
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversPersistencePort.addTrainer(caregiver));
        Mockito.verify(trainersRepositoryMock).persistAndFlush(trainersArgumentCaptor.capture());
        Assertions.assertEquals(trainersArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }
}
