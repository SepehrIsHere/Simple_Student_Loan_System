package repository;

import entity.Couples;
import entity.Student;

import java.util.List;

public interface CouplesRepository extends BaseEntityRepository<Couples> {
    List<Couples> findAll();

    Couples findById(Long id);

    Couples findByFirstStudentAndSecondStudent(Student firstStudent, Student secondStudent);
}
