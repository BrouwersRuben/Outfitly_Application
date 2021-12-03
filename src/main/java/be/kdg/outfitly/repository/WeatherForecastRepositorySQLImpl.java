package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.WeatherForecast;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository
@Profile("Hibernate")
public class WeatherForecastRepositorySQLImpl implements WeatherForecastRepository{

    private final Logger logger = LoggerFactory.getLogger(WeatherForecastRepositorySQLImpl.class);

    @PersistenceUnit
    private EntityManagerFactory emFactory;
    EntityManager em;

    public WeatherForecastRepositorySQLImpl() {
        logger.debug("Creating Weather forecast repository (SQL)");
    }

    @Override
    public WeatherForecast create(WeatherForecast weatherForecast) {
        logger.debug("Created weatherForecast (SQL): " + weatherForecast.toString());
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(weatherForecast);
        em.getTransaction().commit();
        em.close();
        return weatherForecast;
    }

    @Override
    public List<WeatherForecast> read() {
        logger.debug("Read weather forecast (SQL)");
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        List<WeatherForecast> weatherForecasts = em.createQuery("select w from WeatherForecast w").getResultList();
        em.getTransaction().commit();
        em.close();
        return weatherForecasts;
    }
}
