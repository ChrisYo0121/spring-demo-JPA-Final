package fcu.pbiecs.springdemo.repository;

import fcu.pbiecs.springdemo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    List<Teacher> findByName(String name);
    Teacher findByEmail(String email);
    List<Teacher> findByAge(String age);
}

