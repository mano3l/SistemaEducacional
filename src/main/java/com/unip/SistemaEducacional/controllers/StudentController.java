package com.unip.SistemaEducacional.controllers;

import com.unip.SistemaEducacional.models.Student;
import com.unip.SistemaEducacional.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return studentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    existingStudent.setCpf(student.getCpf());
                    existingStudent.setNome(student.getNome());
                    existingStudent.setSobrenome(student.getSobrenome());
                    existingStudent.setIdade(student.getIdade());
                    existingStudent.setSexo(student.getSexo());
                    existingStudent.setTelefone(student.getTelefone());
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Student> getStudentByCpf(@PathVariable String cpf) {
        return studentRepository.findByCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}/name")
    public ResponseEntity<Student> updateStudentName(@PathVariable Integer id, @RequestBody Map<String, String> updates) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    if (updates.containsKey("nome")) {
                        existingStudent.setNome(updates.get("nome"));
                    }
                    // If you want to update the surname too, you can add:
                    if (updates.containsKey("sobrenome")) {
                        existingStudent.setSobrenome(updates.get("sobrenome"));
                    }
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/idade")
    public ResponseEntity<Student> updateStudentIdade(@PathVariable Integer id, @RequestBody Map<String, Integer> updates) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    if (updates.containsKey("idade")) {
                        existingStudent.setIdade(updates.get("idade"));
                    }
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PatchMapping("/{id}/sexo")
    public ResponseEntity<Student> updateStudentSexo(@PathVariable Integer id, @RequestBody Map<String, String> updates) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    if (updates.containsKey("sexo")) {
                        existingStudent.setSexo(updates.get("sexo"));
                    }
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/telefone")
    public ResponseEntity<Student> updateStudentTelefone(@PathVariable Integer id, @RequestBody Map<String, String> updates) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    if (updates.containsKey("telefone")) {
                        existingStudent.setTelefone(updates.get("telefone"));
                    }
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/endereco")
    public ResponseEntity<Student> updateStudentEndereco(@PathVariable Integer id, @RequestBody Map<String, String> updates) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    if (updates.containsKey("endereco")) {
                        existingStudent.setEndereco(updates.get("endereco"));
                    }
                    Student updatedStudent = studentRepository.save(existingStudent);
                    return ResponseEntity.ok(updatedStudent);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteStudent(@PathVariable Integer id) {
        return studentRepository.findById(id)
                .map(existingStudent -> {
                    studentRepository.delete(existingStudent);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}

