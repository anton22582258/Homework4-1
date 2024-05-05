package ru.hogwarts.school5.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school5.exception.StudentNotFoundException;
import ru.hogwarts.school5.model.Faculty;
import ru.hogwarts.school5.model.Student;
import ru.hogwarts.school5.repository.StudentRepository;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student create(Student student) {
        logger.info("Метод create был вызван");
        return studentRepository.save(student);
    }


    public Student getById(Long id) {
        logger.info("Метод getById был вызван");
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    public Student update(Long id, Student student) {
        logger.warn("Метод update был вызван, чтобы редактировать студента");
        Student existingStudent = getById(id);
        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        studentRepository.save(existingStudent);
        return existingStudent;
    }

    public Student delete(Long id) {
        logger.warn("Метод delete был вызван, чтобы удалить студента");
        Student existingStudent = getById(id);
        studentRepository.delete(existingStudent);
        return existingStudent;
    }

    public Collection<Student> getAll() {
        logger.info("Метод getAll был вызван");
        return studentRepository.findAll();
    }

    public Collection<Student> getByAge(int age) {
        logger.info("Метод getByAge был вызван");
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> getByAgeBetween(int min, int max) {
        logger.info("Метод getByAgeBetween был вызван");
        return studentRepository.findAllByAgeBetween(min, max);
    }
    public Faculty getFacultyById(Long studentId) {
        logger.info("Метод getFacultyById был вызван");
        return studentRepository.findById(studentId).get().getFaculty();
    }

    public Integer getCountStudents() {
        logger.info("Метод getCountStudents был вызван");
        return studentRepository.getCountStudents();
    }

    public Double getAverageAgeStudents() {
        logger.info("Метод getAverageAgeStudents был вызван");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFiveStudents() {
        logger.info("Метод getLastFiveStudents был вызван");
        return studentRepository.getLastFiveStudents();
    }
    public List<Student> getStudentByName(String name) {
        logger.info("Метод getStudentByName был вызван");
        return studentRepository.getStudentByName(name);
    }
}