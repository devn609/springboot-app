package io.dev.coursedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.dev.coursedemo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
