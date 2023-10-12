package com.libertex.aqa.mixqa.practictask.api.bankstatements.Model;

import lombok.Data;
import okhttp3.MediaType;
import okio.BufferedSink;

import java.io.IOException;

@Data
public class BankDetailsRequestBody extends okhttp3.RequestBody {
    private String requisite;
    private String languageIso3;

    public BankDetailsRequestBody(String requisite, String languageIso3) {
        this.requisite = requisite;
        this.languageIso3 = languageIso3;
    }


    public String getRequisite() {
        return requisite;
    }


    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }


    public String getLanguageIso3() {
        return languageIso3;
    }


    public void setLanguageIso3(String languageIso3) {
        this.languageIso3 = languageIso3;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse("application/json; charset=UTF-8");
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        String json = "{\"requisite\":\"" + requisite + "\",\"languageIso3\":\"" + languageIso3 + "\"}";
        sink.writeUtf8(json);
    }
}