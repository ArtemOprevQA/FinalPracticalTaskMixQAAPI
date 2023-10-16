package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsRequestBody;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface BankDetailsApiService {
    @Headers("Content-Type: application/json; charset=UTF-8")
    @POST("v3/getOrgBankDetails")
    Call<BankDetailsResponse> getOrgBankDetails(@Header("Authorization") String basicAuth, @Body BankDetailsRequestBody requestBody);
}
