package com.example.androidinstrumentationtests.domain;

import android.util.Log;

import com.example.androidinstrumentationtests.MainActivity;
import com.example.androidinstrumentationtests.executor.Executor;
import com.example.androidinstrumentationtests.executor.MainThread;
import com.example.androidinstrumentationtests.net.NetworkManager;

import java.io.IOException;

public class MakeNetworkRequestImpl implements MakeNetworkRequest, Runnable {

    private final Executor executor;
    private final MainThread mainThread;
    private final NetworkManager networkManager;
    private Callback callback;

    public MakeNetworkRequestImpl(Executor executor, MainThread mainThread, NetworkManager networkManager) {
        this.executor = executor;
        this.mainThread = mainThread;
        this.networkManager = networkManager;
    }

    @Override
    public void execute(final Callback callback) {
        this.callback = callback;
        this.executor.run(this);
    }

    @Override
    public void run() {
        try {
        String s1 = networkManager.makeCall();
        // for this example we ignore the result of the network call and we'll always return HELLO_WORLD
        mainThread.post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(MainActivity.HELLO_WORLD);
            }
        });
        } catch (IOException e) {
            Log.d("NetworkManager", "Network exception");
        }
    }
}