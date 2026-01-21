package fcu.pbiecs.springdemo.service;

import fcu.pbiecs.springdemo.model.Student;
import fcu.pbiecs.springdemo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

//    @Autowired
//    DatabaseService dbService;

    @Autowired
    private StudentRepository studentRepository;



    public List<Student> getAllStudents() {
        // 使用 Spring Data JPA 的方式查詢所有學生
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        // 使用 Spring Data JPA 的方式查詢所有學生
        Optional<Student> optStudent = studentRepository.findById(id);
        if (optStudent.isPresent()) {
          return optStudent.get();
        }
        return null;
    }

    public Student createStudent(Student student) {
        // 使用 Spring Data JPA 的方式新增學生
        return studentRepository.save(student);
    }

public Student updateStudent(Student studentDetails) {
    // 使用 Spring Data JPA 的方式更新學生
    // studentId 是 int 型別，不能與 null 比較，因此移除 null 檢查
    if (studentRepository.existsById(studentDetails.getStudentId())) {
        return studentRepository.save(studentDetails);
    }
    return null;
}

public boolean deleteStudentById(Integer id) {
    // 使用 Spring Data JPA 的方式刪除學生
    if (studentRepository.existsById(id)) {
        studentRepository.deleteById(id);
        return true;
    }
    return false;
}

public List<Student> getStudentsByFirstName(String firstName) {
    // 使用 Spring Data JPA 的方式查詢學生
    return studentRepository.findByFirstName(firstName);
}

public List<Student> getStudentsByLastName(String lastName) {
    // 使用 Spring Data JPA 的方式查詢學生
    return studentRepository.findByLastName(lastName);
}


public Student getStudentByEmail(String email) {
    // 使用 Spring Data JPA 的方式查詢學生
        return studentRepository.findByEmail(email);
    }





//    public List<Student> getAllStudents() {
//        String sql = "SELECT * FROM Student";
//        List<Student> students = new ArrayList<>();
//        try (Connection conn = dbService.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql);
//             ResultSet rs = pstmt.executeQuery()) {
//            while (rs.next()) {
//                int studentId = rs.getInt("student_id");
//                String firstName = rs.getString("first_name");
//                String lastName = rs.getString("last_name");
//                String dateOfBirth = rs.getString("date_of_birth");
//                String email = rs.getString("email");
//                students.add(new Student(studentId, firstName, lastName, email, dateOfBirth));
//            }
//        } catch(SQLException exception) {
//            exception.printStackTrace();
//        }
//        return students;
//    }


//    public Student getStudentById(int id) {
//        String sql = "SELECT * FROM Student WHERE student_id = ?";
//        try (Connection conn = dbService.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, id);
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    int studentId = rs.getInt("student_id");
//                    String firstName = rs.getString("first_name");
//                    String lastName = rs.getString("last_name");
//                    String dateOfBirth = rs.getString("date_of_birth");
//                    String email = rs.getString("email");
//                    return new Student(studentId, firstName, lastName, email, dateOfBirth);
//                }
//            }
//        } catch(SQLException exception) {
//            exception.printStackTrace();
//        }
//        return null;
//    }
//
//    public Student createStudent(Student student) {
//        // 修正：改為回傳 Student (2025-08-20)
//        String sql = "INSERT INTO Student (first_name, last_name, email, date_of_birth) VALUES (?, ?, ?, ?)";
//        try (Connection conn = dbService.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
//            pstmt.setString(1, student.getFirstName());
//            pstmt.setString(2, student.getLastName());
//            pstmt.setString(3, student.getEmail());
//            pstmt.setString(4, student.getBirthday());
//            pstmt.executeUpdate();
//            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    student.setStudentId(generatedKeys.getInt(1));
//                }
//            }
//        } catch(SQLException exception) {
//            exception.printStackTrace();
//        }
//        return student;
//    }
//
//    public boolean deleteStudentById(int id) {
//        // 新增：刪除學生方法 (2025-08-20)
//        String sql = "DELETE FROM Student WHERE student_id = ?";
//        try (Connection conn = dbService.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setInt(1, id);
//            int affectedRows = pstmt.executeUpdate();
//            return affectedRows > 0;
//        } catch(SQLException exception) {
//            exception.printStackTrace();
//        }
//        return false;
//    }
//
//    public void updateStudent(Student student) {
//        String sql = "UPDATE Student SET first_name = ?, last_name = ?, email = ?, date_of_birth = ? WHERE student_id = ?";
//        try (Connection conn = dbService.connect();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, student.getFirstName());
//            pstmt.setString(2, student.getLastName());
//            pstmt.setString(3, student.getEmail());
//            pstmt.setString(4, student.getBirthday());
//            pstmt.setInt(5, student.getStudentId());
//            pstmt.executeUpdate();
//
//        } catch(SQLException exception) {
//            exception.printStackTrace();
//        }
//    }
}
