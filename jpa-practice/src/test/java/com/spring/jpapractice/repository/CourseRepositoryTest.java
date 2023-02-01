package com.spring.jpapractice.repository;

import com.spring.jpapractice.entity.Course;
import com.spring.jpapractice.entity.Student;
import com.spring.jpapractice.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courses =
                courseRepository.findAll();

        System.out.println("courses = "+ courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Daya")
                .lastName("Shankar")
                .build();

        Course course = Course.builder()
                .title("Python Programming")
                .credit(4)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords)
                        .getContent();

        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords)
                                .getTotalElements();

        long totalPages =
                courseRepository.findAll(secondPageWithTwoRecords)
                                .getTotalPages();

        System.out.println("courses = "+courses);
        System.out.println("total elements = "+totalElements);
        System.out.println("total pages =  "+totalPages);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle =
                PageRequest.of(0, 2, Sort.by("title"));

        Pageable sortByCreditDesc =
                PageRequest.of(0, 2, Sort.by("credit").descending());

        List<Course> courses = courseRepository.findAll(sortByTitle)
                .getContent();

        System.out.println("courses sorted by title: "+courses);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        List<Course> courses =
                courseRepository.findByTitleContaining("Programming",
                        firstPageTenRecords).getContent();

        System.out.println("courses = "+courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Kiran")
                .lastName("Ravulakollu")
                .build();

        Student student = Student.builder()
                .firstName("Kenny")
                .lastName("Joel")
                .email("kuragayala@gmail.com")
                .build();

        Course course = Course.builder()
                .title("Artificial Intelligence")
                .credit(3)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}