package com.codefellowship.codefellowship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PostController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PostReppsitory postReppsitory;

    @PostMapping("/userprofile")
    public RedirectView addPost(@RequestParam String body,@RequestParam (name="id" ,required=false)ApplicationUserModel applicationUserModel) {
        PostModel post = new PostModel(body, applicationUserModel);
        postReppsitory.save(post);
        return new RedirectView("/userprofile");
    }


}
