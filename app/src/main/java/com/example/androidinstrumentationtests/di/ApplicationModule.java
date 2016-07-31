package com.example.androidinstrumentationtests.di;

import android.app.Application;

import com.example.androidinstrumentationtests.BuildConfig;
import com.example.androidinstrumentationtests.domain.MakeNetworkRequest;
import com.example.androidinstrumentationtests.domain.MakeNetworkRequestImpl;
import com.example.androidinstrumentationtests.executor.Executor;
import com.example.androidinstrumentationtests.executor.MainThread;
import com.example.androidinstrumentationtests.executor.MainThreadImpl;
import com.example.androidinstrumentationtests.executor.ThreadExecutor;
import com.example.androidinstrumentationtests.net.NetworkManager;
import com.example.androidinstrumentationtests.net.NetworkManagerImpl;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

@Module
public final class ApplicationModule {
    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application application() {
        return application;
    }

    @Provides
    @Singleton
    OkHttpClient httpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    NetworkManager networkManager(OkHttpClient client) {
        return new NetworkManagerImpl(client);
    }

    @Provides
    @Singleton
    Executor provideExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    ThreadPoolExecutor provideThreadPoolExecutor(Executor executor) {
        return ((ThreadExecutor) executor).getThreadPoolExecutor();
    }

    @Provides
    @Singleton
    MainThread provideMainThread() {
        return new MainThreadImpl();
    }

    @Provides
    @Singleton
    MakeNetworkRequest provideMakeNetworkRequest(Executor executor, MainThread mainThread, NetworkManager networkManager) {
        return new MakeNetworkRequestImpl(executor, mainThread, networkManager);
    }
}

