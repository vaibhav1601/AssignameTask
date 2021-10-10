package com.vaibhav.data.repository;


import com.vaibhav.data.mapper.profile.ProfileResponseMapper;
import com.vaibhav.data.source.remote.profile.ProfileRemoteDataSource;
import com.vaibhav.domain.model.ProfileDTO;
import com.vaibhav.domain.repository.IProfileRepository;

import java.util.List;

import io.reactivex.Observable;


public class ProfileRepository implements IProfileRepository {

    private ProfileResponseMapper profileResponseMapper;
    private ProfileRemoteDataSource profileRemoteDataSource;


    public ProfileRepository() {
        profileRemoteDataSource = new ProfileRemoteDataSource();
        profileResponseMapper = new ProfileResponseMapper();

    }


    @Override
    public Observable<ProfileDTO> getProfileInfo(int pageNumber, int PageSize) {
        return profileRemoteDataSource.getProfileInfo(pageNumber,PageSize).map(profileResponseMapper::mapFromEntity);

    }
}
