package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Controller
public class ApplicationUserController<T> {
    @Autowired
    public ApplicationUserRepository applicationUserRepository;
    @Autowired
    PostReppsitory postReppsitory;
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
        return new RedirectView("/userprofile");
    }

    @GetMapping("/userprofile")
    public String getUserProfilePage(Principal p, Model m) {
        String loggedInUserName = p.getName();
        ApplicationUserModel loggedInUser = applicationUserRepository.findByUsername(loggedInUserName);
        List<PostModel> requiredPostTwo = postReppsitory.findAllByApplicationUserModelId(loggedInUser.getId());
        m.addAttribute("posts", requiredPostTwo);
        ApplicationUserModel currentUser = (ApplicationUserModel) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        m.addAttribute("follow", loggedInUser.getId());
        m.addAttribute("user", ((UsernamePasswordAuthenticationToken) p).getPrincipal());
        m.addAttribute("currentUser", ((UsernamePasswordAuthenticationToken) p).getPrincipal());

        return "user.html";
    }

    @GetMapping("/userprofile/{id}")
    public String getAllInfo(Principal p, Model m, @PathVariable Integer id) {
        ApplicationUserModel requiredProfile = applicationUserRepository.findById(id)
                                                                        .get();
        if (requiredProfile != null) {
            m.addAttribute("user", requiredProfile);
            String loggedInUserName = p.getName();
            ApplicationUserModel loggedInUser = applicationUserRepository.findByUsername(loggedInUserName);
            boolean isAllowedToEdit = loggedInUser.getId() == id;
            m.addAttribute("isAllowedToEdit", isAllowedToEdit);
            List<PostModel> requiredPostTwo = postReppsitory.findAllByApplicationUserModelId(id);
            m.addAttribute("posts", requiredPostTwo);
            m.addAttribute("currentUser", ((UsernamePasswordAuthenticationToken) p).getPrincipal());

            return "user.html";
        }
        return "user.html";
    }

    @PostMapping("/userprofile/{id}")
    public RedirectView getFollow(Principal p, @RequestParam(name = "id") Integer id) {
        ApplicationUserModel currentUser = (ApplicationUserModel) ((UsernamePasswordAuthenticationToken) p).getPrincipal();

        System.out.println(currentUser.getId() + "" + id);
        applicationUserRepository.insert(currentUser.getId(), id);

        return new RedirectView("/userprofile");
    }

    @GetMapping("/feed")
    public String getAllFollowedUser(Model m, Principal p) {
        ApplicationUserModel currentUser = (ApplicationUserModel) ((UsernamePasswordAuthenticationToken) p).getPrincipal();
        List<PostModel> allFollowedUser = postReppsitory.findFollowedUser(currentUser.getId());

        m.addAttribute("following", allFollowedUser);
        m.addAttribute("currentUser", ((UsernamePasswordAuthenticationToken) p).getPrincipal());

        return "feed.html";
    }

    @GetMapping("/displayprofiles")
    public String displayAllUsers(Model m,Principal p) {
        List<ApplicationUserModel> applicationUserModel = applicationUserRepository.findAll();

        m.addAttribute("users", applicationUserModel);
        m.addAttribute("currentUser", ((UsernamePasswordAuthenticationToken) p).getPrincipal());

        return "dispaly.html";
    }
}
