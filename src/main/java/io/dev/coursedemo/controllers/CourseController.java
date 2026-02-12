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

import io.dev.coursedemo.model.Course;
import io.dev.coursedemo.service.CourseService;

@Controller
public class CourseController {

	private static final String home_page = "/courseapp";
	
	@Autowired
    private CourseService courseService;


	@GetMapping(home_page + "/courses")
    public String viewCoursePage(Model model) {
		model.addAttribute("activePage", "courses");
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping(home_page + "/course/new")
    public String showNewCourseForm(Model model) {
    	model.addAttribute("activePage", "courses");
        Course course = new Course();
        model.addAttribute("course", course);
        return "course_new";
    }

    @GetMapping(home_page + "/course/edit/{id}")
    public String showCourseEdit(@PathVariable( value = "id") long id, Model model) {
    	model.addAttribute("activePage", "courses");
        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);
        return "course_edit";
    }

    @PostMapping(home_page + "/course/save")
    public String saveCourse(@ModelAttribute("course") Course course) {
        // save Course to database
        courseService.saveCourse(course);
        return "redirect:" + home_page + "/courses";
    }

    @GetMapping(home_page + "/course/delete/{id}")
    public String deleteCourse(@PathVariable (value = "id") long id) {

        this.courseService.deleteCourseById(id);
        return "redirect:" + home_page + "/courses";
    }


    @GetMapping(home_page + "/courses/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortOrder") String sortOrder,
                                Model model) {
        int pageSize = 5;

        Page<Course> page = courseService.findPaginated(pageNo, pageSize, sortField, sortOrder);
        List<Course> listCourses = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equals("asc") ? "desc" : "asc");

        model.addAttribute("listCourses", listCourses);
        return "course_list";
    }
}
