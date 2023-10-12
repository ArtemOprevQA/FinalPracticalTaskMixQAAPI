package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.testng.annotations.BeforeClass;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Data
public class BaseTest {

    protected BankDetailsApiService apiService;

    @BeforeClass
    public BankDetailsApiService  setUp() {
        final AllureOkHttp3 allureOkHttp3 = new AllureOkHttp3();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(allureOkHttp3)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fxbank-bvi-rest-fxb2-priv.qa-env.com/rest/bank_statements_api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(BankDetailsApiService.class);

        return apiService;
    }
}
