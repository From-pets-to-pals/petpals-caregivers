package com.petpals.caregivers.bootstrap.openapi;

import jakarta.ws.rs.core.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeIn;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;

@OpenAPIDefinition(
        info = @Info(
                title = "petpals-caregivers",
                version = "0.1.0",
                description = "Caregivers API",
                license = @License(name = "Apache 2.0", url = "https://foo.bar"),
                contact = @Contact(name = "Sid", email = "sa.bennaceur@gmail.com")
        )
)
@SecuritySchemes(
        value = {
                @SecurityScheme(
                        scheme ="api-key",
                        securitySchemeName = "api_key",
                        type = SecuritySchemeType.APIKEY,
                        apiKeyName = "API-KEY",
                        in = SecuritySchemeIn.HEADER
                )
        }
)
public class Swagger extends Application {
}
