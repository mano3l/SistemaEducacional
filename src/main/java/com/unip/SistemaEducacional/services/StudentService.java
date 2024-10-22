package com.unip.SistemaEducacional.services;

import com.unip.SistemaEducacional.models.Student;
import com.unip.SistemaEducacional.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Optional<Student> updateStudent(Integer id, Student student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setCpf(student.getCpf());
                    existingStudent.setNome(student.getNome());
                    existingStudent.setSobrenome(student.getSobrenome());
                    existingStudent.setIdade(student.getIdade());
                    existingStudent.setSexo(student.getSexo());
                    existingStudent.setTelefone(student.getTelefone());
                    return studentRepository.save(existingStudent);
                });
    }

    public Optional<Student> getStudentByCpf(String cpf) {
        return studentRepository.findByCpf(cpf);
    }

    public Optional<Student> updateStudentName(Integer id, String nome, String sobrenome) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setNome(nome);
                    existingStudent.setSobrenome(sobrenome);
                    return studentRepository.save(existingStudent);
                });
    }

    public Optional<Student> updateStudentIdade(Integer id, Integer idade) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setIdade(idade);
                    return studentRepository.save(existingStudent);
                });
    }

    public Optional<Student> updateStudentSexo(Integer id, String sexo) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setSexo(sexo);
                    return studentRepository.save(existingStudent);
                });
    }

    public Optional<Student> updateStudentTelefone(Integer id, String telefone) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setTelefone(telefone);
                    return studentRepository.save(existingStudent);
                });
    }

    public Optional<Student> updateStudentEndereco(Integer id, String endereco) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setEndereco(endereco);
                    return studentRepository.save(existingStudent);
                });
    }

    public Optional<Void> deleteStudent(Integer id) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    studentRepository.delete(existingStudent);
                    return null;
                });
    }
}