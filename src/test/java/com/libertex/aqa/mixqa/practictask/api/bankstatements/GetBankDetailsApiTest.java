package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsResponse;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.Requisite;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class GetBankDetailsApiTest extends BaseTest {

    private static final String requisite = "LV51RTMB0000621806801";

    private static final String languageIso3 = "rus";
    @Test(description = "Response body contains bank name - JSC RIETUMU BANKA")
    public void testGetOrgBankDetails() throws IOException {

        BankDetailsResponse response = bankStatementsApiHelper.getOrgBankDetails(requisite, languageIso3);

        List<Requisite> requisites = response.getRequisites();

        boolean isBankWithSwift = requisites.stream()
                .filter(requisite -> "JSC RIETUMU BANKA".equals(requisite.getBankInfo().getName()))
                .anyMatch(requisite -> "SWIFT".equals(requisite.getBankInfo().getCodeType()));

        Assertions.assertThat(isBankWithSwift).isTrue();
    }
}


