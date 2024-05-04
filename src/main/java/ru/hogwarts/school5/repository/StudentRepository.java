package ru.hogwarts.school5.repository;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.school5.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> getStudentByName(String name);
    List<Student> findAllByAge(int age);

    List<Student> findAllByAgeBetween(int min, int max);
    @Query(value = "SELECT count(*) FROM student", nativeQuery = true)
    Integer getCountStudents();
    @Query(value = "SELECT AVG(age) from student", nativeQuery = true)
    Double getAverageAge();
    @Query(value = "SELECT * from  student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudents();

}


