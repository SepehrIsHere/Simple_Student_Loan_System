package service.impl;

import entity.Couples;
import entity.Student;
import repository.CouplesRepository;
import service.CouplesService;

import java.util.List;

public class CouplesServiceImpl implements CouplesService {
    private final CouplesRepository couplesRepository;

    public CouplesServiceImpl(CouplesRepository couplesRepository) {
        this.couplesRepository = couplesRepository;
    }

    @Override
    public void add(Couples couples) {
        try {
            couplesRepository.add(couples);
        } catch (Exception e) {
            System.out.println("An error occured while adding couples" + e.getMessage());
        }
    }

    @Override
    public void update(Couples couples) {
        try {
            couplesRepository.update(couples);
        } catch (Exception e) {
            System.out.println("An error occured while updating couples" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Couples couples) {
        try {
            couplesRepository.delete(couples);
        } catch (Exception e) {
            System.out.println("An error occured while deleting couples" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Couples> findAll() {
        try {
            return couplesRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occured while finding couples" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Couples findById(Long id) {
        try {
            return couplesRepository.findById(id);
        } catch (Exception e) {
            System.out.println("An error occured while finding couples" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Couples findByFirstStudentAndSecondStudent(Student firstStudent, Student secondStudent) {
        try {
            return couplesRepository.findByFirstStudentAndSecondStudent(firstStudent, secondStudent);
        } catch (Exception e) {
            System.out.println("An error occured while finding couples" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
