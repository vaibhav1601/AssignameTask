package com.vaibhav.data.source.remote.profile;

import com.vaibhav.data.source.remote.profile.model.ProfileResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Header;


interface ProfileEndpoint {


    @GET("api/users")
    Observable<ProfileResponse> getUserList(@Query("page") int page,@Query("per_page") int perPage);




}
