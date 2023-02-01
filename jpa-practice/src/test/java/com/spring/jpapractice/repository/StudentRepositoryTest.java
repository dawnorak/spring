package com.spring.jpapractice.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.jpapractice.entity.Guardian;
import com.spring.jpapractice.entity.Student;

@SpringBootTest
class StudentRepositoryTest {

	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	public void saveStudent() {
		Student student = Student.builder()
				.email("sathivaigarai13@gmail.com")
				.firstName("Vaigarai")
				.lastName("Sathi")
				//.guardianName("PMS")
				//.guardianEmail("pms@gmail.com")
				//.guardianMobile("9999999999")
				.build();
		
		studentRepository.save(student);
	}
	
	@Test
	public void saveStudentWithGuardian() {
		Guardian guardian = Guardian.builder()
				.name("XYZ")
				.email("xyz@gmail.com")
				.mobile("8888888888")
				.build();
		
		Student student = Student.builder()
				.email("guna311@gmail.com")
				.firstName("Gunavardhan")
				.lastName("Sai")
				.guardian(guardian)
				.build();
		
		studentRepository.save(student);
	}
	
	@Test
    public void printAllStudent() {
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println("studentList = " + studentList);
    }
	
	@Test
    public void printStudentByFirstName() {
        
        List<Student> students =
                studentRepository.findByFirstName("Vaigarai");

        System.out.println("students = " + students);
    }

	@Test
	public void printStudentByFirstNameContaining() {

		List<Student> students =
				studentRepository.findByFirstNameContaining("Vai");

		System.out.println("students = " + students);
	}

	@Test
	public void printStudentBasedOnGuardianName() {

		List<Student> students =
				studentRepository.findByGuardianName("PMS");

		System.out.println("students = " + students);
	}

	@Test
	public void printGetStudentsByEmailAddress() {
		Student student =
				studentRepository.getStudentsByEmailAddress("sathivaigarai13@gmail.com");

		System.out.println("student = "+ student);
	}

	@Test
	public void printGetStudentsNameByEmailAddress() {
		String firstName =
				studentRepository.getStudentsNameByEmailAddress("guna311@gmail.com");

		System.out.println("student name = "+ firstName);
	}

	@Test
	public void printGetStudentsByEmailAddressNative() {
		Student student =
				studentRepository.getStudentsByEmailAddressNative("sathivaigarai13@gmail.com");

		System.out.println("student = "+ student);
	}

	@Test
	public void printGetStudentsByEmailAddressNativeNamedParam() {
		Student student =
				studentRepository.getStudentsByEmailAddressNativeNamedParam("sathivaigarai13@gmail.com");

		System.out.println("student = "+ student);
	}

	@Test
	public void updateStudentNameByEmailIdTest(){
		studentRepository.updateStudentNameByEmailId(
				"Gunavardhan", "gunnaa311@gmail.com");
	}

}
