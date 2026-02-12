package io.dev.coursedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.dev.coursedemo.model.Course;
import io.dev.coursedemo.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
    private CourseRepository courseRepository;
	
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Page<Course> findPaginated(int pageNum, int pageSize, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.courseRepository.findAll(pageable);
    }

    public Course getCourseById(long id) {
    	return courseRepository.findById(id).orElse(null);
    }

    public void saveCourse(Course course) {
        this.courseRepository.save(course);
    }

    public void deleteCourseById(long id) {
        this.courseRepository.deleteById(id);
    }

}
