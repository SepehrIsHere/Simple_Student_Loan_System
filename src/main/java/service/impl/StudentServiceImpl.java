package service.impl;

import entity.Student;
import jakarta.persistence.NoResultException;
import jakarta.validation.ConstraintViolationException;
import repository.StudentRepository;
import service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        try {
            return studentRepository.save(student);
        } catch (ConstraintViolationException e) {
            System.out.println("ConstraintViolationException" + e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Student student) {
        try {
            studentRepository.add(student);
        } catch (ConstraintViolationException e) {
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
    public Student login(String username, String password) {
        try {
            return studentRepository.login(username, password);
        } catch (NoResultException e) {
            System.out.println("Student dosent exist or username or password is incorrect ! ");
        }
        return null;
    }

    @Override
    public Boolean studentExists(String firstName, String lastName) {
        try {
            Student student = studentRepository.findByFirstNameAndLastName(firstName, lastName);
            return student != null;
        } catch (Exception e) {
            System.out.println("An error occured while finding a student" + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
