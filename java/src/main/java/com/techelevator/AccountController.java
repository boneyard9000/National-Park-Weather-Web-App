package com.techelevator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.dao.model.User;

@Controller
public class AccountController {
    @Autowired
    private AuthProvider auth;
    
    @GetMapping(path="/login")
    public String login(ModelMap modelHolder) {
        return "login";
    }

    @PostMapping(path="/login")
    public String login(
        @RequestParam String username,
        @RequestParam String password,
        RedirectAttributes flash
    ) {
        if(auth.signIn(username, password) == false) {
        	flash.addFlashAttribute("message", "Login Invalid");
            return "redirect:/login";
            
        }
        return "redirect:/";
    }

    @PostMapping(path="/logout")
    public String processLogout() {
        auth.logOut();
        return "redirect:/";
    }

    @GetMapping(path="/register")
    public String register(ModelMap modelHolder) {
        if( ! modelHolder.containsAttribute("user")){
            modelHolder.put("user", new User());
        }
        return "register";
    }

    @PostMapping(path="/register")
    public String register(
        @Valid @ModelAttribute("user") User user,
        BindingResult result,
        RedirectAttributes flash
    ) {
        if(result.hasErrors()) {
            flash.addFlashAttribute("user", user);
            flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "user", result);
            flash.addFlashAttribute("message", "Please fix the following errors:");
            return "redirect:/register";
        }
        auth.register(user.getUsername(), user.getPassword());
        return "redirect:/login";
    }


}
