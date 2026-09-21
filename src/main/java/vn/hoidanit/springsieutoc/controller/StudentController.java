package vn.hoidanit.springsieutoc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import vn.hoidanit.springsieutoc.model.Student;
import vn.hoidanit.springsieutoc.service.FacultyService;

@Controller
@RequestMapping("/student")
public class StudentController {

    private final FacultyService facultyService;

    public StudentController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public String listStudents(@RequestParam Long facultyId, Model model) {
        List<Student> students = facultyService.getStudentsByFacultyId(facultyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("students", students);
        return "Student/list";
    }

    @GetMapping("/create")
    public String createStudentPage(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "Student/CreateOrEdit";
    }

    @PostMapping("/create")
    public String createStudent(@ModelAttribute Student student, @RequestParam Long facultyId) {
        facultyService.createStudent(facultyId, student)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return "redirect:/student";
    }

    @GetMapping("/{id}/edit")
    public String editStudentPage(@PathVariable Long id, Model model) {
        Student student = facultyService.getStudentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("student", student);
        model.addAttribute("faculties", facultyService.getAllFaculties());
        return "Student/CreateOrEdit";
    }

    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {
        if (!facultyService.updateStudent(student)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/student";
    }

    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        if (!facultyService.deleteStudent(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/student";
    }
}