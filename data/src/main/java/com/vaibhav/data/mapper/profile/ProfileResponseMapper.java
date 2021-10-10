package com.vaibhav.data.mapper.profile;

import com.vaibhav.data.mapper.IMapper;
import com.vaibhav.data.source.remote.profile.model.ProfileResponse;
import com.vaibhav.data.source.remote.profile.model.UserDataResponse;
import com.vaibhav.domain.model.ProfileDTO;
import com.vaibhav.domain.model.UserSuccessDTO;

import java.util.ArrayList;
import java.util.List;

public class ProfileResponseMapper implements IMapper<ProfileResponse, ProfileDTO> {

    @Override
    public ProfileDTO mapFromEntity(ProfileResponse profileResponse) {

        if (profileResponse == null) {
            return null;
        }

        ProfileDTO mProfileDTO = new ProfileDTO();
        mProfileDTO.setTotal(profileResponse.getTotal());
        mProfileDTO.setPage(profileResponse.getPage());
        mProfileDTO.setTotalpages(profileResponse.getTotal_pages());
        mProfileDTO.setPerPage(profileResponse.getPer_page());
        mProfileDTO.setmUserSuccessDTOS(getUserData(profileResponse.getData()));


        return mProfileDTO;
    }

    private List<UserSuccessDTO> getUserData(List<UserDataResponse> data) {

        List<UserSuccessDTO> mResponseList = new ArrayList<>();
        for (UserDataResponse mUserDataResponse : data) {
            UserSuccessDTO mProfileDTO = new UserSuccessDTO();
            mProfileDTO.setmFirstName(mUserDataResponse.getFirst_name());
            mProfileDTO.setmLastName(mUserDataResponse.getLast_name());
            mProfileDTO.setmId(mUserDataResponse.getId());
            mProfileDTO.setmAvatar(mUserDataResponse.getAvatar());
            mProfileDTO.setmEmail(mUserDataResponse.getEmail());
            mResponseList.add(mProfileDTO);


        }
        return mResponseList;
    }

    @Override
    public ProfileResponse mapToEntity(ProfileDTO profileDTO) {
        return null;
    }
}