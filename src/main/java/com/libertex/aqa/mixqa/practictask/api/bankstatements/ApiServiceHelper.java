package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.Model.BankDetailsRequestBody;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.Model.BankDetailsResponse;
import io.qameta.allure.Step;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class ApiServiceHelper {

    //Response<BankDetailsResponse> response;

    private static final Logger logger = LoggerFactory.getLogger(ApiServiceHelper.class);

    private BankDetailsApiService apiService;

    public ApiServiceHelper(BaseTest baseTest) {
        this.apiService = baseTest.apiService;
    }

    @Step("Test of successful execution of the request")
    public Response<BankDetailsResponse> executeApiRequest(String basicAuth, String requisite, String languageIso3) throws IOException {

        BankDetailsRequestBody requestBody = new BankDetailsRequestBody(requisite, languageIso3);

        Call<BankDetailsResponse> call = apiService.getOrgBankDetails(basicAuth, requestBody);

        Response<BankDetailsResponse> response = call.execute();

        return response;
    }

    @Step("Response is successful and has body")
    public Response<BankDetailsResponse> basicValidation(Response<BankDetailsResponse> response) throws IOException {

    Response<BankDetailsResponse> validatedResponse = response;
        if (response.isSuccessful()) {
            BankDetailsResponse apiResponseBody = response.body();
            if (apiResponseBody != null) {
                logger.info("The response is {}", response);
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
        return validatedResponse;
        }
    }

