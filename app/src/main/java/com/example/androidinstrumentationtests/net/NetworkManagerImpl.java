package com.example.androidinstrumentationtests.net;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkManagerImpl implements NetworkManager {

    private OkHttpClient client;

    public NetworkManagerImpl(OkHttpClient client) {
        this.client = client;
    }

    @Override
    public String makeCall() throws IOException {
        String url = "https://github.com/square/okhttp"; // test url
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
