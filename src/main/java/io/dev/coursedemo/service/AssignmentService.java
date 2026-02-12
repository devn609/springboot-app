package io.dev.coursedemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.dev.coursedemo.model.Assignment;
import io.dev.coursedemo.repository.AssignmentRepository;

@Service
public class AssignmentService {

	@Autowired
    private AssignmentRepository assignmentRepository;
	
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Page<Assignment> findPaginated(int pageNum, int pageSize, String sortField, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return this.assignmentRepository.findAll(pageable);
    }

    public Assignment getAssignmentById(long id) {
    	return assignmentRepository.findById(id).orElse(null);
    }

    public void saveAssignment(Assignment assignment) {
        this.assignmentRepository.save(assignment);
    }

    public void deleteAssignmentById(long id) {
        this.assignmentRepository.deleteById(id);
    }

}
