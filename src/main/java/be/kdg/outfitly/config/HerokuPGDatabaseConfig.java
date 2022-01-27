package be.kdg.outfitly.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("HK")
public class HerokuPGDatabaseConfig {

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        String dbUrl = "postgres://cfdmjthsvpbkus:1e9aca4bbf69e8818df9d5495811e10f99b85d46858af1405519056eae4d0462@ec2-54-228-95-1.eu-west-1.compute.amazonaws.com:5432/d9drob0geuku";
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
}