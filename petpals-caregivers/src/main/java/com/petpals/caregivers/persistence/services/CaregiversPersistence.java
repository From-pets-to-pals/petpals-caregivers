package com.petpals.caregivers.persistence.services;

import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import com.petpals.caregivers.domain.ports.out.CaregiversPersistencePort;
import com.petpals.caregivers.persistence.mappers.CaregiversMapper;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import com.petpals.caregivers.persistence.repositories.TrainersRepository;
import com.petpals.caregivers.persistence.repositories.VetsRepository;
import com.petpals.shared.errorhandling.ExceptionsEnum;
import com.petpals.shared.errorhandling.PetPalsExceptions;
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
    private final CaregiversMapper caregiversMapper;

    public CaregiversPersistence(GroomersRepository groomersRepository, VetsRepository vetsRepository, TrainersRepository trainersRepository, CaregiversMapper caregiversMapper) {
        this.groomersRepository = groomersRepository;
        this.vetsRepository = vetsRepository;
        this.trainersRepository = trainersRepository;
        this.caregiversMapper = caregiversMapper;
    }

    @Transactional(rollbackOn = {PetPalsExceptions.class}, value = Transactional.TxType.REQUIRED)
    public void addGroomer(CreateCaregiverCommand caregiver) {
        var mappedEntity = caregiversMapper.toGroomer(caregiver);
        try {
            groomersRepository.persistAndFlush(mappedEntity);
            LOG.info("Groomer added : "+ mappedEntity);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.info(exc.getConstraintName());
                LOG.info(exc.getErrorMessage());

            }
            throw new PetPalsExceptions(ExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
    }

    @Transactional(rollbackOn = {PetPalsExceptions.class})
    public void addVet(CreateCaregiverCommand caregiver) {
        var mappedEntity = caregiversMapper.toVet(caregiver);
        try {
            vetsRepository.persistAndFlush(mappedEntity);
            LOG.info("Vet added : "+ mappedEntity);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new PetPalsExceptions(ExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
    }

    @Transactional(rollbackOn = {PetPalsExceptions.class})
    public void addTrainer(CreateCaregiverCommand caregiver) {
        var mappedEntity = caregiversMapper.toTrainer(caregiver);
        try {
            trainersRepository.persistAndFlush(mappedEntity);
            LOG.info("Trainer added : "+ mappedEntity);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new PetPalsExceptions(ExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
    }

}
