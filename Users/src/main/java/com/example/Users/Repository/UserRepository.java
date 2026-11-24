package com.example.Users.Repository;

import com.example.Users.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData,Long> {

    @Override
    boolean existsById(Long id);
    boolean existsByEmail(String email);
    Optional<UserData> findById(Long id);


}
