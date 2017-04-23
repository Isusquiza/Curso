package com.jesusmariagarcia.petagram2.restApi.model;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class ProfileResponse {

    String fullName;
    String profileImage;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

}
