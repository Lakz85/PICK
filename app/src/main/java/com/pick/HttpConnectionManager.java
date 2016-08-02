package com.pick;

import android.util.Log;

import org.apache.http.params.HttpParams;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 10 on 2016-07-21.
 */
public class HttpConnectionManager {

    private static final String DEBUF_TAG = "HttpConnectionManager";
    private static HttpParams httpInitParams;

    public synchronized static HttpURLConnection getHttpURLConnection(String targetURL){
        HttpURLConnection httpURLConnection = null;

        try{
            URL url = new URL(targetURL);

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setConnectTimeout(10000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        } catch (MalformedURLException e) {
            Log.e("DEbug_tag","getHttpURLConnection()--에러발생--",e);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpURLConnection;
    }
}
