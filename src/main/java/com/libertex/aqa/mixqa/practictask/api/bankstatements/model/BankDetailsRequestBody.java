package com.libertex.aqa.mixqa.practictask.api.bankstatements.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import okhttp3.MediaType;
import okio.BufferedSink;

import java.io.IOException;

@Data
@AllArgsConstructor
public class BankDetailsRequestBody extends okhttp3.RequestBody {
    private String requisite;
    private String languageIso3;

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