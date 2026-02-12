package io.dev.coursedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.dev.coursedemo.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

}
