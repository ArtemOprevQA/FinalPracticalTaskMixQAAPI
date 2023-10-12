package com.libertex.aqa.mixqa.practictask.api.bankstatements;

import com.libertex.aqa.mixqa.practictask.api.bankstatements.Model.BankDetailsResponse;
import com.libertex.aqa.mixqa.practictask.api.bankstatements.Model.Requisite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

public class GetBankDetailsApiTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(GetBankDetailsApiTest.class);
    @Test
    public void testGetOrgBankDetails() throws IOException {

        String credentials = "login:pass";
        String basicAuth = "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
        String requisite = "LV51RTMB0000621806801";
        String languageIso3 = "rus";

        ApiServiceHelper apiServiceHelper = new ApiServiceHelper(this);

        Response<BankDetailsResponse> response = apiServiceHelper.executeApiRequest(basicAuth, requisite, languageIso3);

        if (response != null && response.body() != null) {
            List<Requisite> requisites = response.body().getRequisites();

            if (!requisites.isEmpty()) {
                Requisite firstRequisite = requisites.get(0);
                String name = firstRequisite.getBankInfo().getName();

                if ("JSC RIETUMU BANKA".equals(name)) {
                    logger.info("The response contains the expected name: JSC RIETUMU BANKA");
                } else {
                    logger.info("The response does not contain the expected name.");
                }
            } else {
                logger.info("The response contains no requisites.");
            }
        } else {
            logger.info("API response is null or empty.");
        }

        }
    }


