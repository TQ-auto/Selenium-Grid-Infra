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
    final String cookie = "adminjs=s%3ADnISaux-bVDFidhEC-0Dub9dy8nvGjKt.vxTQOd%2BDo1WLBbEOR%2F%2FLBjq7DhHT75at56Sbva4wK2s";

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
