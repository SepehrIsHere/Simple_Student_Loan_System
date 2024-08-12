package service;

import entity.Student;

import java.util.List;

public interface StudentService {
    void add(Student student);

    void update(Student student);

    void delete(Student student);

    Student findById(Long id);

    List<Student> findAll();

    Student findByStudentId(Integer studentId);

    Student findByNationalCode(Integer nationalCode);
}
