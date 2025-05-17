package api.endpoints;

import api.payload.Post;
import api.utils.HttpLogger;
import okhttp3.*;
import java.io.IOException;

public class PostEndPoints {

    public static Response createPost(String cookie, Post postObject) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("title", postObject.getTitle())
                .addFormDataPart("content", postObject.getContent())
                .addFormDataPart("status", postObject.getStatus())
                .addFormDataPart("publisher", String.valueOf(postObject.getPublisherId()))
                .addFormDataPart("published", postObject.getPublished())
                .addFormDataPart("someJson.0.string", postObject.getJsonString())
                .addFormDataPart("someJson.0.number", String.valueOf(postObject.getJsonNumber()))
                .addFormDataPart("someJson.0.boolean", postObject.getJsonBoolean())
                .build();

        Request request = new Request.Builder()
                .url(Routes.POST_NEW_POST_URL)
                .method("POST", body)
                .addHeader("Cookie", cookie)
                .build();

        return client.newCall(request).execute();
    }

    public static Response editPost(String cookie,Post postObject) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("title", postObject.getTitle())
                .addFormDataPart("content", postObject.getContent())
                .addFormDataPart("status", postObject.getStatus())
                .addFormDataPart("publisher", String.valueOf(postObject.getPublisherId()))
                .addFormDataPart("published", postObject.getPublished())
                .addFormDataPart("someJson.0.string", postObject.getJsonString())
                .addFormDataPart("someJson.0.number", String.valueOf(postObject.getJsonNumber()))
                .addFormDataPart("someJson.0.boolean", postObject.getJsonBoolean())
                .addFormDataPart("id",String.valueOf(postObject.getPostId()))
                .build();

        Request request = new Request.Builder()
                .url(Routes.EDIT_POST_URL.replace("{id}",String.valueOf(postObject.getPostId())))
                .method("POST", body)
                .addHeader("Cookie", cookie)
                .build();

        return client.newCall(request).execute();
    }
}
