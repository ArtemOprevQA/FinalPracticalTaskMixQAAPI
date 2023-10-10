package com.libertex.aqa.mixqa.practictask.api;

import io.qameta.allure.Step;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("v3/getOrgBankDetails")
    @Step
    Call<ApiResponse> getOrgBankDetails(@Header("Authorization") String basicAuth, @Body RequestBody requestBody);
}
