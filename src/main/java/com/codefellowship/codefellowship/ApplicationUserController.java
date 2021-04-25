package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String getSignUpPage() {
        return "signup.html";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @PostMapping("/signup")
    public RedirectView signup(@RequestParam(value = "username") String username,
                               @RequestParam(value = "password") String password,
                               @RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName,
                               @RequestParam(value = "dateOfBirth") String dateOfBirth,
                               @RequestParam(value = "bio") String bio) {
        ApplicationUserModel newUser = new ApplicationUserModel(username, passwordEncoder.encode(password), bio, dateOfBirth, lastName, firstName);
        newUser = applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);
        return new RedirectView("/login");
    }

    @GetMapping("/userprofile")
    public String getUserProfilePage(Principal p, Model m) {
        m.addAttribute("user", ((UsernamePasswordAuthenticationToken) p).getPrincipal());
        return "user.html";
    }

    @GetMapping("/userprofile/{id}")
    public ResponseEntity<ApplicationUserModel> getAlbumById(@PathVariable(value = "id") Integer id) {
        ApplicationUserModel album = applicationUserRepository.findById(id)
                                                              .get();
        return new ResponseEntity(album, HttpStatus.OK);
    }
}
