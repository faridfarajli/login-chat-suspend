package chatproject.controller;

import chatproject.dto.RegisterDto;
import chatproject.model.User;
import chatproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("user", registerDto);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") RegisterDto user, BindingResult result, Model model) {
        User existingUserMail = userService.findByEmail(user.getEmail());
        if (existingUserMail != null && existingUserMail.getEmail() != null && !existingUserMail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        User existingUserUsername = userService.findByUsername(user.getUsername());
        if (existingUserUsername !=null && existingUserUsername.getUsername() !=null && !existingUserUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if (result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
       return "redirect:/login";
    }
}

