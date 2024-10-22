package com.pruebastyt.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pruebastyt.app.entity.User;
import com.pruebastyt.app.exception.NotFoundException;
import com.pruebastyt.app.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "users")
public class UserWebController {

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute("user")
	public User userModel() {
		return new User();
	}

	@GetMapping("/")
    public String UserListTemplate(Model model, HttpSession session) {
        if (session.getAttribute("isUser") != null) {
            // Obtener el ID del usuario autenticado
            String userId = session.getAttribute("userId").toString();
            User user = userRepository.findById(userId).orElse(null);
            
            if (user != null) {
                model.addAttribute("user", user);
                return "home";
            }
        }
        
        return "index"; // Redirigir a la página de inicio si no se encuentra el usuario
    }
	}


