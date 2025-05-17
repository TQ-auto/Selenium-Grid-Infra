package api.endpoints;

import api.utils.HttpLogger;
import okhttp3.*;
import java.io.IOException;

public class LoginEndPoints {

    public static Response login(String email,String password, String cookie) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create("email=%s&password=%s".formatted(email,password),mediaType);

        Request request = new Request.Builder()
                .url(Routes.POST_ADMIN_LOGIN_URL)
                .method("POST", body)
                .addHeader("Accept", " text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .addHeader("Accept-Encoding", "gzip, deflate, br, zstd")
                .addHeader("Accept-Language", "en-US,en;q=0.9,he-IL;q=0.8,he;q=0.7,ar-PS;q=0.6,ar;q=0.5")
                .addHeader("Cache-Control", "max-age=0")
                .addHeader("Connection", "keep-alive")
                .addHeader("Content-Length", "43")
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .addHeader("Host", Routes.HOST)
                .addHeader("Cookie", cookie)
                .build();

        return client.newCall(request).execute();
    }
}
