package com.example.anhch_000.sqlphpasnytaskandroid.Util;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Thanggun99 on 17/11/2016.
 */

public class API {
    public static final String SCHEME = "http";
    public static final String HOST = "192.168.0.102";
    public static final String PATH = "webservice";
    public static final String PATH_MIEN_BAC = "mienBacService/";
    public static final String GET_DATA_URL = PATH_MIEN_BAC + "getData.php";


    public static String callService(String path, Map<String, String> getParams) {
        return callService(path, getParams, null);
    }

    public static String callService(String path, Map<String, String> getParams, Map<String, String> postParams) {
        HttpURLConnection connect;
        InputStream is;
        String response = null;

        Uri.Builder builder = new Uri.Builder()
                .scheme(SCHEME)
                .authority(HOST)
                .appendPath(PATH)
                .appendEncodedPath(path);

        if (getParams != null) {
            builder = Utils.builderParams(builder, getParams);
        }
        try {
            connect = (HttpURLConnection) new URL(builder.build().toString()).openConnection();
            connect.setRequestProperty("accept", "application/json");
            connect.setRequestProperty("Connection", "close");
            connect.setDoInput(true);

            if (postParams != null) {
                Uri.Builder builderPostParams = new Uri.Builder();
                builderPostParams = Utils.builderParams(builderPostParams, postParams);

                connect.setRequestProperty("Content-Type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                connect.setRequestMethod("POST");
                connect.setDoOutput(true);

                OutputStream outputStream = connect.getOutputStream();
                outputStream.write(builderPostParams.build().getEncodedQuery().getBytes());
                outputStream.close();
            } else connect.setRequestMethod("GET");

            is = connect.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            response = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public static Uri.Builder builderParams(Uri.Builder builder, Map<String, String> params){
        for (Map.Entry<String, String> entry : params.entrySet()){
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        return builder;
    }
}
