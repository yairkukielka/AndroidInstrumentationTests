package com.example.androidinstrumentationtests.testutils;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

import com.example.androidinstrumentationtests.DemoApplication;
import com.example.androidinstrumentationtests.di.DemoApplicationComponent;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class ThreadPoolIdlingResourceRule implements TestRule {

    private IdlingResource resource;

    public ThreadPoolIdlingResourceRule() {
        DemoApplicationComponent component = DemoApplication.getApplication().getComponent();
        resource = new ThreadPoolIdlingResource("threadPoolExecutor", component.threadPoolExecutor());
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
