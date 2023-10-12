package com.libertex.aqa.mixqa.practictask.api.bankstatements.Model;
import lombok.Data;
import java.util.List;
@Data
public class BankDetailsResponse {
    private ResponseInfo responseInfo;
    private List<Requisite> requisites;
}
