package api.endpoints;

import api.utils.HttpLogger;
import okhttp3.*;

import java.io.IOException;

public class ProfileEndPoints {

    public static Response deleteProfile(int profileId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url(Routes.DELETE_PROFILE_URL.replace("{id}",String.valueOf(profileId)))
                .method("POST", body)
                .addHeader("Cookie", Routes.COOKIE)
                .build();

        return client.newCall(request).execute();
    }
}
