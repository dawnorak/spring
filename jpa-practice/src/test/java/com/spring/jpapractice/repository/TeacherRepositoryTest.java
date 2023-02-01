package com.spring.jpapractice.repository;

import com.spring.jpapractice.entity.Course;
import com.spring.jpapractice.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course courseJ = Course.builder()
                .title("Java Programming")
                .credit(4)
                .build();
        Course courseC = Course.builder()
                .title("C Programming")
                .credit(4)
                .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Amogh")
                        .lastName("Deshmukh")
                        //.courses(List.of(courseJ, courseC))
                        .build();

        teacherRepository.save(teacher);
    }

}