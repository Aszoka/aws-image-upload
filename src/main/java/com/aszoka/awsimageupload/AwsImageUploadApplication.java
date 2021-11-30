package com.aszoka.awsimageupload;

import com.aszoka.awsimageupload.dataloader.DataLoader;
import com.aszoka.awsimageupload.profile.UserProfile;
import com.aszoka.awsimageupload.repo.UserProfileRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class AwsImageUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsImageUploadApplication.class, args);
	}


/*	@Bean
	CommandLineRunner run(UserProfileRepo userProfileRepo) {
		return args -> {
			userProfileRepo.save(
					new UserProfile(UUID.randomUUID(), "aszoka", null)
			);
			userProfileRepo.save(
					new UserProfile(UUID.randomUUID(), "picike", null)
			);
		};
	}*/
}
