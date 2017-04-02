package com.jesusmariagarcia.petagram2.restApi.model;

import com.jesusmariagarcia.petagram2.pojo.Followers;

import java.util.ArrayList;

/**
 * Created by jesusmgarcia on 2/4/17.
 */

public class FollowersResponse {

    public ArrayList<Followers> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Followers> followers) {
        this.followers = followers;
    }

    ArrayList<Followers> followers;
}

