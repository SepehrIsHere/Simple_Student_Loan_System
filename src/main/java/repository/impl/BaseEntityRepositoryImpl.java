package repository.impl;

import entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import repository.BaseEntityRepository;

public class BaseEntityRepositoryImpl<T extends BaseEntity> implements BaseEntityRepository<T> {
    private final EntityManager em;

    public BaseEntityRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(T entity) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(entity);
        transaction.commit();
    }

    @Override
    public void update(T entity) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(entity);
        transaction.commit();
    }

    @Override
    public void delete(T entity) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(entity);
    }
}
