package api.tests;

import api.endpoints.LoginEndPoints;
import okhttp3.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class TestApiBase {

    final String adminEmail = "admin@example.com";
    final String adminPassword = "password";
    final String cookie = "adminjs=s%3AmAIzl2448JPByO6P69YbjRqRS3zYPah-.VgnnPPM%2FWdA%2BwFnYB09OlnfFnRCTEyBuLwTiwSuKRvM";

    @BeforeClass
    public void login() throws IOException {
        Response response = LoginEndPoints.login(adminEmail,adminPassword,cookie);
        Assert.assertEquals(response.code(),200);
        String responseBodyString = response.body().string();
        Assert.assertTrue(responseBodyString.contains("root.render(AdminJS.Application)"));
    }


    public static JSONObject getJsonObjectOfResponse(Response response) throws IOException {
        String responseBody = response.body().string();
        return new JSONObject(responseBody);
    }
}
