package com.petpals.caregivers.application.dto;

import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;

class CreateCaregiverTest {


    private CreateCaregiver createCaregiver(){
        return new CreateCaregiver(
                "24352352335325",
                "Sid",
                "Bennaceur",
                "sa.bennaceur@test-db-test.com",
                "Aa123456",
                "0764017528",
                "101, rue des Acquevilles",
                "Suresnes",
                "92150",
                "France",
                new String[0],
                new String[0],
                false,
                0.5,
                "GROOMER",
                false,
                0.0,
                0.0
        );
    }
}
