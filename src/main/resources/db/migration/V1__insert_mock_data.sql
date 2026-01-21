-- Clear existing data before inserting mock data
DELETE FROM enrollment;
DELETE FROM courseschedule;
DELETE FROM users;
DELETE FROM course;
DELETE FROM student;
DELETE FROM teacher;
DELETE FROM classroom;

-- Insert Mock Data for Teachers (Explicit IDs)
INSERT INTO teacher (teacher_id, name, email, age) VALUES 
(1, '王大明', 'wang@fcu.edu.tw', '45'),
(2, '林小美', 'lin@fcu.edu.tw', '38'),
(3, '陳志豪', 'chen@fcu.edu.tw', '50');

-- Insert Mock Data for Students (Explicit IDs)
INSERT INTO student (student_id, first_name, last_name, email, date_of_birth) VALUES 
(1, 'Chris', 'Yu', 'chengchun121@gmail.com', '1995-01-21'),
(2, 'Alice', 'Chen', 'alice@example.com', '2000-05-15'),
(3, 'Bob', 'Chang', 'bob@example.com', '2001-08-20');

-- Insert Mock Data for Classrooms (Explicit IDs)
INSERT INTO classroom (classroom_id, classroom_name, capacity) VALUES 
(1, '資電 101', 50),
(2, '商學 205', 40),
(3, '理學 303', 30);

-- Insert Mock Data for Users (Authentication)
INSERT INTO users (user_id, username, password_hash, role, student_id, teacher_id, created_at) VALUES 
(1, 'student_chris', 'password123', 'STUDENT', 1, NULL, NOW()),
(2, 'teacher_wang', 'password123', 'TEACHER', NULL, 1, NOW()),
(3, 'student_alice', 'password123', 'STUDENT', 2, NULL, NOW());

-- Insert Mock Data for Courses (Explicit IDs)
INSERT INTO course (course_id, course_name, course_description, credits, teacher_id) VALUES 
(1, 'Java 程式設計', '深入淺出 Java 語言與物件導向觀念', 3, 1),
(2, '資料庫系統', '關聯式資料庫設計與 SQL 應用', 3, 2),
(3, '網頁前端開發', 'HTML, CSS, JavaScript 與 Vue.js 實戰', 2, 1),
(4, '演算法', '演算法分析與設計', 3, 3);

-- Insert Mock Data for Course Schedules (Explicit IDs)
INSERT INTO courseschedule (schedule_id, course_id, teacher_id, classroom_id, day_of_week, start_time, end_time) VALUES 
(1, 1, 1, 1, 'Monday', '09:00:00', '12:00:00'),
(2, 2, 2, 2, 'Tuesday', '13:00:00', '16:00:00'),
(3, 3, 1, 1, 'Wednesday', '10:00:00', '12:00:00'),
(4, 4, 3, 3, 'Thursday', '14:00:00', '17:00:00');

-- Insert Mock Data for Enrollments
INSERT INTO enrollment (student_id, course_id, enrollment_date, grade) VALUES 
(1, 1, '2023-09-01', 'A'),
(1, 2, '2023-09-01', 'B+'),
(2, 1, '2023-09-02', 'A-'),
(3, 3, '2023-09-03', 'B');

-- Reset Sequences to ensure next auto-generated ID is correct (Start from max ID + 1)
-- Note: Sequence names might vary, usually table_column_seq
SELECT setval(pg_get_serial_sequence('teacher', 'teacher_id'), (SELECT MAX(teacher_id) FROM teacher));
SELECT setval(pg_get_serial_sequence('student', 'student_id'), (SELECT MAX(student_id) FROM student));
SELECT setval(pg_get_serial_sequence('classroom', 'classroom_id'), (SELECT MAX(classroom_id) FROM classroom));
SELECT setval(pg_get_serial_sequence('users', 'user_id'), (SELECT MAX(user_id) FROM users));
SELECT setval(pg_get_serial_sequence('course', 'course_id'), (SELECT MAX(course_id) FROM course));
SELECT setval(pg_get_serial_sequence('courseschedule', 'schedule_id'), (SELECT MAX(schedule_id) FROM courseschedule));