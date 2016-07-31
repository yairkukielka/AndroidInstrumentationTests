package com.example.androidinstrumentationtests.domain;

public interface MakeNetworkRequest {

    interface Callback {
        void onSuccess(final String s);
    }

    void execute(final Callback callback);
}