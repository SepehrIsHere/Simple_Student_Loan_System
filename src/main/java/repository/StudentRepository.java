package repository;

import entity.Student;

import java.util.List;

public interface StudentRepository extends BaseEntityRepository<Student> {
    Student save(Student student);

    List<Student> findAll();

    Student findById(Long id);

    Student findByStudentId(Integer studentId);

    Student findByFirstNameAndLastName(String firstName, String lastName);

    Student findByNationalCode(Integer nationalCode);

    Student login(String username, String password);

}
