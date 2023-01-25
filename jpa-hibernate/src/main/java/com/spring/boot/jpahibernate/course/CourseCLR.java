package com.spring.boot.jpahibernate.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.spring.boot.jpahibernate.course.jdbc.CourseJdbcRepository;
import com.spring.boot.jpahibernate.course.jpa.CourseJpaRepository;

@Component
public class CourseCLR implements CommandLineRunner{
	
	// @Autowired
	// private CourseJdbcRepository repository;
	
	// @Autowired
	// private CourseJpaRepository repository;
	
	@Autowired
	private CourseSpringDataJpaRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Course(1, "Learn Spring", "Udemy"));
		repository.save(new Course(2, "Learn Java", "Udemy"));
		repository.save(new Course(3, "Learn SQL", "Udemy"));
		
		System.out.println(repository.findById(2l));
		
		System.out.println(repository.findAll());
		
		System.out.println(repository.findByName("Learn SQL"));
		
	}
	
}
