package com.libertex.aqa.mixqa.practictask.api;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Base64;

public class ApiTest{

    private static final Logger logger = LoggerFactory.getLogger(ApiTest.class);
    private RetrofitInterface apiService;
    @BeforeClass
    public void setUp() {

        final AllureOkHttp3 allureOkHttp3 = new AllureOkHttp3();final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(allureOkHttp3)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fxbank-bvi-rest-fxb2-priv.qa-env.com/rest/bank_statements_api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(RetrofitInterface.class);

    }

    @Test
    @Step
    public void testGetOrgBankDetails() throws IOException {

        String credentials = "login:pass";
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());

        RequestBody requestBody = new RequestBody("LV51RTMB0000621806801", "rus");

        Call<ApiResponse> call = apiService.getOrgBankDetails(basicAuth, requestBody);

        Response<ApiResponse> response = call.execute();


        if (response.isSuccessful()) {
            ApiResponse apiResponse = response.body();
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
    }
}
