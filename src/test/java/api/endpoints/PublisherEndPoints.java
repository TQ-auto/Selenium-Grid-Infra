package api.endpoints;

import api.payload.Publisher;
import api.utils.HttpLogger;
import okhttp3.*;
import java.io.IOException;

public class PublisherEndPoints {

    public static Response createPublisher(Publisher publisherObject) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("name",publisherObject.getPublisherName())
                .addFormDataPart("email",publisherObject.getPublisherEmail())
                .build();

        Request request = new Request.Builder()
                .url(Routes.POST_PUBLISHER_URL)
                .method("POST", body)
                .addHeader("Cookie", Routes.COOKIE)
                .build();

        return client.newCall(request).execute();
    }

    public static Response deletePublisher(int publisherId) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url(Routes.DELETE_PUBLISHER_URL.replace("{id}",String.valueOf(publisherId)))
                .method("POST", body)
                .addHeader("Cookie", Routes.COOKIE)
                .build();

        return client.newCall(request).execute();
    }

}
