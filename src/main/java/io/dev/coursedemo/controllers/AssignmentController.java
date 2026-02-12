package io.dev.coursedemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.dev.coursedemo.model.Assignment;
import io.dev.coursedemo.model.Course;
import io.dev.coursedemo.service.AssignmentService;
import io.dev.coursedemo.service.CourseService;

@Controller
public class AssignmentController {

	private static final String home_page = "/courseapp";
	
	@Autowired
    private AssignmentService assignmentService;

	@Autowired
    private CourseService courseService;

	@GetMapping(home_page + "/assignments")
    public String viewAssignmentPage(Model model) {
		model.addAttribute("activePage", "assignments");
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping(home_page + "/assignment/new")
    public String showNewAssignmentForm(Model model) {
    	model.addAttribute("activePage", "assignments");
        Assignment assignment = new Assignment();
        model.addAttribute("assignment", assignment);
        
        List<Course> listCourses = courseService.getAllCourses();
        model.addAttribute("listCourses", listCourses);
        
        return "assignment_new";
    }

    @GetMapping(home_page + "/assignment/edit/{id}")
    public String showAssignmentEdit(@PathVariable( value = "id") long id, Model model) {
    	model.addAttribute("activePage", "assignments");
        Assignment assignment = assignmentService.getAssignmentById(id);
        model.addAttribute("assignment", assignment);

        List<Course> listCourses = courseService.getAllCourses();
        model.addAttribute("listCourses", listCourses);
        
        return "assignment_edit";
    }

    @PostMapping(home_page + "/assignment/save")
    public String saveAssignment(@ModelAttribute("assignment") Assignment assignment) {
        // save Assignment to database
        assignmentService.saveAssignment(assignment);
        return "redirect:" + home_page + "/assignments";
    }

    @GetMapping(home_page + "/assignment/delete/{id}")
    public String deleteAssignment(@PathVariable (value = "id") long id) {

        this.assignmentService.deleteAssignmentById(id);
        return "redirect:" + home_page + "/assignments";
    }


    @GetMapping(home_page + "/assignments/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortOrder") String sortOrder,
                                Model model) {
        int pageSize = 5;

        Page<Assignment> page = assignmentService.findPaginated(pageNo, pageSize, sortField, sortOrder);
        List<Assignment> listAssignments = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equals("asc") ? "desc" : "asc");

        model.addAttribute("listAssignments", listAssignments);
        return "assignment_list";
    }
}
