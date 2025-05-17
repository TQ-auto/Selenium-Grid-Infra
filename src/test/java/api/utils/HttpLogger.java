package api.utils;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger {

    public static HttpLoggingInterceptor getLogger() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logging;
    }

}
