package cgg.spring_security.spring_security.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.spring_security.spring_security.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

    public User findByUsername(String username);

}
