package com.aszoka.awsimageupload.profile;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserProfile {

    @Id
    private UUID profileId = UUID.randomUUID();
    private String userName;
    private String profileImageLink; // s3 link

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfile that = (UserProfile) o;
        return Objects.equals(profileId, that.profileId)  &&
               Objects.equals(userName, that.userName)  &&
               Objects.equals(profileImageLink, that.profileImageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profileId, userName, profileImageLink);
    }
}