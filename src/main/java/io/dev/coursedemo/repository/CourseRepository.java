package io.dev.coursedemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.dev.coursedemo.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}
