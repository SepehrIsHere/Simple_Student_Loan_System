package repository.impl;

import entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.UniversityRepository;

import java.util.List;

public class UniversityRepositoryImpl<T extends University> extends BaseEntityRepositoryImpl<University> implements UniversityRepository {
    private final EntityManager em;

    public UniversityRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public University findById(Long id) {
        return em.find(University.class, id);
    }

    @Override
    public University findByName(String name) {
        TypedQuery<University> query = em.createQuery("SELECT u FROM University u WHERE u.name = :name", University.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public List<University> findAll() {
        TypedQuery<University> query = em.createQuery("SELECT u FROM University u", University.class);
        return query.getResultList();
    }
}
