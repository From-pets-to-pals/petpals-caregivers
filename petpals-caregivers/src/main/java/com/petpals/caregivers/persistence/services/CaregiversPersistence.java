package com.petpals.caregivers.persistence.services;

import com.petpals.caregivers.domain.pojo.Caregivers;
import com.petpals.caregivers.domain.ports.out.CaregiversPersistencePort;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.caregivers.persistence.entities.Vets;
import com.petpals.caregivers.persistence.errorhandling.DBExceptionsEnum;
import com.petpals.caregivers.persistence.errorhandling.DBPersistenceException;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import com.petpals.caregivers.persistence.repositories.TrainersRepository;
import com.petpals.caregivers.persistence.repositories.VetsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CaregiversPersistence implements CaregiversPersistencePort {

    private final Logger LOG = Logger.getLogger(CaregiversPersistence.class);

    private final GroomersRepository groomersRepository;

    private final VetsRepository vetsRepository;
    private final TrainersRepository trainersRepository;

    public CaregiversPersistence(GroomersRepository groomersRepository, VetsRepository vetsRepository, TrainersRepository trainersRepository) {
        this.groomersRepository = groomersRepository;
        this.vetsRepository = vetsRepository;
        this.trainersRepository = trainersRepository;
    }



    @Transactional(rollbackOn = {DBPersistenceException.class})
    public void addGroomer(Caregivers caregiver) {
        Groomers toSave = new Groomers();
        mapCaregiversFromDomain(caregiver,toSave);
        try {
            groomersRepository.persistAndFlush(toSave);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new DBPersistenceException(DBExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
        LOG.info("Groomer added : "+ toSave);

    }

    @Transactional(rollbackOn = {DBPersistenceException.class})
    public void addVet(Caregivers caregiver) {
        Vets toSave = new Vets();
        mapCaregiversFromDomain(caregiver,toSave);
        try {
            vetsRepository.persistAndFlush(toSave);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new DBPersistenceException(DBExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
        LOG.info("Vet added : "+ toSave);

    }

    @Transactional(rollbackOn = {DBPersistenceException.class})
    public void addTrainer(Caregivers caregiver) {
        Trainers toSave = new Trainers();
        mapCaregiversFromDomain(caregiver,toSave);
        try {
            trainersRepository.persistAndFlush(toSave);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new DBPersistenceException(DBExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
        LOG.info("Trainer added : "+ toSave);
    }

    private void mapCaregiversFromDomain(Caregivers from, com.petpals.caregivers.persistence.entities.Caregivers to) {
        to.setCaregiverId(null);
        to.setFirstName(from.getFirstName());
        to.setLastName(from.getLastName());
        to.setPhoneNumber(from.getPhoneNumber());
        to.setAddress(from.getAddress());
        to.setCity(from.getCity());
        to.setZipCode(from.getZipCode());
        to.setCountry(from.getCountry());
        to.setClients(from.getClients());
        to.setEmail(from.getEmail());
        to.setHomeService(from.isHomeService());
        to.setReference(from.getReference());
        to.setAppointmentDuration(from.getAppointmentDuration());
        to.setWorkingDays(from.getWorkingDays());
        to.setPalsHandled(from.getPalsHandled());
        to.setClients(from.getClients());
        to.setPriceRating(from.getPriceRating());
        to.setServiceRating(from.getServiceRating());
        to.setSubscribed(from.isSubscribed());
    }
}
