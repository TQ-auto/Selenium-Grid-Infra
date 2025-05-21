package api.utils;

import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;

public class JsonActions {


    public static JSONObject getJsonObjectOfResponse(Response response) throws IOException {
        String responseBody = response.body().string();
        return new JSONObject(responseBody);
    }

    // Can be used for getting publisher id or post id from a response
    public static int getEntityIdFromJson(Response response) throws IOException {
        JSONObject jsonResponse = getJsonObjectOfResponse(response);
        return jsonResponse.getJSONObject("record").getJSONObject("params").getInt("id");
    }

    public static String getStatusOfPostFromJson(Response response) throws IOException {
        JSONObject jsonResponse = getJsonObjectOfResponse(response);
        return jsonResponse.getJSONObject("record").getJSONObject("params").getString("status");
    }
}
