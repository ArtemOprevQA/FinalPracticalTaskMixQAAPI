package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import io.qameta.allure.okhttp3.AllureOkHttp3;
import lombok.Data;
import okhttp3.OkHttpClient;
import org.testng.annotations.BeforeClass;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Data
public class BaseTest {

    protected BankDetailsApiService apiService;

    @BeforeClass
    public BankDetailsApiService setUp() {

        Properties properties = loadConfigProperties();
        String baseUrl = properties.getProperty("api.base.url1");

        final AllureOkHttp3 allureOkHttp3 = new AllureOkHttp3();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(allureOkHttp3)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl + "/rest/bank_statements_api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiService = retrofit.create(BankDetailsApiService.class);

        return apiService;
    }
    private Properties loadConfigProperties() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
