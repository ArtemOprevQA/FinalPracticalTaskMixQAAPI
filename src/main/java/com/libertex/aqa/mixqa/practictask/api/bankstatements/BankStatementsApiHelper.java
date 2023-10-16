package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsRequestBody;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsResponse;
import io.qameta.allure.Step;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Base64;
@Slf4j
public class BankStatementsApiHelper {

    private final BankDetailsApiService apiServiceHelper;

    private static final String endPoint = "/rest/bank_statements_api/";
    public BankStatementsApiHelper() {

        String baseUrl = PropertyReader.getProperty("api.base.url");

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

    @Step("getOrgBankDetails")
    public BankDetailsResponse getOrgBankDetails(String requisite, String languageIso3) throws IOException {


        String credentials = PropertyReader.getProperty("APIcredentials");

        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        BankDetailsRequestBody requestBody = new BankDetailsRequestBody(requisite, languageIso3);

        Call <BankDetailsResponse> call = apiServiceHelper.getOrgBankDetails(basicAuth, requestBody);

        return basicValidation(call.execute());
    }

    @Step("Response is successful and has body")
    private BankDetailsResponse basicValidation(Response <BankDetailsResponse> response) throws IOException {

        if (response.isSuccessful()) {
            log.info("The response is {}", response.body());
            return response.body();
        } else {
            log.error("Error {}", response.message());
            throw new IOException("Request was not successful: " + response.message());

        }
        }
    }

