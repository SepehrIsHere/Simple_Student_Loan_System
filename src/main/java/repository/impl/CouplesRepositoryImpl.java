package repository.impl;

import entity.Couples;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.CouplesRepository;

import java.util.List;

public class CouplesRepositoryImpl<T extends Couples> extends BaseEntityRepositoryImpl<Couples> implements CouplesRepository {
    private final EntityManager em;

    public CouplesRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public List<Couples> findAll() {
        TypedQuery<Couples> query = em.createQuery("FROM Couples", Couples.class);
        return query.getResultList();
    }

    @Override
    public Couples findById(Long id) {
        TypedQuery<Couples> query = em.createQuery("FROM Couples WHERE id = :id", Couples.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Couples findByFirstStudentAndSecondStudent(Student firstStudent, Student secondStudent) {
        TypedQuery<Couples> query = em.createQuery("SELECT c from Couples c where firstStudent = :firstStudent and secondStudent = :secondStudent", Couples.class);
        query.setParameter("firstStudent", firstStudent);
        query.setParameter("secondStudent", secondStudent);
        return query.getSingleResult();
    }
}
