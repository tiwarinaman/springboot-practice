package com.practice.springpractice.service;

import com.practice.springpractice.model.Student;
import com.practice.springpractice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveNewStudent(Student student) {
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());

        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email taken");
        }

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean isExists = studentRepository.existsById(id);

        if (!isExists) {
            throw new IllegalStateException("No Student preset with id " + id);
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudentDetails(Long id, Student studentDetails) {
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new IllegalStateException("Student with id " + id + " does not exits"));

        if (studentDetails.getName() != null
                && studentDetails.getName().length() > 0
                && !Objects.equals(studentDetails.getName(), student.getName())) {
            student.setName(studentDetails.getName());
        }

        if (studentDetails.getEmail() != null
                && studentDetails.getEmail().length() > 0
                && !Objects.equals(studentDetails.getEmail(), student.getEmail())) {
            Optional<Student> studentOptional =
                    studentRepository.findStudentByEmail(studentDetails.getEmail());

            if (studentOptional.isPresent()) {
                throw new IllegalStateException("Email Taken");
            }
            student.setEmail(studentDetails.getEmail());
        }

        studentRepository.save(student);

    }
}
