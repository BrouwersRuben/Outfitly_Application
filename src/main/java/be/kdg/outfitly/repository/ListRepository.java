package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ListRepository<T extends Entity> implements EntityRepository<T>{

    private static final Logger logger = LoggerFactory.getLogger(ListRepository.class);
    protected List<T> entities = new ArrayList<>();

    public ListRepository() {
        logger.debug("Created a List Repository.");
    }

    @Override
    public List<T> read() {
        logger.debug("Reading a List Repository.");
        return entities;
    }

    @Override
    public T create(T entity) {
        int maxId = entities.stream().mapToInt(Entity::getId).max().orElse(0);
        entity.setId(maxId + 1);
        entities.add(entity);
        logger.debug("Creating entity: "+entity+" in "+this);
        return entity;
    }

    @Override
    public T findById(int id) {
        return entities.stream().filter(e->e.getId()==id).findFirst().orElse(null);
    }

    @Override
    public void delete(T entity) {
        logger.debug("Deleting entity: "+entity);
        entities.remove(entity);
    }

    @Override
    public void update(T entity) {
        logger.debug("Updating entity:" + entity);
        int index = entities.indexOf(entity);
        entities.set(index, entity);
    }
}