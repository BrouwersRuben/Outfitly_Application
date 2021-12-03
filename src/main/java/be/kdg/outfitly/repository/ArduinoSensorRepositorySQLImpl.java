package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ArduinoSensor;
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
public class ArduinoSensorRepositorySQLImpl implements ArduinoSensorRepository{

    private final Logger logger = LoggerFactory.getLogger(ArduinoSensorRepositorySQLImpl.class);

    @PersistenceUnit
    private EntityManagerFactory emFactory;
    EntityManager em;

    public ArduinoSensorRepositorySQLImpl() {
        logger.debug("Creating Weather forecast repository (SQL)");
    }

    @Override
    public List<ArduinoSensor> read() {
        logger.debug("Read arduino sensor (SQL)");
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        List<ArduinoSensor> arduinoSensors = em.createQuery("select a from ArduinoSensor a").getResultList();
        em.getTransaction().commit();
        em.close();
        return arduinoSensors;
    }

    @Override
    public ArduinoSensor findById(int id) {
        logger.debug("(SQL) Found arduino sensor with id " + id);
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        ArduinoSensor arduinoSensor = em.find(ArduinoSensor.class, id);
        em.getTransaction().commit();
        em.close();
        return arduinoSensor;
    }

    @Override
    public ArduinoSensor create(ArduinoSensor arduinoSensor) {
        logger.debug("Created arduino sensor (SQL): " + arduinoSensor.toString());
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(arduinoSensor);
        em.getTransaction().commit();
        em.close();
        return arduinoSensor;
    }
}
