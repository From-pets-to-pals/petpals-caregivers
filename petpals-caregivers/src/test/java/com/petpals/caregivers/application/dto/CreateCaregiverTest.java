package com.petpals.caregivers.application.dto;

import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateCaregiverTest {
    @Test
     void shouldCheckObjectsIntegrity(){
        var createCaregiver = new CreateCaregiver(
                "Sid",
                "Bennaceur",
                "sa.bennaceur@test-db-test.com",
                "0764017528",
                "101, rue des Acquevilles",
                "Suresnes",
                "92150",
                "France",
                new Days[]{},
                new Species[]{},
                false,
                0.5,
                CaregiverTypes.GROOMER,
                false,
                0.0,
                0.0
        );
        var checkEquality = new CreateCaregiver(
                "Sid",
                "Bennaceur",
                "sa.bennaceur@test-db-test.com",
                "0764017528",
                "101, rue des Acquevilles",
                "Suresnes",
                "92150",
                "France",
                new Days[]{},
                new Species[]{},
                false,
                0.5,
                CaregiverTypes.GROOMER,
                false,
                0.0,
                0.0
        );
        Assertions.assertEquals(createCaregiver.hashCode(), checkEquality.hashCode());
        Assertions.assertEquals(createCaregiver, checkEquality);

    }
}
