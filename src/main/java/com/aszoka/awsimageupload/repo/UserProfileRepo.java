package com.aszoka.awsimageupload.repo;

import com.aszoka.awsimageupload.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProfileRepo extends JpaRepository<UserProfile, UUID> {

}
