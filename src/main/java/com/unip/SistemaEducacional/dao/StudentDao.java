package com.unip.SistemaEducacional.dao;

import com.unip.SistemaEducacional.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Integer id);
    Student createStudent(Student student);
    Optional<Student> updateStudent(Integer id, Student student);
    Optional<Student> getStudentByCpf(String cpf);
    Optional<Student> updateStudentName(Integer id, String nome, String lastName);
    Optional<Student> updateStudentAge(Integer id, Integer age);
    Optional<Student> updateStudentSex(Integer id, String sex);
    Optional<Student> updateStudentPhone(Integer id, String phone);
    Optional<Student> updateStudentAddress(Integer id, String address);
    Optional<Void> deleteStudent(Integer id);
}