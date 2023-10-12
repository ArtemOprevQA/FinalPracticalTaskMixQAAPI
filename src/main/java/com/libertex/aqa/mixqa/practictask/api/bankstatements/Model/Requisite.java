package com.libertex.aqa.mixqa.practictask.api.bankstatements.Model;

import lombok.Data;

@Data
public class Requisite {
    private String requisite;
    private String currency;
    private BankInfo bankInfo;
    private OwnerInfo ownerInfo;
}
