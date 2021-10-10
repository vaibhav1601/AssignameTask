package com.vaibhav.domain.usecase.profile;


import com.vaibhav.domain.model.ProfileDTO;
import com.vaibhav.domain.repository.IProfileRepository;

import java.util.List;

import io.reactivex.Observable;


public class ProfileUseCase {
    IProfileRepository mRepository;

    public ProfileUseCase(IProfileRepository repository) {
        mRepository = repository;
    }

    public Observable<ProfileDTO>getProfileInfo(int PageNumber, int PageSize) {
        return mRepository.getProfileInfo(PageNumber, PageSize);
    }

}
