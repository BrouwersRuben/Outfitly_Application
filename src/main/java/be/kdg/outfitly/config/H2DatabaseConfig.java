package be.kdg.outfitly.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("H2")
public class H2DatabaseConfig {
    private static final Logger logger = LoggerFactory.getLogger(H2DatabaseConfig.class);

    @Bean
    public DataSource dataSource(){
        logger.debug("Connection with database made");
        DataSource dataSource = DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:outfitlydevdb")
                .username("sa")
                .password("")
                .build();
        return dataSource;
    }
}