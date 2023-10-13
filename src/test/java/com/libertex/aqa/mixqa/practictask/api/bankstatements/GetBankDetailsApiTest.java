package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsResponse;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.Requisite;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Slf4j
public class GetBankDetailsApiTest extends BaseTest {

    @Test
    public void testGetOrgBankDetails() throws IOException {

        String credentials = "login:pass";
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
        String requisite = "LV51RTMB0000621806801";
        String languageIso3 = "rus";

        BankStatementsApiHelper apiServiceHelper = new BankStatementsApiHelper();

        Response<BankDetailsResponse> response =apiServiceHelper.executeApiRequest(basicAuth, requisite, languageIso3);

        List<Requisite> requisites = response.body().getRequisites();

        Requisite firstRequisite = requisites.get(0);
        String name = firstRequisite.getBankInfo().getName();

        if ("JSC RIETUMU BANKA".equals(name)) {
            log.info("The response contains the expected name: JSC RIETUMU BANKA");
        } else {
            log.info("The response does not contain the expected name.");
        }
    }
}


