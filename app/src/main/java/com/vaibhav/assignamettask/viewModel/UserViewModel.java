package com.vaibhav.assignamettask.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.vaibhav.assignamettask.utils.ApiResponse;
import com.vaibhav.data.repository.ProfileRepository;
import com.vaibhav.domain.repository.IProfileRepository;
import com.vaibhav.domain.usecase.profile.ProfileUseCase;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserViewModel extends BaseViewModel {
    private ProfileUseCase mProfileUseCase;

    private final MutableLiveData<ApiResponse> mUserLiveData = new MutableLiveData<>();


    public UserViewModel(@NonNull Application application) {
        super(application);

        IProfileRepository mIProfileRepository = new ProfileRepository();
        mProfileUseCase = new ProfileUseCase(mIProfileRepository);


    }

    public MutableLiveData<ApiResponse> getmUserLiveData() {
        return mUserLiveData;
    }

    public void getUserList(int pageNo, int PageSize) {
        disposables.add(mProfileUseCase.getProfileInfo(pageNo, PageSize)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> mUserLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> mUserLiveData.setValue(ApiResponse.success(result)),
                        throwable -> mUserLiveData.setValue(ApiResponse.error(throwable))
                ));
    }
    //endregion
}
