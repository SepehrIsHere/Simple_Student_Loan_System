package service.impl;

import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import repository.LoanRepository;
import service.LoanService;

import java.util.List;

public class LoanServiceImpl implements LoanService {
    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public void add(Loan loan) {
        try {
            loanRepository.add(loan);
        } catch (Exception e) {
            System.out.println("An error occured while adding a loan" + e.getMessage());
        }
    }

    @Override
    public void update(Loan loan) {
        try {
            loanRepository.update(loan);
        } catch (Exception e) {
            System.out.println("An error occured while updating a loan" + e.getMessage());
        }
    }

    @Override
    public void delete(Loan loan) {
        try {
            loanRepository.delete(loan);
        } catch (Exception e) {
            System.out.println("An error occured while deleting a loan" + e.getMessage());
        }
    }

    @Override
    public Loan findById(Long id) {
        try {
            return loanRepository.findById(id);
        } catch (Exception e) {
            System.out.println("An error occured while finding a loan" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Loan> findAll() {
        try {
            return loanRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occured while finding all loans" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Loan> findByStudent(Student student) {
        try {
            return loanRepository.findByStudent(student);
        } catch (Exception e) {
            System.out.println("An error occured while finding all loans" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Loan> findByDegree(EducationDegree degree) {
        try {
            return loanRepository.findByDegree(degree);
        } catch (Exception e) {
            System.out.println("An error occured while finding all loans" + e.getMessage());
        }
        return null;
    }
}
