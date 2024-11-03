package com.unip.SistemaEducacional.dao;

import com.unip.SistemaEducacional.models.Student;
import com.unip.SistemaEducacional.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentDaoImpl {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentDaoImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // SELECT * FROM Student
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // SELECT * FROM Student WHERE id = ?
    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    // INSERT INTO Student (cpf, lastName, age, sex, phone) VALUES (?, ?, ?, ?, ?)
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // UPDATE Student SET cpf = ?, lastName = ?, age = ?, sex = ?, phone = ? WHERE id = ?
    public Optional<Student> updateStudent(Integer id, Student student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setCpf(student.getCpf());
                    existingStudent.setLastName(student.getLastName());
                    existingStudent.setName(student.getName());
                    existingStudent.setAge(student.getAge());
                    existingStudent.setSex(student.getSex());
                    existingStudent.setPhone(student.getPhone());
                    return studentRepository.save(existingStudent);
                });
    }

    // SELECT * FROM Student WHERE cpf = ?
    public Optional<Student> getStudentByCpf(String cpf) {
        return studentRepository.findByCpf(cpf);
    }

    // UPDATE Student SET name = ?, lastName = ? WHERE id = ?
    public Optional<Student> updateStudentName(Integer id, String nome, String lastName) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setName(nome);
                    existingStudent.setLastName(lastName);
                    return studentRepository.save(existingStudent);
                });
    }

    // UPDATE Student SET age = ? WHERE id = ?
    public Optional<Student> updateStudentAge(Integer id, Integer age) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setAge(age);
                    return studentRepository.save(existingStudent);
                });
    }

    // UPDATE Student SET sex = ? WHERE id = ?
    public Optional<Student> updateStudentSex(Integer id, String sex) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setSex(sex);
                    return studentRepository.save(existingStudent);
                });
    }

    // UPDATE Student SET phone = ? WHERE id = ?
    public Optional<Student> updateStudentPhone(Integer id, String phone) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setPhone(phone);
                    return studentRepository.save(existingStudent);
                });
    }

    // UPDATE Student SET address = ? WHERE id = ?
    public Optional<Student> updateStudentAddress(Integer id, String address) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setAddress(address);
                    return studentRepository.save(existingStudent);
                });
    }

    // DELETE FROM Student WHERE id = ?
    public Optional<Void> deleteStudent(Integer id) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    studentRepository.delete(existingStudent);
                    return null;
                });
    }
}