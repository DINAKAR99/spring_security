package cgg.spring_security.spring_security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cgg.spring_security.spring_security.entities.User;
import cgg.spring_security.spring_security.repo.UserRepo;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "library apis", version = "1.0", description = "lib manag apis"))
public class SpringSecurityApplication implements CommandLineRunner {

	@Autowired
	private UserRepo u1;
	@Autowired
	private PasswordEncoder p1;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		// User u2 = new User("din", p1.encode("add"), "d@g.com", "ROLE_NORMAL");

		// u1.save(u2);

	}

}
