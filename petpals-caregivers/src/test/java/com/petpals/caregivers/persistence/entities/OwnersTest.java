package com.petpals.caregivers.persistence.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OwnersTest {
    @Test
    void shouldBeSameObjects(){
        var owner = new Owners();
        owner.setDevice("OPPO x9");
        owner.setMail("sa.bennaceur@gmail.com");
        owner.setReference("123456789123456789");
        owner.setLocation("France");
        var checkEquality = new Owners();
        checkEquality.setDevice("OPPO x9");
        checkEquality.setMail("sa.bennaceur@gmail.com");
        checkEquality.setReference("123456789123456789");
        checkEquality.setLocation("France");
        Assertions.assertEquals(owner.toString(), checkEquality.toString());
        Assertions.assertEquals(owner, checkEquality);
        Assertions.assertEquals(owner.hashCode(), checkEquality.hashCode());
    }

    @Test
    void shouldNotBeSameObjects(){
        var owner = new Owners();
        owner.setDevice("OPPO x9");
        owner.setMail("sa.bennaceur@gmail.com");
        owner.setReference("123456789123456789");
        owner.setLocation("France");
        var checkEquality = new Owners();
        checkEquality.setDevice("OPPO x9");
        checkEquality.setMail("sa.bennaceur@gmail.com");
        checkEquality.setReference("123456789123456789");
        checkEquality.setLocation("Italie");
        Assertions.assertNotEquals(owner.toString(), checkEquality.toString());
        Assertions.assertNotEquals(owner, checkEquality);
        Assertions.assertNotEquals(owner.hashCode(), checkEquality.hashCode());
    }
}
