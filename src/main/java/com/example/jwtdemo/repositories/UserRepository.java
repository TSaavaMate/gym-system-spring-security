package com.example.jwtdemo.repositories;

import com.example.jwtdemo.entities.User;
import com.example.jwtdemo.utils.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    Boolean existsUserByUsername(String username);

    Optional<User> findByFirstNameAndLastName(String firstName,String lastName);

    List<User> findUsersByStatus(UserStatus status);
}
