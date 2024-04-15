package com.petpals.caregivers.persistence.services;

import com.petpals.caregivers.persistence.errorhandling.DBExceptionsEnum;
import com.petpals.caregivers.persistence.errorhandling.DBPersistenceException;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.repositories.GroomersRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CaregiversPersistence {

    private final Logger LOG = Logger.getLogger(CaregiversPersistence.class);

    private final GroomersRepository caregiversRepository;

    public CaregiversPersistence(GroomersRepository caregiversRepository) {
        this.caregiversRepository = caregiversRepository;
    }

    @Transactional(rollbackOn = {DBPersistenceException.class})
    public void saveGroomer(Groomers caregiver) {
        try {
            LOG.info("flushing");
            caregiversRepository.persistAndFlush(caregiver);
        } catch (ConstraintViolationException exc){
            if(LOG.isInfoEnabled()){
                LOG.error(exc.toString());
            }
            throw new DBPersistenceException(DBExceptionsEnum.DB_UNIQUE_KEY_CAREGIVER_MAIL_CONSTRAINT_VIOLATION);
        }
    }
}
