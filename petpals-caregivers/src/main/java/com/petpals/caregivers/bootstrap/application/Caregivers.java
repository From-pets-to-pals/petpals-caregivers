package com.petpals.caregivers.bootstrap.application;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;



@QuarkusMain
public class Caregivers {
    public static void main(String ... args) {
        Quarkus.run(args);
    }
}
