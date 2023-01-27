package com.spring.boot.mapstruct;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {
	
	private int id;
	private String firstName;
	private String lastName;

}
