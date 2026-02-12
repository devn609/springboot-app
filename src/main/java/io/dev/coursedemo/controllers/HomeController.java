package io.dev.coursedemo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	private static final String home_page = "/courseapp";

	@GetMapping("/")
    public String index(Model model) {
        model.addAttribute("activePage", "home");
        return "index";
    }

	@GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("activePage", "home"); // Highlight 'Home' in navbar
        return "home";
    }

	@GetMapping(home_page)
    public String viewCourseApp() {
        return "courseapp";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("activePage", "admin");
        return "admin";
    }
    @GetMapping("/profile")
    public String about(Model model) {
        model.addAttribute("activePage", "profile");
        return "profile";
    }


}
