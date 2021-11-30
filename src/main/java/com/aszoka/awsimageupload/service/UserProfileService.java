package com.aszoka.awsimageupload.service;


import com.aszoka.awsimageupload.buckets.BucketName;
import com.aszoka.awsimageupload.fileStore.FileStore;
import com.aszoka.awsimageupload.profile.UserProfile;
import com.aszoka.awsimageupload.repo.UserProfileRepo;
import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.apache.http.entity.ContentType.*;

@Service
@AllArgsConstructor
public class UserProfileService {

    UserProfileRepo userProfileRepo;
    FileStore fileStore;

    public UserProfile getUserByUsername(String username) {
        return userProfileRepo.findByUserName(username);
    }

    public List<UserProfile> getUsers() {
        return userProfileRepo.findAll();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file) {
        // 1. check if image is not empty
        boolean imgIsEmpty = file.isEmpty();
        if(imgIsEmpty) {
            throw new IllegalStateException("file is empty");
        }
        // 2. If file is an image
        if(!Arrays.asList(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType()).contains(file.getContentType())){
            throw new IllegalStateException("file is not an image");
        }
        // 3. The user exists in our database
        UserProfile user = userProfileRepo.getById(userProfileId);
       if(!userProfileRepo.findById(userProfileId).isPresent()) {
            throw new IllegalStateException("user not found");
        }
        // 4. Grab some metadata if any
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        // 5. store the image in s3 and update database (userProfileImageLink) with s3 image link
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBUCKET_NAME(),user.getProfileId());

        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.save(path,filename, Optional.of(metadata),file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        user.setProfileImageLink(filename);

        userProfileRepo.save(user);

    }

    public byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = userProfileRepo.getById(userProfileId);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBUCKET_NAME(),user.getProfileId());

        return user.getProfileImageLink()
                .map(key -> fileStore.download(path,key))
                .orElse(new byte[0]);

    }
}
