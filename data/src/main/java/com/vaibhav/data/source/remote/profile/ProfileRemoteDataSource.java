package com.vaibhav.data.source.remote.profile;


import com.vaibhav.data.source.ApiServiceGenerator;
import com.vaibhav.data.source.remote.profile.model.ProfileResponse;

import io.reactivex.Observable;

public class ProfileRemoteDataSource {

    ProfileEndpoint mProfileEndPoint;

    public ProfileRemoteDataSource() {
        this.mProfileEndPoint = ApiServiceGenerator.createService(ProfileEndpoint.class);
    }


    public Observable<ProfileResponse> getProfileInfo(int pageNumber, int pageSize) {
        return mProfileEndPoint.getUserList(pageNumber,pageSize);
    }


}
