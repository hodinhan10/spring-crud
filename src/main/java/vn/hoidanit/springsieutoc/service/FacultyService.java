package vn.hoidanit.springsieutoc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import org.springframework.stereotype.Service;

import vn.hoidanit.springsieutoc.model.Faculty;
import vn.hoidanit.springsieutoc.model.Student;

@Service
public class FacultyService {

    private final List<Faculty> faculties;

    public FacultyService() {
        List<Student> csStudents = new ArrayList<>(List.of(
                new Student(1L, "Alice Smith", "alice@example.com"),
            new Student(2L, "Bob Jones", "bob@example.com")));

        List<Student> engineeringStudents = new ArrayList<>(List.of(
                new Student(3L, "Charlie Brown", "charlie@example.com"),
            new Student(4L, "Diana Prince", "diana@example.com")));

        faculties = new ArrayList<>(List.of(
                new Faculty(1L, "Computer Science", csStudents),
            new Faculty(2L, "Engineering", engineeringStudents)));
    }

    public List<Faculty> getAllFaculties() {
        return faculties;
    }

    public Optional<Faculty> getFacultyById(Long id) {
        return faculties.stream()
                .filter(faculty -> faculty.getId().equals(id))
                .findFirst();
    }

    public List<Student> getAllStudents() {
        return faculties.stream()
                .flatMap(faculty -> faculty.getStudents().stream())
                .toList();
    }

    public Optional<List<Student>> getStudentsByFacultyId(Long facultyId, String keyword) {
        return getFacultyById(facultyId)
                .map(faculty -> filterStudents(faculty.getStudents(), keyword));
    }

    private List<Student> filterStudents(List<Student> students, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return students;
        }

        String searchKeyword = keyword.trim().toLowerCase(Locale.ROOT);
        return students.stream()
                .filter(student -> student.getName().toLowerCase(Locale.ROOT).contains(searchKeyword)
                        || student.getEmail().toLowerCase(Locale.ROOT).contains(searchKeyword))
                .toList();
    }

    public Optional<Student> getStudentByFacultyId(Long facultyId, Long studentId) {
        return getFacultyById(facultyId)
                .flatMap(faculty -> faculty.getStudents().stream()
                        .filter(student -> student.getId().equals(studentId))
                        .findFirst());
    }

    public Optional<Student> createStudent(Long facultyId, Student student) {
        return getFacultyById(facultyId).map(faculty -> {
            student.setId(getAllStudents().stream()
                    .mapToLong(existingStudent -> existingStudent.getId())
                    .max()
                    .orElse(0) + 1);
            faculty.getStudents().add(student);
            return student;
        });
    }

    public boolean updateStudent(Long facultyId, Long studentId, Student updatedStudent) {
        Optional<Student> existingStudent = getStudentByFacultyId(facultyId, studentId);
        if (existingStudent.isEmpty()) {
            return false;
        }

        Student student = existingStudent.get();
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        return true;
    }

    public boolean deleteStudent(Long facultyId, Long studentId) {
        return getFacultyById(facultyId)
                .map(faculty -> faculty.getStudents()
                        .removeIf(student -> student.getId().equals(studentId)))
                .orElse(false);
    }
}
