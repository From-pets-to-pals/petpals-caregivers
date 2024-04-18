package com.petpals.caregivers.bootstrap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class Error404FileTest {
    @Test
    void buildGeneratesOpenApi() throws IOException {
        var errorPage = new File("target/classes/templates/error404.html");
        Assertions.assertTrue(errorPage.exists());
    }
}
