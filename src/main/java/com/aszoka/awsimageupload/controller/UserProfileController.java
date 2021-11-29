package com.aszoka.awsimageupload.controller;

import com.aszoka.awsimageupload.profile.UserProfile;
import com.aszoka.awsimageupload.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserProfileController {

    UserProfileService service;

    @GetMapping("/userprofile/{username}")
    public UserProfile getUserProfile(@PathVariable("username") String username) {
        return service.getUserByUsername(username);
    }
}
