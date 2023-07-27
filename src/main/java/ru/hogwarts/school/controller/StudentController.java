package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.services.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        if (student.getAge() > 0) {
            return ResponseEntity.ok(service.createStud(student));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.findStud(id));
    }

    @PutMapping()
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        if (student.getAge() > 0) {
            return ResponseEntity.ok(service.editStud(student));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id) {
        service.deleteStud(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStud());
    }

    @GetMapping("age/{age}")
    public ResponseEntity<Collection<Student>> getStudentByAge(@PathVariable int age) {
        if (age > 0) {
            return ResponseEntity.ok(service.getStudByAge(age));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("age")
    public ResponseEntity<Collection<Student>> getStudentsByAgeRange(
            @RequestParam int minAge,
            @RequestParam int maxAge) {
        if (minAge <= maxAge) {
            return ResponseEntity.ok(service.getStudByAgeBetween(minAge, maxAge));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("{id}/faculty")
    public ResponseEntity<Faculty> getStudentFaculty(@PathVariable Long id) {
        Faculty faculty = service.getStudentFaculty(id);
        return ResponseEntity.ok(faculty);
    }

}

