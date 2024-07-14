package com.petpals.caregivers.persistence.services;

import com.petpals.caregivers.persistence.entities.Caregivers;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.entities.Trainers;
import com.petpals.caregivers.persistence.entities.Vets;
import com.petpals.caregivers.persistence.repositories.CountriesRepository;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import com.petpals.caregivers.persistence.repositories.TrainersRepository;
import com.petpals.caregivers.persistence.repositories.VetsRepository;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
import com.petpals.shared.utils.PasswordUtils;
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
    private final CountriesRepository countriesRepository;


    public CaregiversPersistence(GroomersRepository groomersRepository, VetsRepository vetsRepository, TrainersRepository trainersRepository, CountriesRepository countriesRepository) {
        this.groomersRepository = groomersRepository;
        this.vetsRepository = vetsRepository;
        this.trainersRepository = trainersRepository;
        this.countriesRepository = countriesRepository;
    }

    private void hashPasswordAndSalt(Caregivers caregivers) {
        caregivers.setSalt(PasswordUtils.generateSalt());
        caregivers.setPassword(
                PasswordUtils.hashPassword(caregivers.getPassword(), caregivers.getSalt())
        );
    }

    @Transactional(rollbackOn = {PetPalsExceptions.class}, value = Transactional.TxType.REQUIRED)
    public void addGroomer(Groomers caregiver) {
        try {
            hashPasswordAndSalt(caregiver);
            groomersRepository.persistAndFlush(caregiver);
            LOG.info("Groomer added : "+ caregiver);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.info(exc.getConstraintName());
                LOG.info(exc.getErrorMessage());

            }
            throw new PetPalsExceptions(ExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
    }

    @Transactional(rollbackOn = {PetPalsExceptions.class})
    public void addVet(Vets caregiver) {
        try {
            hashPasswordAndSalt(caregiver);
            vetsRepository.persistAndFlush(caregiver);
            LOG.info("Vet added : "+ caregiver);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new PetPalsExceptions(ExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
    }

    @Transactional(rollbackOn = {PetPalsExceptions.class})
    public void addTrainer(Trainers caregiver) {
        try {
            hashPasswordAndSalt(caregiver);
            trainersRepository.persistAndFlush(caregiver);
            LOG.info("Trainer added : "+ caregiver);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new PetPalsExceptions(ExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
    }


}
