package com.learnspring.boot.restapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsCLR implements CommandLineRunner{
	
	public UserDetailsCLR(UserDetailsRepository repository) {
		super();
		this.repository = repository;
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private UserDetailsRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new UserDetails("Dawn", "Vaigarai"));
	}

}
