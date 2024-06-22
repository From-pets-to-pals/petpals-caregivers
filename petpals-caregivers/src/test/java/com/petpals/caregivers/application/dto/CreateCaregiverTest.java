package com.petpals.caregivers.application.dto;

import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateCaregiverTest {
    @Test
     void shouldCheckObjectsIntegrity(){
        var reference = UUIDGenerator.generateUUID().toString();
        var createCaregiver = new CreateCaregiver(
                reference,
                "Sid",
                "Bennaceur",
                "sa.bennaceur@test-db-test.com",
                "0764017528",
                "101, rue des Acquevilles",
                "Suresnes",
                "92150",
                "France",
                new Days[]{},
                new SpeciesEnum[]{},
                false,
                0.5,
                PalsFriendsTypes.GROOMER,

                false,
                0.0,
                0.0
        );
        var checkEquality = new CreateCaregiver(
                reference,
                "Sid",
                "Bennaceur",
                "sa.bennaceur@test-db-test.com",
                "0764017528",
                "101, rue des Acquevilles",
                "Suresnes",
                "92150",
                "France",
                new Days[]{},
                new SpeciesEnum[]{},
                false,
                0.5,
                PalsFriendsTypes.GROOMER,
                false,
                0.0,
                0.0
        );
        Assertions.assertEquals(createCaregiver.hashCode(), checkEquality.hashCode());
        Assertions.assertEquals(createCaregiver, checkEquality);
        Assertions.assertEquals(createCaregiver.toString(), checkEquality.toString());

    }
}
