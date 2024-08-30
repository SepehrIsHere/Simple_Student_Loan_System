package service.impl;

import entity.Loan;
import entity.Student;
import enumerations.LoanType;
import repository.LoanRepository;
import service.LoanService;

import java.time.LocalDate;
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
    public List<Loan> findByStudentAndLoanType(Student student, LoanType type) {
        try {
            return loanRepository.findByStudentAndLoanType(student, type);
        } catch (Exception e) {
            System.out.println("An error occured while finding all loans" + e.getMessage());
        }
        return null;
    }


    @Override
    public List<Loan> findByStudentAndTypeAndYear(Student student, LoanType type, Integer year) {
        try {
            return loanRepository.findByStudentAndTypeAndYear(student, type, year);
        } catch (Exception e) {
            System.out.println("An error occured while finding the loan " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Loan findByStudentMonthYearAndType(Student student, LoanType type, Integer month, Integer year) {
        try {
            return loanRepository.findByStudentMonthYearAndType(student, type, month, year);
        } catch (Exception e) {
            System.out.println("An error occured while finding loan " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Loan findByStudentAndLoanTypeAndYear(Student student, LoanType type, Integer year) {
        try {
            return loanRepository.findByStudentAndLoanTypeAndYear(student, type, year);
        } catch (Exception e) {
            System.out.println("An error occured while finding the loan " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
