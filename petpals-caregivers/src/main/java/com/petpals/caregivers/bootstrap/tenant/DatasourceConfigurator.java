package com.petpals.caregivers.bootstrap.tenant;

import com.azure.identity.ClientSecretCredentialBuilder;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import io.agroal.api.AgroalDataSource;
import io.agroal.api.configuration.supplier.AgroalConnectionFactoryConfigurationSupplier;
import io.agroal.api.configuration.supplier.AgroalConnectionPoolConfigurationSupplier;
import io.agroal.api.configuration.supplier.AgroalDataSourceConfigurationSupplier;
import io.agroal.api.security.NamePrincipal;
import io.agroal.api.security.SimplePassword;
import io.agroal.api.transaction.TransactionIntegration;
import io.agroal.narayana.NarayanaTransactionIntegration;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.customized.QuarkusConnectionProvider;
import io.quarkus.hibernate.orm.runtime.tenant.TenantConnectionResolver;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.TransactionSynchronizationRegistry;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.jboss.logging.Logger;

import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
@PersistenceUnitExtension
public class DatasourceConfigurator implements TenantConnectionResolver {
    private final Logger LOG = Logger.getLogger(DatasourceConfigurator.class);
    TransactionManager transactionManager;
    TransactionSynchronizationRegistry transactionSynchronizationRegistry;

    public DatasourceConfigurator(TransactionManager transactionManager, TransactionSynchronizationRegistry transactionSynchronizationRegistry) {
        this.transactionManager = transactionManager;
        this.transactionSynchronizationRegistry = transactionSynchronizationRegistry;
    }

    private final String KEY_VAULT_URI =  "https://petpals-key-vault.vault.azure.net";

    private AgroalDataSource createDatasource() {
        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(KEY_VAULT_URI)
                .credential(new ClientSecretCredentialBuilder()
                        .tenantId("851bef4a-6c51-445b-812f-9dd619bedeb7")
                        .clientSecret("UYk8Q~.93HF9GO_VP9W75kjwT4ksPX0DgPiiCbUq")
                        .clientId("6e314b3a-dd0c-4a97-b9dd-1ad9a8b0402a")
                        .build())
                .buildClient();
        LOG.info("Retrieving secrets from Azure key vault");
        LOG.info(secretClient.getSecret("DB-URL").getValue());
        final String url = secretClient.getSecret("DB-URL").getValue() + "/" + secretClient.getSecret("DB-NAME").getValue() + "?currentSchema=" + secretClient.getSecret("DB-NAME").getValue();
        AgroalDataSourceConfigurationSupplier dataSourceConfiguration = new AgroalDataSourceConfigurationSupplier();

        AgroalConnectionPoolConfigurationSupplier poolConfiguration = dataSourceConfiguration.connectionPoolConfiguration();

        TransactionIntegration txIntegration = new NarayanaTransactionIntegration(transactionManager, transactionSynchronizationRegistry, null, false, null);
        poolConfiguration
                .initialSize(2)
                .maxSize(10)
                .minSize(2)
                .maxLifetime(Duration.of(5, ChronoUnit.MINUTES))
                .acquisitionTimeout(Duration.of(30, ChronoUnit.SECONDS))
                .transactionIntegration(txIntegration); //This part, specify transaction integration

        AgroalConnectionFactoryConfigurationSupplier connectionFactoryConfiguration = poolConfiguration.connectionFactoryConfiguration();

        connectionFactoryConfiguration
                .jdbcUrl("jdbc:postgresql://"+url)
                .credential(new NamePrincipal((secretClient.getSecret("DB-ADMIN").getValue())))
                .credential(new SimplePassword((secretClient.getSecret("DB-PASSWORD").getValue())));
    try{
        return AgroalDataSource.from(dataSourceConfiguration.get());
    } catch (
    SQLException ex) {
        throw new IllegalStateException("Failed to create a new data source based on the existing datasource configuration", ex);
    }
}

    @Override
    public ConnectionProvider resolve(String tenantId) {
        return new QuarkusConnectionProvider(createDatasource());
    }
}
