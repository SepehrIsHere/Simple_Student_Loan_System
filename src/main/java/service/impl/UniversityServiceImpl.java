package service.impl;

import entity.University;
import enumerations.UniversityType;
import jakarta.persistence.NoResultException;
import repository.UniversityRepository;
import service.UniversityService;

import java.util.List;

public class UniversityServiceImpl implements UniversityService {
    private final UniversityRepository universityRepository;

    public UniversityServiceImpl(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    @Override
    public void add(University university) {
        try {
            universityRepository.add(university);
        } catch (Exception e) {
            System.out.println("An error occured while adding university" + e.getMessage());
        }
    }

    @Override
    public void update(University university) {
        try {
            universityRepository.update(university);
        } catch (Exception e) {
            System.out.println("An error occured while updating university" + e.getMessage());
        }
    }

    @Override
    public void delete(University university) {
        try {
            universityRepository.delete(university);
        } catch (Exception e) {
            System.out.println("An error occured while deleting university" + e.getMessage());
        }
    }

    @Override
    public University findById(Long id) {
        try {
            return universityRepository.findById(id);
        } catch (Exception e) {
            System.out.println("An error occured while finding university" + e.getMessage());
        }
        return null;
    }

    @Override
    public University findByName(String name) {
        try {
            return universityRepository.findByName(name);
        } catch (Exception e) {
            System.out.println("An error occured while finding university" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<University> findAll() {
        try {
            return universityRepository.findAll();
        } catch (Exception e) {
            System.out.println("An error occured while finding university" + e.getMessage());
        }
        return null;
    }

    @Override
    public University findByNameAndUniversityType(String universityName, UniversityType universityType) {
        try {
            return universityRepository.findByNameAndUniversityType(universityName, universityType);
        } catch (NoResultException e) {
            System.out.println("An error occured while finding university " + e.getMessage());
        }
        return null;
    }
}
