package service;

import entity.University;

import java.util.List;

public interface UniversityService {
    void add(University university);

    void update(University university);

    void delete(University university);

    University findById(Long id);

    University findByName(String name);

    List<University> findAll();
}
