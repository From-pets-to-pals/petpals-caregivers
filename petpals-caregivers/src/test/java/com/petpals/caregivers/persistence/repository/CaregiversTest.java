package com.petpals.caregivers.persistence.repository;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.persistence.entities.*;
import com.petpals.caregivers.persistence.repositories.CountriesRepository;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import com.petpals.caregivers.persistence.repositories.TrainersRepository;
import com.petpals.caregivers.persistence.repositories.VetsRepository;
import com.petpals.caregivers.persistence.services.CaregiversPersistencePort;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.Mock;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;

@QuarkusTest

class CaregiversTest {

    @InjectMock
    GroomersRepository groomersRepositoryMock;
    @InjectMock
    VetsRepository vetsRepositoryMock;
    @InjectMock
    TrainersRepository trainersRepositoryMock;
    @InjectMock
    CountriesRepository countriesRepository;
    @Inject
    CaregiversPersistencePort caregiversPersistencePort;


    ArgumentCaptor<Groomers> groomersArgumentCaptor = ArgumentCaptor.forClass(Groomers.class);
    ArgumentCaptor<Vets> vetsArgumentCaptor = ArgumentCaptor.forClass(Vets.class);
    ArgumentCaptor<Trainers> trainersArgumentCaptor = ArgumentCaptor.forClass(Trainers.class);
    String[] workingDays = new String[] {
            Days.MONDAY.name(), Days.TUESDAY.name(), Days.THURSDAY.name(), Days.SATURDAY.name()
    };
    String[] palsHandled = new String[] {
            SpeciesEnum.DOG.name(),  SpeciesEnum.CAT.name()
    };

