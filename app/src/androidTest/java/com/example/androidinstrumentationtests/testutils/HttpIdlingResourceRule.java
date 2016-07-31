package com.example.androidinstrumentationtests.testutils;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

import com.example.androidinstrumentationtests.DemoApplication;
import com.example.androidinstrumentationtests.di.DemoApplicationComponent;
import com.jakewharton.espresso.OkHttp3IdlingResource;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class HttpIdlingResourceRule implements TestRule {

    private IdlingResource resource;

    public HttpIdlingResourceRule() {
        DemoApplicationComponent component = DemoApplication.getApplication().getComponent();
        resource = OkHttp3IdlingResource.create("OkHttp", component.okHttpClient());
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Espresso.registerIdlingResources(resource);
                base.evaluate();
                Espresso.unregisterIdlingResources(resource);
            }
        };
    }
}
