package ru.hogwarts.school5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.school5.model.Faculty;

import java.util.List;


public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findAllByColor(String color);

    List<Faculty> findByNameIgnoreCaseOrColorIgnoreCase(String name, String color);
}