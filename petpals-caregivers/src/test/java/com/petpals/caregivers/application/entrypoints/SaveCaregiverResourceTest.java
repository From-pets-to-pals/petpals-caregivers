package com.petpals.caregivers.application.entrypoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.petpals.caregivers.application.dto.CreateCaregiver;
import com.petpals.caregivers.application.dto.Days;
import com.petpals.caregivers.persistence.entities.Groomers;
import com.petpals.caregivers.persistence.services.CaregiversPersistencePort;
import com.petpals.shared.entities.uuid.UUIDFormatter;
import com.petpals.shared.entities.uuid.UUIDGenerator;
import com.petpals.shared.model.enums.PalsFriendsTypes;
import com.petpals.shared.model.enums.SpeciesEnum;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class SaveCaregiverResourceTest {

    @InjectMock
    CaregiversPersistencePort caregiversPersistencePort;

    @ConfigProperty(name = "api.key")
    String apiKey;

    @Test
    void testAddCaregiver() throws JsonProcessingException {
        var uuid = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,"");
        Mockito.doNothing().when(caregiversPersistencePort).addGroomer(Mockito.any(Groomers.class));
        var createCaregiver = createCaregiver();
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(createCaregiver);
        given()
                .header("API-KEY", apiKey)
                .header("Content-Type", "application/json")
                .body(json)
                .when().post("/caregivers/create")
                .then()
                .statusCode(204);

    }

    @Test
    void testAddVets() throws JsonProcessingException {
        var uuid = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,"");
        Mockito.doNothing().when(caregiversPersistencePort).addGroomer(Mockito.any(Groomers.class));
        var createCaregiver = createCaregiver();
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(createCaregiver);
        given()
                .header("API-KEY", apiKey)
                .header("Content-Type", "application/json")
                .body(json)
                .when().post("/caregivers/create")
                .then()
                .statusCode(204);

    }

    @Test
    void testAddTrainer() throws JsonProcessingException {
        var uuid = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(),true,"");
        Mockito.doNothing().when(caregiversPersistencePort).addGroomer(Mockito.any(Groomers.class));
        var createCaregiver = createCaregiver();
        ObjectMapper mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(createCaregiver);
        given()
                .header("API-KEY", apiKey)
                .header("Content-Type", "application/json")
                .body(json)
                .when().post("/caregivers/create")
                .then()
                .statusCode(204);

    }

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