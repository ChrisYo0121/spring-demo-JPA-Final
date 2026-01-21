package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Classroom;
import fcu.pbiecs.springdemo.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    public List<Classroom> findAll() {
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findById(Integer id) {
        return classroomRepository.findById(id);
    }

    public Classroom save(Classroom classroom) {
        return classroomRepository.save(classroom);
    }

    public void deleteById(Integer id) {
        classroomRepository.deleteById(id);
    }
}
