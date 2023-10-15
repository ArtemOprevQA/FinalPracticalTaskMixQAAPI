package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.BankDetailsResponse;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.model.Requisite;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

@Slf4j
public class GetBankDetailsApiTest extends BaseTest {

    String requisite = "LV51RTMB0000621806801";
    String languageIso3 = "rus";
    @Test(description = "Response body contains bank name - JSC RIETUMU BANKA")
    public void testGetOrgBankDetails() throws IOException {

        Response<BankDetailsResponse> response =bankStatementsApiHelper.getOrgBankDetails(requisite, languageIso3);

        List<Requisite> requisites = response.body().getRequisites();

        Requisite firstRequisite = requisites.get(0);
        String name = firstRequisite.getBankInfo().getName();

        Assertions.assertThat(name).isEqualTo("JSC RIETUMU BANKA");

    }
}


