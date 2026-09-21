package vn.hoidanit.springsieutoc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.springsieutoc.model.Faculty;
import vn.hoidanit.springsieutoc.model.Student;
import vn.hoidanit.springsieutoc.service.FacultyService;

@RestController
@RequestMapping("/api/faculties")
public class FacultyRestController {

    private final FacultyService facultyService;

    public FacultyRestController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public List<Faculty> getFaculties() {
        return facultyService.getAllFaculties();
    }

    @GetMapping("/{facultyId}/students")
    public ResponseEntity<List<Student>> getStudentsByFaculty(
            @PathVariable Long facultyId,
            @RequestParam(required = false) String keyword) {
        return facultyService.getStudentsByFacultyId(facultyId, keyword)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{facultyId}/students/{studentId}")
    public ResponseEntity<Student> getStudent(
            @PathVariable Long facultyId,
            @PathVariable Long studentId) {
        return facultyService.getStudentByFacultyId(facultyId, studentId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{facultyId}/students")
    public ResponseEntity<Student> createStudent(
            @PathVariable Long facultyId,
            @RequestBody Student student) {
        return facultyService.createStudent(facultyId, student)
                .map(createdStudent -> ResponseEntity.status(HttpStatus.CREATED).body(createdStudent))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{facultyId}/students/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long facultyId,
            @PathVariable Long studentId,
            @RequestBody Student updatedStudent) {
        if (!facultyService.updateStudent(facultyId, studentId, updatedStudent)) {
            return ResponseEntity.notFound().build();
        }
        updatedStudent.setId(studentId);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{facultyId}/students/{studentId}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long facultyId,
            @PathVariable Long studentId) {
        if (!facultyService.deleteStudent(facultyId, studentId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
