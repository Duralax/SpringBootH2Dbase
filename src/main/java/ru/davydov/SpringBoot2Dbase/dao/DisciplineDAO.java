package ru.davydov.SpringBoot2Dbase.dao;

import org.springframework.stereotype.Repository;
import ru.davydov.SpringBoot2Dbase.entity.Discipline;
import ru.davydov.SpringBoot2Dbase.entity.Student;

import java.util.List;

@Repository
public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();

    Discipline saveDiscipline(Discipline discipline);

    Discipline getDiscipline(int id);

    void deleteDiscipline(int id);

}
