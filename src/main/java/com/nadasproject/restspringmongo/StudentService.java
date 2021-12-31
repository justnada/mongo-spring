package com.nadasproject.restspringmongo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException(
                    "Email has already taken."
            );
        }
        studentRepository.save(student);
    }

    public void deleteStudent(String id) {
        boolean isExist = studentRepository.existsById(id);

        if (!isExist) {
            throw new IllegalStateException(
                    "Student with id " + id + " does not exist"
            );
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(String id,
                              String firstName,
                              String lastName,
                              String email) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(
                        "Student with id " + id + " does not exist."
                ));

        if (firstName != null
                && firstName.length() > 0
                && !Objects.equals(student.getFirstName(), firstName)) {
            student.setFirstName(firstName);
        }

        if (lastName != null
                && lastName.length() > 0
                && !Objects.equals(student.getLastName(), lastName)) {
            student.setLastName(lastName);
        }

        if (email != null
                && email.length() > 0
                && !Objects.equals(student.getEmail(), email)) {

            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

            if (studentByEmail.isPresent()) {
                throw new IllegalStateException(
                        "Email has already taken."
                );
            }
            student.setEmail(email);
        }
    }

}
