package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.ClothingItem;
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
public class ClothingRepositorySQLImpl implements ClothingRepository{

    private final Logger logger = LoggerFactory.getLogger(ClothingRepositorySQLImpl.class);

    @PersistenceUnit
    private EntityManagerFactory emFactory;
    EntityManager em;

    public ClothingRepositorySQLImpl() {
        logger.debug("Creating clothing repository (SQL)");
    }

    @Override
    public List<ClothingItem> read() {
        logger.debug("Read clothing items (SQL)");
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        List<ClothingItem> clothingItems = em.createQuery("select c from ClothingItem c").getResultList();
        em.getTransaction().commit();
        em.close();
        return clothingItems;
    }

    @Override
    public ClothingItem findById(int id) {
        logger.debug("(SQL) Found clothingItem with id " + id);
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
        ClothingItem clothingItem = em.find(ClothingItem.class, id);
        em.getTransaction().commit();
        em.close();
        return clothingItem;
    }

    @Override
    public ClothingItem create(ClothingItem clothingItem) {
        logger.debug("(SQL) Created clothing item: " + clothingItem.toString());
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
//        while(!em.contains(clothingItem)){
//            em.merge(clothingItem);
//        }

        em.persist(clothingItem);
        em.getTransaction().commit();
        em.close();
        return clothingItem;
    }

    @Override
    public void delete(ClothingItem clothingItem) {
        logger.debug("(SQL) Removed clothing item: " + clothingItem.toString());
        em = emFactory.createEntityManager();
        em.getTransaction().begin();
//        em.merge(em.contains(clothingItem) ? clothingItem : em.merge(clothingItem) );
//        while(!em.contains(clothingItem)){
//            em.merge(clothingItem);
//        }
        ClothingItem managedClothingItem = em.merge(clothingItem);

        managedClothingItem.setUser(null);
        em.remove(managedClothingItem);
        em.getTransaction().commit();
        em.close();
    }
}
