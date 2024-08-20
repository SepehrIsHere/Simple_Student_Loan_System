package repository.impl;

import entity.Installment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.InstallmentRepository;

import java.util.List;

public class InstallmentRepositoryImpl<T extends Installment> extends BaseEntityRepositoryImpl<Installment> implements InstallmentRepository {
    private final EntityManager em;

    public InstallmentRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public List<Installment> findAll() {
        TypedQuery<Installment> query = em.createQuery("FROM Installment", Installment.class);
        return query.getResultList();
    }

    @Override
    public Installment findById(Long id) {
        TypedQuery<Installment> query = em.createQuery("FROM Installment WHERE id = :id", Installment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}
