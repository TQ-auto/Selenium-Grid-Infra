package api.tests;

import GeneralUtils.TestUtils;
import api.endpoints.LoginEndPoints;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Stack;

public abstract class TestApiBase {

    final String adminEmail = "admin@example.com";
    final String adminPassword = "password";
    final String deletionMessage = "successfullyDeleted";

    // Saves objects to be deleted at the end of each test.
    Stack<Object> deletionStack = new Stack<>();

    @BeforeClass
    public void login() throws IOException {
        Response response = LoginEndPoints.login(adminEmail,adminPassword);
        Assert.assertEquals(response.code(),200);
        String responseBodyString = response.body().string();
        Assert.assertTrue(responseBodyString.contains("root.render(AdminJS.Application)"));
    }

    @AfterClass
    public void cleanup() throws IOException {
        TestUtils.cleanup(deletionStack);
    }
}
