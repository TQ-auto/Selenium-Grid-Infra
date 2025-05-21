package api.tests;

import api.endpoints.LoginEndPoints;
import okhttp3.*;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public abstract class TestApiBase {

    final String adminEmail = "admin@example.com";
    final String adminPassword = "password";
    final String cookie = "adminjs=s%3A_d_AjjnynBD0P5MzhrCh1hS0PrrAoNAx.81QgJf7wpBgd9WXb7bRCjoZh8m9oRuyFp2NYyyV0T6c";

    @BeforeClass
    public void login() throws IOException {
        Response response = LoginEndPoints.login(adminEmail,adminPassword,cookie);
        Assert.assertEquals(response.code(),200);
        String responseBodyString = response.body().string();
        Assert.assertTrue(responseBodyString.contains("root.render(AdminJS.Application)"));
    }

}
