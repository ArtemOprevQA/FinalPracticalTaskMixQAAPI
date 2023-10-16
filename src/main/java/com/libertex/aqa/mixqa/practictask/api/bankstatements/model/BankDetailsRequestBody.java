package com.libertex.aqa.mixqa.practictask.api.bankstatements.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankDetailsRequestBody{
    private String requisite;
    private String languageIso3;


}