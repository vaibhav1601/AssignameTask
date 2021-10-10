package com.vaibhav.assignamettask.utils;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;


public class ApiDbResponse {

    public final Status status;

    @Nullable
    public final Object data;

    @androidx.annotation.Nullable
    public final Throwable error;


    private ApiDbResponse(Status status, @Nullable Object data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiDbResponse loading() {
        return new ApiDbResponse(Status.LOADING, null, null);
    }

    public static ApiDbResponse success(@NonNull Object data) {
        return new ApiDbResponse(Status.SUCCESS, data, null);
    }

    public static ApiDbResponse error(@androidx.annotation.NonNull Throwable error) {
        return new ApiDbResponse(Status.ERROR, null, error);
    }


    public enum Status {
        LOADING,
        SUCCESS,
        ERROR,
        COMPLETED
    }

}
