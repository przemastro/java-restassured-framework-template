package com.testing.Tests.REST;

import com.testing.MainREST;
import com.testing.Pages.REST.RestFormLandingPage;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.jayway.restassured.response.Response;

public class RestForm extends MainREST {

    public String json;
    public Response response;
    private RestFormLandingPage restFormLandingPage;

    @Test
    public void saveNewRestForm(ITestContext context) throws InterruptedException, Exception {
        restFormLandingPage = new RestFormLandingPage()
                .postFormAndVerifyStatusCode(context, "correctRestForm.json");
    }

    @Test
    public void saveWrongRestForm(ITestContext context) throws InterruptedException, Exception {
        restFormLandingPage = new RestFormLandingPage()
                .postWrongFormAndVerifyStatusCode(context, "incorrectRestForm.json");
    }

    @Test
    public void uploadPDFFile(ITestContext context) throws InterruptedException, Exception {
        restFormLandingPage = new RestFormLandingPage()
                .uploadCorrectFileWithBadRequest(context, "file.pdf");
    }
}
