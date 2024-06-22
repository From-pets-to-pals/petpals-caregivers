package com.petpals.caregivers.persistence.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PalsTest {
    @Test
    void shouldBeSameObjects(){
        var owner = new Owners();
        var pal = new Pals();
        pal.setBreed((short)1);
        pal.setName("Tobby");
        pal.setMale(true);
        pal.setHasPassport(true);
        pal.setOwner(owner);
        pal.setShortname("Tobby");
        pal.setVaccinated(true);
        var checkEquality = new Pals();
        checkEquality.setBreed((short)1);
        checkEquality.setName("Tobby");
        checkEquality.setMale(true);
        checkEquality.setHasPassport(true);
        checkEquality.setOwner(owner);
        checkEquality.setShortname("Tobby");
        checkEquality.setVaccinated(true); // modified value
        Assertions.assertEquals(pal.toString(), checkEquality.toString());
        Assertions.assertEquals(pal, checkEquality);
        Assertions.assertEquals(pal.hashCode(), checkEquality.hashCode());

    }

    @Test
    void shouldNotBeSameObjects(){
        var owner = new Owners();
        var pal = new Pals();
        pal.setBreed((short)1);
        pal.setName("Tobby");
        pal.setMale(true);
        pal.setHasPassport(true);
        pal.setOwner(owner);
        pal.setShortname("Tobby");
        pal.setVaccinated(true);
        var checkEquality = new Pals();
        pal.setBreed((short)1);
        pal.setName("Tobby");
        pal.setMale(true);
        pal.setHasPassport(true);
        pal.setOwner(owner);
        pal.setShortname("Tobby");
        pal.setVaccinated(false); // modified value
        Assertions.assertNotEquals(pal, checkEquality);
        Assertions.assertNotEquals(pal.toString(), checkEquality.toString());
        Assertions.assertNotEquals(pal.hashCode(), checkEquality.hashCode());
    }
}
