package com.aszoka.awsimageupload.buckets;

import lombok.Getter;

//create an S3 bucket in aws account, copy the name
public enum BucketName {
    PROFILE_IMAGE("aszoka-image-upload-123");

    @Getter
    private final String BUCKET_NAME;

    BucketName(String BUCKET_NAME) {
        this.BUCKET_NAME = BUCKET_NAME;
    }
}