    @Test
    @TestTransaction
    void shouldAddGroomerToDb() {
        var caregiver = new Groomers();
        caregiver.setReference(UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""));
        caregiver.setFirstName("Sid");
        caregiver.setLastName("Bennaceur");
        caregiver.setEmail("sa.bennaceur@test-db.com");
        caregiver.setPhoneNumber("0764017528");
        caregiver.setAddress( "101, rue des Acquevilles");
        caregiver.setCity("Suresnes");
        caregiver.setZipCode( "92150");
        var country = new Countries();
        country.setName("France");
        caregiver.setCountry(country);
        caregiver.setWorkingDays(workingDays);
        caregiver.setPalsHandled(palsHandled);
        caregiver.setHomeService(true);
        caregiver.setAppointmentDuration(0.25);
        caregiver.setPriceRating(0.0);
        caregiver.setServiceRating(0.0);
        caregiver.setSubscribed(false);
        caregiver.setClients(new ArrayList<>());
        Mockito.doCallRealMethod().when(groomersRepositoryMock).persistAndFlush(Mockito.any(Groomers
                .class));
        Mockito.when(countriesRepository.findIdByName(country.getName())).thenReturn((short)2);
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
        var caregiver = new Vets();
        caregiver.setReference(UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""));
        caregiver.setFirstName("Sid");
        caregiver.setLastName("Bennaceur");
        caregiver.setEmail("sa.bennaceur@test-db.com");
        caregiver.setPhoneNumber("0764017528");
        caregiver.setAddress( "101, rue des Acquevilles");
        caregiver.setCity("Suresnes");
        caregiver.setZipCode( "92150");
        var country = new Countries();
        country.setId((short)2);
        caregiver.setCountry(country);
        caregiver.setWorkingDays(workingDays);
        caregiver.setPalsHandled(palsHandled);
        caregiver.setHomeService(true);
        caregiver.setAppointmentDuration(0.25);
        caregiver.setPriceRating(0.0);
        caregiver.setServiceRating(0.0);
        caregiver.setSubscribed(false);
        caregiver.setClients(new ArrayList<>());
        Mockito.doCallRealMethod().when(vetsRepositoryMock).persistAndFlush(Mockito.any(Vets
                .class));
        Mockito.when(countriesRepository.findIdByName(country.getName())).thenReturn((short)2);

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
        var caregiver = new Trainers();
        caregiver.setReference(UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""));
        caregiver.setFirstName("Sid");
        caregiver.setLastName("Bennaceur");
        caregiver.setEmail("sa.bennaceur@test-db.com");
        caregiver.setPhoneNumber("0764017528");
        caregiver.setAddress( "101, rue des Acquevilles");
        caregiver.setCity("Suresnes");
        caregiver.setZipCode( "92150");
        var country = new Countries();
        country.setId((short)2);
        caregiver.setCountry(country);
        caregiver.setWorkingDays(workingDays);
        caregiver.setPalsHandled(palsHandled);
        caregiver.setHomeService(true);
        caregiver.setAppointmentDuration(0.25);
        caregiver.setPriceRating(0.0);
        caregiver.setServiceRating(0.0);
        caregiver.setSubscribed(false);
        caregiver.setClients(new ArrayList<>());
        Mockito.doCallRealMethod().when(trainersRepositoryMock).persistAndFlush(Mockito.any(Trainers
                .class));
        Mockito.when(countriesRepository.findIdByName(country.getName())).thenReturn((short)2);
        caregiversPersistencePort.addTrainer( caregiver);
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
        var caregiver = new Groomers();
        caregiver.setReference(UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""));
        caregiver.setFirstName("Sid");
        caregiver.setLastName("Bennaceur");
        caregiver.setEmail("sa.bennaceur@test-db.com");
        caregiver.setPhoneNumber("0764017528");
        caregiver.setAddress( "101, rue des Acquevilles");
        caregiver.setCity("Suresnes");
        caregiver.setZipCode( "92150");
        var country = new Countries();
        country.setId((short)2);
        caregiver.setCountry(country);
        caregiver.setWorkingDays(workingDays);
        caregiver.setPalsHandled(palsHandled);
        caregiver.setHomeService(true);
        caregiver.setAppointmentDuration(0.25);
        caregiver.setPriceRating(0.0);
        caregiver.setServiceRating(0.0);
        caregiver.setSubscribed(false);
        caregiver.setClients(new ArrayList<>());
        //Groomers
        Mockito.when(countriesRepository.findIdByName(country.getName())).thenReturn((short)2);
        Mockito.doThrow(ConstraintViolationException.class).when(groomersRepositoryMock).persistAndFlush(Mockito.any(Groomers.class));
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversPersistencePort.addGroomer(caregiver));
        Mockito.verify(groomersRepositoryMock).persistAndFlush(groomersArgumentCaptor.capture());
        Assertions.assertEquals(groomersArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }

    @Test
    @TestTransaction
    void shouldThrowConstraintViolationExceptionWhenAddVetsToDb() {
        var caregiver = new Vets();
        caregiver.setReference(UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""));
        caregiver.setFirstName("Sid");
        caregiver.setLastName("Bennaceur");
        caregiver.setEmail("sa.bennaceur@test-db.com");
        caregiver.setPhoneNumber("0764017528");
        caregiver.setAddress( "101, rue des Acquevilles");
        caregiver.setCity("Suresnes");
        caregiver.setZipCode( "92150");
        var country = new Countries();
        country.setId((short)2);
        caregiver.setCountry(country);
        caregiver.setWorkingDays(workingDays);
        caregiver.setPalsHandled(palsHandled);
        caregiver.setHomeService(true);
        caregiver.setAppointmentDuration(0.25);
        caregiver.setPriceRating(0.0);
        caregiver.setServiceRating(0.0);
        caregiver.setSubscribed(false);
        caregiver.setClients(new ArrayList<>());
        Mockito.when(countriesRepository.findIdByName(country.getName())).thenReturn((short)2);
        Mockito.doThrow(ConstraintViolationException.class).when(vetsRepositoryMock).persistAndFlush(Mockito.any(Vets.class));
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversPersistencePort.addVet( caregiver));
        Mockito.verify(vetsRepositoryMock).persistAndFlush(vetsArgumentCaptor.capture());
        Assertions.assertEquals(vetsArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }

    @Test
    @TestTransaction
    void shouldThrowConstraintViolationExceptionWhenAddTrainerToDb() {
        var caregiver = new Trainers();
        caregiver.setReference(UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,""));
        caregiver.setFirstName("Sid");
        caregiver.setLastName("Bennaceur");
        caregiver.setEmail("sa.bennaceur@test-db.com");
        caregiver.setPhoneNumber("0764017528");
        caregiver.setAddress( "101, rue des Acquevilles");
        caregiver.setCity("Suresnes");
        caregiver.setZipCode( "92150");
        var country = new Countries();
        country.setId((short)2);
        caregiver.setCountry(country);
        caregiver.setWorkingDays(workingDays);
        caregiver.setPalsHandled(palsHandled);
        caregiver.setHomeService(true);
        caregiver.setAppointmentDuration(0.25);
        caregiver.setPriceRating(0.0);
        caregiver.setServiceRating(0.0);
        caregiver.setSubscribed(false);
        caregiver.setClients(new ArrayList<>());
        Mockito.when(countriesRepository.findIdByName(country.getName())).thenReturn((short)2);
        Mockito.doThrow(ConstraintViolationException.class).when(trainersRepositoryMock).persistAndFlush(Mockito.any(Trainers.class));
        Assertions.assertThrows(PetPalsExceptions.class, () -> caregiversPersistencePort.addTrainer(caregiver));
        Mockito.verify(trainersRepositoryMock).persistAndFlush(trainersArgumentCaptor.capture());
        Assertions.assertEquals(trainersArgumentCaptor.getValue().getFirstName(), caregiver.getFirstName());
    }
}
