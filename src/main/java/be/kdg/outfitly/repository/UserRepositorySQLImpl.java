package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.User;
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
public class UserRepositorySQLImpl implements UserRepository{

    private final Logger logger = LoggerFactory.getLogger(UserRepositorySQLImpl.class);

    @PersistenceUnit
    private EntityManagerFactory emFactory;
    EntityManager em;

    public UserRepositorySQLImpl() {
        logger.debug("Creating user repository (SQL)");
    }

    @Override
    public User create(User user) {
        logger.debug("Created user (SQL): " + user.toString());
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public List<User> read() {
        logger.debug("Read users (SQL)");
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        List<User> users = em.createQuery("select u from User u").getResultList();
        logger.warn("All users read: "+users);
        em.getTransaction().commit();
        em.close();
        return users;
    }

    @Override
    public User findById(int id) {
        logger.debug("(SQL) Found user with id " + id);
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.getTransaction().commit();
        em.close();
        return user;
    }

    @Override
    public void update(User user) {
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        User fetchedUser = em.find(User.class, user.getId());
        fetchedUser.merge(em.contains(fetchedUser) ? user : em.merge(fetchedUser));
        em.persist(fetchedUser);
        em.getTransaction().commit();
        em.close();
    }
}
