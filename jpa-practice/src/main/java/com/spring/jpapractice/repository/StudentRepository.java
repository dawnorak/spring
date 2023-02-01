package com.spring.jpapractice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jpapractice.entity.Student;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	public List<Student> findByFirstName(String firstName);

	public List<Student> findByFirstNameContaining(String name);

	public List<Student> findByGuardianName(String guardianName);

	public List<Student> findByLastNameNotNull();

	@Query("select s from Student s where s.email= ?1")
	public Student getStudentsByEmailAddress(String emailId);

	// Java Persistence Query Language
	@Query("select s.firstName from Student s where s.email= ?1")
	public String getStudentsNameByEmailAddress(String emailId);

	// Native Query
	@Query(
			value = "SELECT * FROM tbl_student s where s.email_address = ?1",
			nativeQuery = true
	)
	public Student getStudentsByEmailAddressNative(String emailId);

	@Query(
			value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
			nativeQuery = true
	)
	public Student getStudentsByEmailAddressNativeNamedParam(
			@Param("emailId") String emailId);

	@Modifying
	@Transactional
	@Query(
			value = "update tbl_student set first_name = ?1 where email_address = ?2",
			nativeQuery = true
	)
	public int updateStudentNameByEmailId(String firstName, String emailId);

}
