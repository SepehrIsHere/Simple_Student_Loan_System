package repository.impl;

import entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import repository.StudentRepository;

import java.util.List;

public class StudentRepositoryImpl<T extends Student> extends BaseEntityRepositoryImpl<Student> implements StudentRepository {
    private final EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        super(em);
        this.em = em;
    }

    @Override
    public Student save(Student student) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(student);
        transaction.commit();
        return student;
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    @Override
    public Student findByStudentId(Integer studentId) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.studentId = :studentId", Student.class);
        query.setParameter("studentId", studentId);
        return query.getSingleResult();
    }

    @Override
    public Student findByNationalCode(Integer nationalCode) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s WHERE s.nationalCode = :nationalCode", Student.class);
        query.setParameter("nationalCode", nationalCode);
        return query.getSingleResult();
    }

    @Override
    public Student findByFirstNameAndLastName(String firstName, String lastName) {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s where s.firstName = :firstName and s.lastName = :lastName", Student.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        return query.getSingleResult();
    }

    @Override
    public Student login(String username, String password) {
        TypedQuery<Student> query = em.createQuery("SELECT s from Student s where s.username = :username and s.password = :password", Student.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

}
