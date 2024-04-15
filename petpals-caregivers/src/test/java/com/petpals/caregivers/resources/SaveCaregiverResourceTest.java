package com.petpals.caregivers.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.domain.pojo.Caregivers;
import com.petpals.caregivers.domain.ports.in.CaregiversServicePort;
import com.petpals.shared.entities.CaregiverTypes;
import com.petpals.shared.entities.Species;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import io.quarkus.test.InjectMock;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

class SaveCaregiverResourceTest {

    @InjectMock
    CaregiversServicePort caregiversServicePort;

    @ConfigProperty(name = "api.key")
    String apiKey;

    @Test
    void testAddCaregiver() throws JsonProcessingException {
        var uuid = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,"");
        Mockito.when(caregiversServicePort.addCaregiver(Mockito.any(Caregivers.class))).thenReturn(uuid);
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
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(createCaregiver);
        given()
                .header("API-KEY", apiKey)
                .header("Content-Type", "application/json")
                .body(json)
                .when().post("/caregivers/create")
                .then()
                .statusCode(200)
                .body(is(uuid));
    }
}
