package fcu.pbiecs.springdemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Classroom")
@Data
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_id")
    private Integer classroomId;

    @Column(name = "classroom_name", nullable = false)
    private String classroomName;

    @Column(name = "capacity")
    private Integer capacity;
}
