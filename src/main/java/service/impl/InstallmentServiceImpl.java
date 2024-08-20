package service.impl;

import entity.Installment;
import repository.InstallmentRepository;
import service.InstallmentService;

import java.util.List;

public class InstallmentServiceImpl implements InstallmentService {
    private final InstallmentRepository installmentRepository;

    public InstallmentServiceImpl(InstallmentRepository installmentRepository) {
        this.installmentRepository = installmentRepository;
    }

    @Override
    public void add(Installment installment) {
        try {
            installmentRepository.add(installment);
        } catch (Exception e) {
            System.out.println("An error occured while adding the installment" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Installment installment) {
        try {
            installmentRepository.update(installment);
        } catch (Exception e) {
            System.out.println("An error occured while updating the installment" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Installment installment) {
        try {
            installmentRepository.delete(installment);
        } catch (Exception e) {
            System.out.println("An error occured while deleting the installment" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<Installment> findAll() {
        try {
            return installmentRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occured while finding the installments" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Installment findById(Long id) {
        try {
            return installmentRepository.findById(id);
        } catch (Exception e) {
            System.out.println("An error occured while finding the installment" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
