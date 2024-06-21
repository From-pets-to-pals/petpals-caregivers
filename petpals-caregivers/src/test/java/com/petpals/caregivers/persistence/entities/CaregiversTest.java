package com.petpals.caregivers.persistence.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CaregiversTest {
    @Test
    void shouldBeSameObjects(){
        var caregivers = new Caregivers();
        caregivers.setFirstName("Sid");
        caregivers.setLastName("Bee");
        caregivers.setEmail("sa.benn@gmail.com");
        caregivers.setPhoneNumber("+336454545");
        caregivers.setAddress("La rue");
        caregivers.setCity("La Ville");
        caregivers.setZipCode("00000");
        caregivers.setCountry(new Countries());
        String[] wd = new String[]{"MONDAY"};
        caregivers.setWorkingDays(wd);
        String[] ph = new String[]{"DOG","CAT"};
        caregivers.setPalsHandled(ph);
        caregivers.setHomeService(true);
        caregivers.setAppointmentDuration(0.5);
        caregivers.setPriceRating(2.0);
        caregivers.setServiceRating(2.0);
        caregivers.setSubscribed(true);
        caregivers.setReference("123456789123456789");
        var checkEquality = new Caregivers();
        checkEquality.setFirstName("Sid");
        checkEquality.setLastName("Bee");
        checkEquality.setEmail("sa.benn@gmail.com");
        checkEquality.setPhoneNumber("+336454545");
        checkEquality.setAddress("La rue");
        checkEquality.setCity("La Ville");
        checkEquality.setZipCode("00000");
        checkEquality.setCountry(new Countries());
        checkEquality.setWorkingDays(wd);
        checkEquality.setPalsHandled(ph);
        checkEquality.setHomeService(true);
        checkEquality.setAppointmentDuration(0.5);
        checkEquality.setPriceRating(2.0);
        checkEquality.setServiceRating(2.0);
        checkEquality.setSubscribed(true);
        checkEquality.setReference("123456789123456789");
        Assertions.assertEquals(caregivers.hashCode(), checkEquality.hashCode());
        Assertions.assertEquals(caregivers, checkEquality);
        Assertions.assertEquals(caregivers.toString(), checkEquality.toString());

    }

    @Test
    void shouldNotBeSameObjects(){
        var caregivers = new Caregivers();
        caregivers.setFirstName("Sid");
        caregivers.setLastName("Bee");
        caregivers.setEmail("sa.benn@gmail.com");
        caregivers.setPhoneNumber("+336454545");
        caregivers.setAddress("La rue");
        caregivers.setCity("La Ville");
        caregivers.setZipCode("00000");
        caregivers.setCountry(new Countries());
        String[] wd = new String[]{"MONDAY"};
        caregivers.setWorkingDays(wd);
        String[] ph = new String[]{"DOG","CAT"};
        caregivers.setPalsHandled(ph);
        caregivers.setHomeService(true);
        caregivers.setAppointmentDuration(0.5);
        caregivers.setPriceRating(2.0);
        caregivers.setServiceRating(2.0);
        caregivers.setSubscribed(true);
        caregivers.setReference("123456789123456789");
        var checkEquality = new Caregivers();
        checkEquality.setFirstName("Sid");
        checkEquality.setLastName("Bee");
        checkEquality.setEmail("sa.benn@gmail.com");
        checkEquality.setPhoneNumber("+336454545");
        checkEquality.setAddress("La rue");
        checkEquality.setCity("La Ville");
        checkEquality.setZipCode("00000");
        checkEquality.setCountry(new Countries());
        checkEquality.setWorkingDays(wd);
        checkEquality.setPalsHandled(ph);
        checkEquality.setHomeService(true);
        checkEquality.setAppointmentDuration(0.5);
        checkEquality.setPriceRating(2.0);
        checkEquality.setServiceRating(2.0);
        checkEquality.setSubscribed(true);
        checkEquality.setReference("1234567891234567891"); // Modified value
        Assertions.assertEquals(caregivers.hashCode(), checkEquality.hashCode());
        Assertions.assertNotEquals(caregivers, checkEquality);
    }
}
