package service.impl;

import entity.Student;
import repository.StudentRepository;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void add(Student student) {
        try {
            studentRepository.add(student);
        } catch (Exception e) {
            System.out.println("An error occured while adding a student" + e.getMessage());
        }
    }

    @Override
    public void update(Student student) {
        try {
            studentRepository.update(student);
        } catch (Exception e) {
            System.out.println("An error occured while updating a student" + e.getMessage());
        }

    }

    @Override
    public void delete(Student student) {
        try {
            studentRepository.delete(student);
        } catch (Exception e) {
            System.out.println("An error occured while deleting a student" + e.getMessage());
        }
    }

    @Override
    public Student findById(Long id) {
        try {
            return studentRepository.findById(id);
        } catch (Exception e) {
            System.out.println("An error occured while finding a student" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occured while finding all students" + e.getMessage());
        }
        return null;
    }

    @Override
    public Student findByStudentId(Integer studentId) {
        try {
            return studentRepository.findByStudentId(studentId);
        } catch (Exception e) {
            System.out.println("An error occured while finding a student" + e.getMessage());
        }
        return null;
    }

    @Override
    public Student findByNationalCode(Integer nationalCode) {
        try {
            return studentRepository.findByNationalCode(nationalCode);
        } catch (Exception e) {
            System.out.println("An error occured while finding a student" + e.getMessage());
        }
        return null;
    }

    @Override
    public Student findByFirstNameAndLastName(String firstName, String lastName) {
        try {
            return studentRepository.findByFirstNameAndLastName(firstName, lastName);
        } catch (Exception e) {
            System.out.println("An error occured while finding a student" + e.getMessage());
        }
        return null;
    }

    @Override
    public Student login(Student student) {
        try {
            studentRepository.login(student.getUsername(), student.getPassword());
        } catch (Exception e) {
            System.out.println("Login failed either username or password is incorrect " + e.getMessage());
        }
        return null;
    }
}
