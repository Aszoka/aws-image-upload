package com.aszoka.awsimageupload.service;

import com.aszoka.awsimageupload.profile.UserProfile;
import com.aszoka.awsimageupload.repo.UserProfileRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserProfileService {

    UserProfileRepo userProfileRepo;

    public UserProfile getUserByUsername(String username) {
        return userProfileRepo.findByUserName(username);
    }
}
