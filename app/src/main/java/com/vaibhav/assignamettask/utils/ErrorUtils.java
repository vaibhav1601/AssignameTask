package com.vaibhav.assignamettask.utils;

import android.util.Log;



import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.vaibhav.data.source.remote.profile.model.ErrorJsonResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import okhttp3.ResponseBody;

import retrofit2.Response;


public class ErrorUtils {
    private final static String TAG = "ErrorUtils";


    public static APIError parseError(Response<?> response) {

        APIError error = new APIError();
        ResponseBody responseBody = response.errorBody();
        try {

            JSONObject obj = new JSONObject(responseBody.string());
            error.setMessage(obj.toString());
            error.setStatusCode(response.code());
        } catch (JSONException je) {
            try {
                JSONArray jsonArray = new JSONArray(responseBody.string());
                error.setMessage(jsonArray.toString());
                error.setStatusCode(response.code());
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        } catch (Exception e) {

            Log.e("ErrorUtilty", e.toString());
        }

        return error;
    }

    public static ErrorJsonResponse parseCustomError(String message) {
        Gson gson = new Gson();
        ErrorJsonResponse errorJsonResponse = gson.fromJson(message, ErrorJsonResponse.class);
        return errorJsonResponse;

    }


    public static APIError parseThrowable(Throwable t) {
        APIError apiError = new APIError();
        ErrorJsonResponse errorJsonResponse = new ErrorJsonResponse();
        Gson gson = new Gson();
        try {
            if (t instanceof HttpException) {
//                ResponseBody responseBody = ((HttpException) t).response().errorBody();
                apiError = parseError(((HttpException) t).response());
            } else if (t instanceof retrofit2.HttpException) {
                apiError = parseError(((retrofit2.HttpException) t).response());
            } else if (t instanceof IOException) {

            } else {

            }

        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return apiError;
    }
}
