package com.nadasproject.restspringmongo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> fetchAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public void deleteStudent(@PathVariable("id") String id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{id}")
    public void updateStudent(@PathVariable("id") String id,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String email) {
        studentService.updateStudent(id, firstName, lastName, email);
    }

}
