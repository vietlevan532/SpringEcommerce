package com.mid_term.springecommerce.Controllers;

import com.mid_term.springecommerce.DTO.UserDTO;
import com.mid_term.springecommerce.Models.Role;
import com.mid_term.springecommerce.Models.User;
import com.mid_term.springecommerce.Repositorys.RoleRepository;
import com.mid_term.springecommerce.Repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String renderLogin(Model model) {
        model.addAttribute("UserDTO", new User());
        return "login";
    }

    @GetMapping("/register")
    public String renderRegister() {
        return "register";
    }

}
