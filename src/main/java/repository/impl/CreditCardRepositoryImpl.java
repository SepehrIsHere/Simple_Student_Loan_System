package repository.impl;

import entity.BaseEntity;
import entity.CreditCard;
import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.CreditCardRepository;

import java.util.List;

public class CreditCardRepositoryImpl<T extends CreditCard> extends BaseEntityRepositoryImpl<CreditCard> implements CreditCardRepository {
    private final EntityManager em;

    public CreditCardRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public List<CreditCard> findAll() {
        TypedQuery<CreditCard> query = em.createQuery("SELECT c FROM CreditCard c", CreditCard.class);
        return query.getResultList();
    }

    @Override
    public CreditCard findById(Long id) {
        return em.find(CreditCard.class, id);
    }

    @Override
    public CreditCard findByCardNumber(Integer cardNumber) {
        TypedQuery<CreditCard> query = em.createQuery("SELECT c FROM CreditCard c WHERE c.cardNumber = :cardNumber", CreditCard.class);
        query.setParameter("cardNumber", cardNumber);
        return query.getSingleResult();
    }

    @Override
    public CreditCard findByStudent(Student student) {
        TypedQuery<CreditCard> query = em.createQuery("SELECT c FROM CreditCard c WHERE c.student = :student", CreditCard.class);
        query.setParameter("student", student);
        return query.getSingleResult();
    }
}
