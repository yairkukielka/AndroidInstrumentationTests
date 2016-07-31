package com.example.androidinstrumentationtests.di;

import com.example.androidinstrumentationtests.DemoApplication;
import com.example.androidinstrumentationtests.MainActivity;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;


@Singleton
@Component(modules = ApplicationModule.class)
public interface DemoApplicationComponent {

    void injectApplication(DemoApplication application);

    void injectActivity(MainActivity activity);

    OkHttpClient okHttpClient();
    ThreadPoolExecutor threadPoolExecutor();
}
