package com.pruebastyt.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pruebastyt.app.entity.Admin;
import com.pruebastyt.app.entity.User;
import com.pruebastyt.app.repository.AdminRepository;
import com.pruebastyt.app.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class PrincipalController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("/login")
	public String loginAdm(Model model) {
		return "loginUA";
	}
	
	@PostMapping("/login")
	public String login(@RequestParam ("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
	    Admin admin = adminRepository.findByEmail(email);
	    User user = userRepository.findByEmail(email);

	    if (admin != null && admin.getPassword().equals(password)) {
	        // Si es un administrador, redirigir al panel de administrador
	        session.setAttribute("isAdmin", true);
	        session.setAttribute("isUser", false);
	        return "redirect:/administrador/";
	    } else if (user != null && user.getPassword().equals(password)) {
	        // Si es un usuario normal, redirigir a la página de usuario
	        session.setAttribute("isAdmin", false);
	        session.setAttribute("isUser", true);
	        session.setAttribute("userId", user.getId()); // Agregar el ID del usuario a la sesión
	        return "redirect:/users/";
	    } else {
	        session.setAttribute("msg", "Correo o contraseña incorrecta. Verifica por favor");
	        return "loginUA"; // Página de inicio de sesión general
	    }
	}

}
