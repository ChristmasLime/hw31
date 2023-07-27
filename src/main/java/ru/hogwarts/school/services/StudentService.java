package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.NoSuchElementException;


@Service
public class StudentService {

    @Autowired
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStud(Student student) {
        return studentRepository.save(student);
    }

    public Student findStud(long id) {

        return studentRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Студент с указанным id не найден"));
    }

    public Student editStud(Student student) {
        return studentRepository.save(student);
    }


    public void deleteStud(long id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAllStud() {
        return studentRepository.findAll();
    }

    public Collection<Student> getStudByAge(int age) {
        return studentRepository.findByAge(age);
    }
}
