package com.petpals.caregivers.domain.services.pojo;

import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.domain.commands.CreateCaregiverCommand;
import com.petpals.shared.enums.CaregiverTypes;
import com.petpals.shared.enums.Species;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreateCaregiverCommandTest {
    @Test
    void shouldBeSameObject(){
        CreateCaregiverCommand createCaregiverCommand = new CreateCaregiverCommand(
                null,
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
                5.0,
                5.0,
                false,
                CaregiverTypes.GROOMER
        );

        CreateCaregiverCommand check = new CreateCaregiverCommand(
                null,
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
                5.0,
                5.0,
                false,
                CaregiverTypes.GROOMER
        );
        Assertions.assertEquals(createCaregiverCommand.hashCode(), check.hashCode());
        Assertions.assertEquals(createCaregiverCommand, check);
        Assertions.assertEquals(createCaregiverCommand.toString(), check.toString());
    }

    @Test
    void shouldNotBeSameObject(){
        CreateCaregiverCommand createCaregiverCommand = new CreateCaregiverCommand(
                null,
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
                5.0,
                5.0,
                false,
                CaregiverTypes.GROOMER
        );

        CreateCaregiverCommand check = new CreateCaregiverCommand(
                null,
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
                5.0,
                5.0,
                false,
                CaregiverTypes.VET
        );
        Assertions.assertNotEquals(createCaregiverCommand.hashCode(), check.hashCode());
        Assertions.assertNotEquals(createCaregiverCommand, check);
        Assertions.assertNotEquals(createCaregiverCommand.toString(), check.toString());
    }
}
