package ru.hogwarts.school5.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school5.model.Faculty;
import ru.hogwarts.school5.model.Student;
import ru.hogwarts.school5.service.StudentService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable("id") Long id) {
        return studentService.getById(id);
    }

    @GetMapping("/filtered")
    public Collection<Student> getByAge(@RequestParam("age") int age) {
        return studentService.getByAge(age);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Student>> getStudentByName(@PathVariable("name") String name) {
        List<Student> students = studentService.getStudentByName(name);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return studentService.create(student);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable("id") Long id, @RequestBody Student student) {
        return studentService.update(id, student);
    }

    // @DeleteMapping("/{id}")
    //public void delete(@PathVariable("id") Long id) {
    //    studentService.remove(id);
    // }
    @DeleteMapping("{id}")
    @Operation(summary = "Delete student by id")
    public void delStudent(@PathVariable Long id) {
        if (id != null)
            studentService.delete(id);
    }

    @GetMapping("/filteredByBetween")
    public Collection<Student> filtered(@RequestParam int min, @RequestParam int max) {
        return studentService.getByAgeBetween(min, max);
    }

    public Faculty getFacultyByStudent(@PathVariable Long id) {
        return studentService.getById(id).getFaculty();
    }

    @GetMapping("/count-students")
    public Integer getCountStudents() {
        return studentService.getCountStudents();
    }

    @GetMapping("/average-age-students")
    public Double getAverageAgeStudents() {
        return studentService.getAverageAgeStudents();
    }

    @GetMapping("/last-five-students")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/namesStartingWithA")
    public List<String> getStudentNamesStartingWithA() {
        return studentService.getStudentNamesStartingWithA();
    }

    @GetMapping("/averageAge")
    public Double getAverageStudentAge() {
        return studentService.getAverageStudentAge();
    }

    @GetMapping("/print-parallel")
    public void printAsynchronized() {
        studentService.printAsynchronized();
    }

    @GetMapping("/print-synchronized")
    public void printSynchronized() {
        studentService.printSynchronized();
    }

}