package repository.impl;

import entity.Student;
import jakarta.persistence.EntityManager;
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


}
