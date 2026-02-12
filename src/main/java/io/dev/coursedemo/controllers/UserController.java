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

import io.dev.coursedemo.model.User;
import io.dev.coursedemo.service.UserService;

@Controller
public class UserController {

	private static final String home_page = "/courseapp";
	
	@Autowired
    private UserService userService;


	@GetMapping(home_page + "/users")
    public String viewUserPage(Model model) {
		model.addAttribute("activePage", "users");
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping(home_page + "/user/new")
    public String showNewUserForm(Model model) {
    	model.addAttribute("activePage", "users");
        User user = new User();
        model.addAttribute("user", user);
        return "user_new";
    }

    @GetMapping(home_page + "/user/edit/{id}")
    public String showUserEdit(@PathVariable( value = "id") long id, Model model) {
    	model.addAttribute("activePage", "users");
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @PostMapping(home_page + "/user/save")
    public String saveUser(@ModelAttribute("user") User user) {
        // save User to database
        userService.saveUser(user);
        return "redirect:" + home_page + "/users";
    }

    @GetMapping(home_page + "/user/delete/{id}")
    public String deleteUser(@PathVariable (value = "id") long id) {

        this.userService.deleteUserById(id);
        return "redirect:" + home_page + "/users";
    }


    @GetMapping(home_page + "/users/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortOrder") String sortOrder,
                                Model model) {
        int pageSize = 5;

        Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortOrder);
        List<User> listUsers = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equals("asc") ? "desc" : "asc");

        model.addAttribute("listUsers", listUsers);
        return "user_list";
    }
}
