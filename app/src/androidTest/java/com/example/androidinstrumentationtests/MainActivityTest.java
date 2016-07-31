package com.example.androidinstrumentationtests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.androidinstrumentationtests.testutils.ThreadPoolIdlingResourceRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public ThreadPoolIdlingResourceRule idlingResourceRule = new ThreadPoolIdlingResourceRule();

    @Test
    public void myTest() {
        onView(withText(MainActivity.HELLO)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).perform(click());
        onView(withText(MainActivity.HELLO_WORLD)).check(matches(isDisplayed()));
    }
}