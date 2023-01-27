package com.spring.boot.mapstruct;

public class Main {
	
	private static EmployeeMapper mapper = new EmployeeMapperImpl();

	public static void main(String[] args) {
				
		Employee emp = Employee.builder()
				.id(1)
				.firstName("Dawn")
				.lastName("Vaigarai")
				.build();
		
		EmployeeDto employeeDto = mapper.toDto(emp);
		System.out.println(employeeDto);
	}

}
