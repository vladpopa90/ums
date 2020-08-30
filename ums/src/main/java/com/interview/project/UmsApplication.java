package com.interview.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UmsApplication.class, args);
	}

//	private UserMapper userMapper;
//
//	public UmsApplication(UserMapper userMapper) {
//		this.userMapper = userMapper;
//	}

//	@Bean
//	CommandLineRunner sampleCommandLineRunner() {
//		return args -> {
//			User user = new User();
//			user.setFirstName("Andi");
//			user.setLastName("Popa");
//			user.setEmail("andi_popa@test.com");
//			try {
//				userMapper.insertUser(user);
//				System.out.println(this.userMapper.findUserById(user.getId()));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		};
//	}

}
