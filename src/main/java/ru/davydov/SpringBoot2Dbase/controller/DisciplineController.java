package ru.davydov.SpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.davydov.SpringBoot2Dbase.entity.Discipline;
import ru.davydov.SpringBoot2Dbase.model.Response;
import ru.davydov.SpringBoot2Dbase.service.DisciplineService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public ResponseEntity<Response> allDisciplines(){
        List<Discipline> disciplinesAll = disciplineService.getAllDisciplines();
        if (!disciplinesAll.isEmpty()){
            return ResponseEntity.ok(Response.success(disciplinesAll, "Список дисциплин получен"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Response.error(null, "Списка дисциплин нет"));
        }
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<Response> getDiscipline(@PathVariable("id") int id){
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline != null){
            return ResponseEntity.ok(Response.success(discipline, "Получена дисциплина с id: " + id));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(null, "Дисциплина с id: " + id + " не найдена"));
    }

    @PostMapping("/disciplines")
    public ResponseEntity<Response> saveDiscipline(@RequestBody Discipline discipline){
        Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
        return ResponseEntity.ok(Response.success(savedDiscipline, "Дисциплина успешно добавлена с id: " + savedDiscipline.getId()));
    }

    @PutMapping("/disciplines")
    public ResponseEntity<Response> updateDiscipline(@RequestBody Discipline discipline){
        if (disciplineService.getDiscipline(discipline.getId()) != null) {
            Discipline updated = disciplineService.saveDiscipline(discipline);
            return ResponseEntity.ok(Response.success(updated, "Дисциплина с id: " + discipline.getId() + " успешно обновлена"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(null, "Нельзя обновить: дисциплина с id " + discipline.getId() + " не найдена"));
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<Response> deleteDiscipline(@PathVariable("id") int id) {
        if (disciplineService.getDiscipline(id) != null) {
            disciplineService.deleteDiscipline(id);
            return ResponseEntity.ok(Response.success(null, "Дисциплина с id " + id + " успешно удалена"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Response.error(null, "Нельзя удалить: дисциплина с id " + id + " не найдена"));
    }
}
