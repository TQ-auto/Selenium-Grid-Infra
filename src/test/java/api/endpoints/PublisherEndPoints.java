package api.endpoints;


import api.payload.Publisher;
import api.utils.HttpLogger;
import okhttp3.*;
import java.io.IOException;

public class PublisherEndPoints {

    public static Response createPublisher(Publisher publisherObject, String cookie) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name",publisherObject.getPublisherName())
                .addFormDataPart("email",publisherObject.getPublisherEmail())
                .build();
        Request request = new Request.Builder()
                .url(Routes.POST_PUBLISHER_URL)
                .method("POST", body)
                .addHeader("Accept", "application/json, text/plain, */*")
                .addHeader("Content-Length", "245")
                .addHeader("Content-Type", "multipart/form-data; boundary=----WebKitFormBoundaryjQaxMEAkAj27seSz")
                .addHeader("Cookie", cookie)
                .addHeader("Host", Routes.HOST)
                .addHeader("Origin", Routes.ORIGIN)
                .addHeader("Referer", Routes.POST_PUBLISHER_URL)
                .build();

        return client.newCall(request).execute();
    }

}
