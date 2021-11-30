package com.aszoka.awsimageupload.controller;

import com.aszoka.awsimageupload.profile.UserProfile;
import com.aszoka.awsimageupload.service.UserProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@CrossOrigin("*") // provide the address from where the api accepts requests - * means from anywhere, only for development!
public class UserProfileController {

    UserProfileService service;

    @GetMapping("/userprofile/{username}")
    public UserProfile getUserProfile(@PathVariable("username") String username) {
        return service.getUserByUsername(username);
    }

    @GetMapping("/userprofile/users")
    public List<UserProfile> getUserProfiles() {
        return service.getUsers();
    }

    @PostMapping(
            path = "/userprofile/{profileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void uploadUserProfileImage(@PathVariable("profileId") UUID userProfileId,
                                       @RequestParam("file")MultipartFile file) {
        service.uploadUserProfileImage(userProfileId, file);

    }

    @GetMapping(path = "/userprofile/{profileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("profileId") UUID userProfileId) {
       return service.downloadUserProfileImage(userProfileId);
    }
}
