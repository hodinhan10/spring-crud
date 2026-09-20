package vn.hoidanit.springsieutoc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.model.Student;

@Service
public class StudentService {

    private final FacultyService facultyService;

    public StudentService(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    public List<Student> fetchStudents() {
        return facultyService.getAllStudents();
    }

    public Optional<Student> fetchStudentById(Long id) {
        return facultyService.getStudentById(id);
    }

    public Optional<Student> createStudent(Long facultyId, Student student) {
        return facultyService.createStudent(facultyId, student);
    }

    public boolean updateStudent(Student student) {
        return facultyService.updateStudent(student);
    }

    public boolean deleteStudent(Long id) {
        return facultyService.deleteStudent(id);
    }
}