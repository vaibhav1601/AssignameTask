package com.vaibhav.domain.repository;



import com.vaibhav.domain.model.ProfileDTO;

import java.util.List;

import io.reactivex.Observable;


public interface IProfileRepository {
    Observable<ProfileDTO> getProfileInfo(int pageNumber, int PageSize);


}
