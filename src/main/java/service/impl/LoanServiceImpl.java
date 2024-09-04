package service.impl;

import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import enumerations.LoanType;
import repository.LoanRepository;
import service.LoanService;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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
    public Long countLoanByStudentAndMonthAndYearAndType(Student student, Integer month, Integer year, LoanType loanType) {
        try {
            return loanRepository.countLoanByStudentAndMonthAndYearAndType(student, month, year, loanType);
        } catch (Exception e) {
            System.out.println("An error occured while counting loan " + e.getMessage());
        }
        return null;
    }

    @Override
    public Long countHousingLoanByStudentAndEducationDegree(Student student, EducationDegree educationDegree, LoanType loanType) {
        try {
            return loanRepository.countHousingLoanByStudentAndEducationDegree(student, educationDegree, loanType);
        } catch (Exception e) {
            System.out.println("An error occured while counting loan " + e.getMessage());
        }
        return null;
    }

    @Override
    public Loan displayAndChooseLoan(Scanner input, Student student, List<Loan> loans, LoanType loanType) {
        if (loans != null && !loans.isEmpty()) {
            for (Loan loan : loans) {
                System.out.println("Loan Number : " + loan.getId());
                System.out.println("Loan Amount : " + loan.getAmount());
                System.out.println("Loan taken at : " + loan.getDate());
            }

            while (true) {
                System.out.println("Enter the loan number to select the one : ");
                long loanNumber = input.nextLong();
                Loan loan = findById(loanNumber);
                if (loan != null) {
                    return loan;
                } else {
                    System.out.println("Invalid Input ! ");
                }
            }
        } else {
            System.out.println("You dont have a loan !");
        }
        return null;
    }
}
