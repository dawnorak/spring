package com.spring.boot.mapstruct;

import lombok.Data;

@Data
public class EmployeeDto {
	
	private int id;
	private String fullName; // firstName + lastName

}
