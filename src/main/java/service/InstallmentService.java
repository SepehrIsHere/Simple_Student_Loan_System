package service;

import entity.Installment;

import java.util.List;

public interface InstallmentService {
    void add(Installment installment);

    void update(Installment installment);

    void delete(Installment installment);

    List<Installment> findAll();

    Installment findById(Long id);
}
