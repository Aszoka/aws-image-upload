package com.aszoka.awsimageupload.profile;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserProfile {

    @Id
    @Type(type="uuid-char")
    private UUID profileId = UUID.randomUUID();
    @Column(unique = true)
    private String userName;
    private String profileImageLink; // s3 link

    public UserProfile(String userName, String profileImageLink) {
        this.userName = userName;
        this.profileImageLink = profileImageLink;
    }

    public Optional<String> getProfileImageLink() {
        return Optional.ofNullable(profileImageLink);
    }

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
