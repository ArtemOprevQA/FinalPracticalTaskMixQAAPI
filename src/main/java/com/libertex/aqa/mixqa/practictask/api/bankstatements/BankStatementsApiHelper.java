package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsRequestBody;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsResponse;
import io.qameta.allure.Step;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Base64;
import java.util.Properties;
@Slf4j
public class BankStatementsApiHelper {

    private final BankDetailsApiService apiServiceHelper;

    public BankStatementsApiHelper() {

        String endPoint = "/rest/bank_statements_api/";

        PropertyReader propertyReader = new PropertyReader();
        Properties properties = propertyReader.loadConfigProperties();
        String baseUrl = properties.getProperty("api.base.url1");

        final AllureOkHttp3 allureOkHttp3 = new AllureOkHttp3();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(allureOkHttp3)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl + endPoint)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiServiceHelper = retrofit.create(BankDetailsApiService.class);

    }

    @Step("Test of successful execution of the request")
    public Response<BankDetailsResponse> executeApiRequest(String requisite, String languageIso3) throws IOException {

        PropertyReader propertyReader = new PropertyReader();
        Properties properties = propertyReader.loadConfigProperties();
        String credentials = properties.getProperty("credentials");

        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        BankDetailsRequestBody requestBody = new BankDetailsRequestBody(requisite, languageIso3);

        Call<BankDetailsResponse> call = apiServiceHelper.getOrgBankDetails(basicAuth, requestBody);

        return basicValidation(call.execute());
    }

    @Step("Response is successful and has body")
    private Response<BankDetailsResponse> basicValidation(Response<BankDetailsResponse> response) throws IOException {

        if (response.isSuccessful()) {
            BankDetailsResponse apiResponseBody = response.body();
            if (apiResponseBody != null) {
                log.info("The response is {}", response);
            }
        } else {
            int errorCode = response.code();
            String errorMessage = response.message();
            log.error("Error {}:{}", errorCode, errorMessage);

            ResponseBody errorBody = response.errorBody();
            if (errorBody != null) {
                String errorResponseString = errorBody.string();
                log.error("Error Response Body: {}", errorResponseString);
            }
        }
        return response;
        }
    }

