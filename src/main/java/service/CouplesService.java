package service;

import entity.Couples;
import entity.Student;

import java.util.List;

public interface CouplesService {
    void add(Couples couples);

    void update(Couples couples);

    void delete(Couples couples);

    List<Couples> findAll();

    Couples findById(Long id);

    Couples findByFirstStudentAndSecondStudent(Student firstStudent, Student secondStudent);
}
