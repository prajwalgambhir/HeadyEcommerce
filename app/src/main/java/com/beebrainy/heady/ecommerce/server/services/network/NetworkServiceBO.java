package com.beebrainy.heady.ecommerce.server.services.network;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class NetworkServiceBO implements INetworkService {

    @Override
    public void getRequest(final String requestUrl, final CallbackResponse callbackResponse) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                HttpURLConnection httpURLConnection = null;
                try {
                    url = new URL(requestUrl);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = new BufferedInputStream(httpURLConnection
                            .getInputStream());
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    boolean flag = false;
                    while ((line = br.readLine()) != null) {
                        result.append(flag ? System.getProperty("line.separator") : "").append
                                (line);
                        flag = true;
                    }
                    callbackResponse.onResponse(result.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    httpURLConnection.disconnect();
                }
            }
        }).start();

    }
}
