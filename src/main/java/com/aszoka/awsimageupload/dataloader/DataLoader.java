package com.aszoka.awsimageupload.dataloader;

import com.aszoka.awsimageupload.profile.UserProfile;
import com.aszoka.awsimageupload.repo.UserProfileRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

public class DataLoader {

    /*@Bean
    CommandLineRunner run(UserProfileRepo userProfileRepo) {
        return args -> {
            userProfileRepo.save(
                    new UserProfile(UUID.randomUUID(), "aszoka", null)
            );
        };
    }*/
}
