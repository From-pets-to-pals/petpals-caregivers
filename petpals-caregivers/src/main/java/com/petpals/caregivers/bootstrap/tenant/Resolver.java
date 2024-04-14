package com.petpals.caregivers.bootstrap.tenant;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@PersistenceUnitExtension
public class Resolver implements TenantResolver {

    @Override
    public String getDefaultTenantId() {
        return "azure";
    }

    @Override
    public String resolveTenantId() {
        return "azure";
    }
}
