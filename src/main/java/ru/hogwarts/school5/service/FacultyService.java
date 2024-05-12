package ru.hogwarts.school5.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school5.exception.FacultyNotFoundException;
import ru.hogwarts.school5.model.Faculty;
import ru.hogwarts.school5.repository.FacultyRepository;

import java.util.Collection;

import org.slf4j.Logger;

import java.util.Comparator;

@Service
public class FacultyService {
    private final Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty create(Faculty faculty) {
        logger.info("Метод create был вызван");
        return facultyRepository.save(faculty);
    }


    public Faculty getById(Long id) {
        logger.info("Метод getById был вызван");
        return facultyRepository.findById(id).orElseThrow(FacultyNotFoundException::new);
    }

    public Faculty update(Long id, Faculty faculty) {
        logger.warn("Метод update был вызван, чтобы редактировать факультет");
        Faculty existingFaculty = getById(id);
        existingFaculty.setName(faculty.getName());
        existingFaculty.setColor(faculty.getColor());
        facultyRepository.save(existingFaculty);
        return existingFaculty;
    }

    public Faculty remove(Long id) {
        logger.warn("Метод remove был вызван, чтобы удалить факультет");
        Faculty existingFaculty = getById(id);
        facultyRepository.delete(existingFaculty);
        return existingFaculty;
    }

    public Collection<Faculty> getAll() {
        logger.info("Метод getAll был вызван");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getByColor(String color) {
        logger.info("Метод getByColor был вызван");
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> getByNameOrColor(String name, String color) {
        logger.info("Метод getByNameOrColor был вызван");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    public String getLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(s -> s.getName())
                .max(Comparator.comparingInt(String::length))
                .get();
    }
   /* public String getLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElseThrow();
    }*/

}