package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.google.gson.Gson;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.Model.BankDetailsRequestBody;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.Model.BankDetailsResponse;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;


public class ApiServiceHelper {

    private static final Logger logger = LoggerFactory.getLogger(ApiServiceHelper.class);

    private BankDetailsApiService apiService;

    public ApiServiceHelper(BaseTest baseTest) {
        this.apiService = baseTest.apiService;
    }
    //BaseTest baseTest = new BaseTest();

    public Response<BankDetailsResponse> executeApiRequest(String basicAuth, String requisite, String languageIso3) throws IOException {

        BankDetailsRequestBody requestBody = new BankDetailsRequestBody(requisite, languageIso3);

        Call<BankDetailsResponse> call = apiService.getOrgBankDetails(basicAuth, requestBody);

        Response<BankDetailsResponse> response = call.execute();


        if (response.isSuccessful()) {
            BankDetailsResponse apiResponse = response.body();
            if (apiResponse != null) {

                Gson gson = new Gson();
                String jsonResponse = gson.toJson(apiResponse);

                logger.info("The response is {}", jsonResponse);


            }
        } else {
            int errorCode = response.code();
            String errorMessage = response.message();
            logger.error("Error {}:{}", errorCode, errorMessage);

            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                String errorResponseString = errorBody.string();
                logger.error("Error Response Body: {}", errorResponseString);
            }
        }
        return response;
    }

}