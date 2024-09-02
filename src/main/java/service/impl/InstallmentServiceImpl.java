package service.impl;

import entity.Installment;
import entity.Loan;
import entity.Student;
import enumerations.EducationDegree;
import enumerations.InstallmentStatus;
import enumerations.LoanType;
import repository.InstallmentRepository;
import service.InstallmentService;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;


public class InstallmentServiceImpl implements InstallmentService {
    DecimalFormat tomanFormat = new DecimalFormat("#,###");
    private final double INTEREST_RATE = 0.04;
    private final InstallmentRepository installmentRepository;

    public InstallmentServiceImpl(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    @Override
    public void add(Installment installment) {
        try {
            installmentRepository.add(installment);
        } catch (Exception e) {
            System.out.println("An error occured while adding the installment " + e.getMessage());
        }
    }

    @Override
    public void update(Installment installment) {
        try {
            installmentRepository.update(installment);
        } catch (Exception e) {
            System.out.println("An error occured while updating the installment " + e.getMessage());
        }
    }

    @Override
    public void delete(Installment installment) {
        try {
            installmentRepository.delete(installment);
        } catch (Exception e) {
            System.out.println("An error occured while deleting the installment " + e.getMessage());
        }
    }

    @Override
    public List<Installment> findAll() {
        try {
            return installmentRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occured while finding the installments " + e.getMessage());
        }
        return null;
    }

    @Override
    public Installment findById(Long id) {
        try {
            return installmentRepository.findById(id);
        } catch (Exception e) {
            System.out.println("An error occured while finding the installment " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Installment> findByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus) {
        try {
            return installmentRepository.findByLoanTypeAndPaymentStatus(loanType, installmentStatus);
        } catch (Exception e) {
            System.out.println("An error occured while finding the installments " + e.getMessage());
        }
        return null;
    }

    @Override
    public Long findInstallmentCountByLoanTypeAndPaymentStatus(LoanType loanType, InstallmentStatus installmentStatus) {
        try {
            return installmentRepository.findInstallmentCountByLoanTypeAndPaymentStatus(loanType, installmentStatus);
        } catch (Exception e) {
            System.out.println("An error occured while finding the installments " + e.getMessage());
        }
        return null;
    }

    @Override
    public double getEducationLoanAmount(Student student) {
        double loanAmount;
        switch (student.getEducationDegree()) {
            case ASSOCIATE, BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> loanAmount = 1900000.0 * (1 + INTEREST_RATE);
            case MASTER_PEYVASTE, MASTER_NAPEYVASTE, PHD_PEYVASTER -> loanAmount = 2250000.0 * (1 + INTEREST_RATE);
            case PHD_NAPEYVASTE -> loanAmount = 2600000.0 * (1 + INTEREST_RATE);
            default -> loanAmount = 0.0;
        }
        return Double.parseDouble(tomanFormat.format(loanAmount));
    }

    // Method to calculate the tuition loan amount including 4% interest
    @Override
    public double getTuitionLoanAmount(Student student) {
        double loanAmount;
        switch (student.getEducationDegree()) {
            case ASSOCIATE, BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> loanAmount = 1300000.0 * (1 + INTEREST_RATE);
            case MASTER_PEYVASTE, MASTER_NAPEYVASTE, PHD_PEYVASTER -> loanAmount = 2600000.0 * (1 + INTEREST_RATE);
            case PHD_NAPEYVASTE -> loanAmount = 65000000.0 * (1 + INTEREST_RATE);
            default -> loanAmount = 0.0;
        }
        return Double.parseDouble(tomanFormat.format(loanAmount));
    }

    // Method to calculate the housing loan amount including 4% interest
    @Override
    public double getHousingLoanAmount(Student student) {
        double loanAmount;
        switch (student.getUniversity().getMetropolis()) {
            case TEHRAN -> loanAmount = 32000000.0 * (1 + INTEREST_RATE);
            case ALBORZ, AZARBAYEJAN_SHARQI, ESFEHAN, FARS, GILLAN, QOM, KHOOZESTAN, KHORASAN_RAZAVI ->
                    loanAmount = 26000000.0 * (1 + INTEREST_RATE);
            default -> loanAmount = 19500000.0 * (1 + INTEREST_RATE);
        }
        return Double.parseDouble(tomanFormat.format(loanAmount));
    }

    public double getBaseEducationLoanAmount(Student student) {
        double loanAmount = getEducationLoanAmount(student);
        return loanAmount / (1 + INTEREST_RATE);
    }

    public double getBaseTuitionLoanAmount(Student student) {
        double loanAmount = getTuitionLoanAmount(student);
        return loanAmount / (1 + INTEREST_RATE);
    }

    public double getBaseHousingLoanAmount(Student student) {
        double loanAmount = getHousingLoanAmount(student);
        return loanAmount / (1 + INTEREST_RATE);
    }

    @Override
    public double calculateInstallmentForYear(int year, double baseInstallmentAmount) {
        if (year < 1 || year > 5) {
            throw new IllegalArgumentException("Year must be between 1 and 5.");
        }
        double yearlyMultiplier = Math.pow(2, year - 1);
        double installmentAmount = baseInstallmentAmount * yearlyMultiplier;

        return Double.parseDouble(tomanFormat.format(installmentAmount));
    }

    @Override
    public int graduationYear(Student student, EducationDegree educationDegree) {
        int graduationYear = 0;
        switch (educationDegree) {
            case BACHELOR_PEYVASTE, BACHELOR_NAPEYVASTE -> graduationYear = student.getYearOfEnter() + 4;

            case ASSOCIATE, MASTER_NAPEYVASTE -> graduationYear = student.getYearOfEnter() + 2;

            case MASTER_PEYVASTE -> graduationYear = student.getYearOfEnter() + 6;

            case PHD_NAPEYVASTE, PHD_PEYVASTER -> graduationYear = student.getYearOfEnter() + 5;

        }
        return graduationYear;
    }

    @Override
    public double findTotalAmount(Student student, Loan loan) {
        double totalAmount;
        switch (loan.getLoanType()) {
            case EDUCATION ->
                    totalAmount = getEducationLoanAmount(student);
            case TUITION ->
                    totalAmount = getTuitionLoanAmount(student);
            case HOUSING ->
                    totalAmount = getHousingLoanAmount(student);
            default -> totalAmount = 0.0;
        }
        tomanFormat.format(totalAmount);
        return totalAmount;
    }

    @Override
    public Installment createInstallment(Student student, Loan loan,double paidAmount,LocalDate payedDate) {
        Installment installment = new Installment();
        installment.setLoan(loan);
        installment.setInstallmentStatus(InstallmentStatus.PAYED);
        installment.setPayedDate(payedDate);
        installment.setPaidAmount(paidAmount);
        installment.setTotalAmount(findTotalAmount(student, loan));
        add(installment);
        return installment;
    }
}
