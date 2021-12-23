package be.kdg.outfitly.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("PGSQL")
public class PGSQLDatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(H2DatabaseConfig.class);

    @Bean
    public DataSource dataSource(){
        logger.debug("Connection with database made");
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.postgresql.Driver")
                .url("jdbc:postgresql:Outfitly")
                .username("postgres")
                .password("postgres")
                .build();
        return dataSource;
    }
}