package ru.hogwarts.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;

import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.NoSuchElementException;


@Service
public class FacultyService {

    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFacul(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFacul(long id) {
        return facultyRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Факультет с указанным id не найден"));
    }

    public Faculty editFacul(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFacul(long id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAllFacul() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getFacultiesByColor(String color) {
        return facultyRepository.getFaculByColor(color);
    }

    public Collection<Faculty> getFacultiesByNameOrColor(String searchString) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(searchString, searchString);
    }


    public Collection<Student> getFacultyStudents(Long facultyId) {
        Faculty faculty = facultyRepository.findFacultyById(facultyId);
        if (faculty == null) {
            throw new NoSuchElementException("Факультет с указанным ID не найден");
        }
        return faculty.getStudents();
    }
}
