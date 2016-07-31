package com.example.androidinstrumentationtests;

import android.app.Application;

import com.example.androidinstrumentationtests.di.ApplicationModule;
import com.example.androidinstrumentationtests.di.DaggerDemoApplicationComponent;
import com.example.androidinstrumentationtests.di.DemoApplicationComponent;

public class DemoApplication extends Application {

    private DemoApplicationComponent component;
    private static DemoApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        this.component = DaggerDemoApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        component.injectApplication(this);
    }

    public DemoApplicationComponent getComponent() {
        return component;
    }

    public static DemoApplication getApplication() {
        return application;
    }
}
