package ru.davydov.SpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.davydov.SpringBoot2Dbase.entity.Student;
import ru.davydov.SpringBoot2Dbase.model.Response;
import ru.davydov.SpringBoot2Dbase.service.StudentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<Response> allStudents(){
        List<Student> studentsAll = studentService.getAllStudents();
        if (!studentsAll.isEmpty()){
            return ResponseEntity.ok(Response.success(studentsAll, "Список студентов получен"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.error(null, "Списка студентов нет"));
        }
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Response> getStudent(@PathVariable("id") int id){
        Student student = studentService.getStudent(id);
        if (student != null){
            return ResponseEntity.ok(Response.success(student, "Получен студент с id: " + id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(null, "Студент с id: " + id + " не найден"));
    }

    @PostMapping("/students")
    public ResponseEntity<Response> saveStudent(@RequestBody Student student){
        Student savedStudent =  studentService.saveStudent(student);
        return ResponseEntity.ok(Response.success(savedStudent, "Студент успешно добавлен с id: " + savedStudent.getId()));
    }

    @PutMapping("/students")
    public ResponseEntity<Response> updateStudent(@RequestBody Student student){
        if (studentService.getStudent(student.getId()) != null) {
            Student updated = studentService.saveStudent(student);
            return ResponseEntity.ok(Response.success(updated, "Студент с id: " + student.getId() + " успешно обновлен"));

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(null, "Нельзя обновить: студент с id " + student.getId() + " не найден"));
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Response> deleteStudent(@PathVariable("id") int id) {
        if (studentService.getStudent(id) != null) {
            studentService.deleteStudent(id);
            return ResponseEntity.ok(Response.success(null, "Студент с id " + id + " успешно удален"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(null, "Нельзя удалить: студент с id " + id + " не найден"));
    }
}
