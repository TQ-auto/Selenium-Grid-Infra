package api.endpoints;

import api.payload.Post;
import api.utils.HttpLogger;
import okhttp3.*;
import java.io.IOException;

public class PostEndPoints {

    public static Response createPost(Post postObject) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("title", postObject.getTitle())
                .addFormDataPart("content", postObject.getContent())
                .addFormDataPart("status", postObject.getStatus().toString())
                .addFormDataPart("publisher", String.valueOf(postObject.getPublisherId()))
                .addFormDataPart("published", String.valueOf(postObject.getPublished()))
                .addFormDataPart("someJson.0.string", postObject.getJsonString())
                .addFormDataPart("someJson.0.number", String.valueOf(postObject.getJsonNumber()))
                .addFormDataPart("someJson.0.boolean", String.valueOf(postObject.getJsonBoolean()))
                .build();

        Request request = new Request.Builder()
                .url(Routes.POST_NEW_POST_URL)
                .method("POST", body)
                .addHeader("Cookie", Routes.COOKIE)
                .build();

        return client.newCall(request).execute();
    }

    public static Response editPost(Post postObject) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("title", postObject.getTitle())
                .addFormDataPart("content", postObject.getContent())
                .addFormDataPart("status", String.valueOf(postObject.getStatus()))
                .addFormDataPart("publisher", String.valueOf(postObject.getPublisherId()))
                .addFormDataPart("published", String.valueOf(postObject.getPublished()))
                .addFormDataPart("someJson.0.string", postObject.getJsonString())
                .addFormDataPart("someJson.0.number", String.valueOf(postObject.getJsonNumber()))
                .addFormDataPart("someJson.0.boolean", String.valueOf(postObject.getJsonBoolean()))
                .addFormDataPart("id",String.valueOf(postObject.getPostId()))
                .build();

        Request request = new Request.Builder()
                .url(Routes.EDIT_POST_URL.replace("{id}",String.valueOf(postObject.getPostId())))
                .method("POST", body)
                .addHeader("Cookie", Routes.COOKIE)
                .build();

        return client.newCall(request).execute();
    }

    public static Response deletePost(int id) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(HttpLogger.getLogger())
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(Routes.DELETE_POST_URL.replace("{id}",String.valueOf(id)))
                .method("POST", body)
                .addHeader("Cookie", Routes.COOKIE)
                .addHeader("Host", " localhost:3000")
                .build();
        return client.newCall(request).execute();
    }
}
