package vn.hoidanit.springsieutoc.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.springsieutoc.model.Faculty;
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

}
