package be.kdg.outfitly.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("Hibernate")
public class HSQLDatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(HSQLDatabaseConfig.class);

    @Bean
    public DataSource dataSource(){
        logger.debug("Connection with database made");
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.hsqldb.jdbcDriver")
                .url("jdbc:hsqldb:file:dbData/OutfitlyDevelopment")
                .username("sa")
                .password("")
                .build();
        return dataSource;
    }
}