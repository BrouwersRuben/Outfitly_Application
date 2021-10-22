package be.kdg.outfitly.repository;

import be.kdg.outfitly.domain.Entity;

import java.util.List;

public interface EntityRepository <T extends Entity> {
    List<T> read();
    T create(T t);
    T findById(int id);
    void delete(T t);
    void update(T t);
}
