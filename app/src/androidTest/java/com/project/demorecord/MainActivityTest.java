package com.project.demorecord;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addedUserInfoSuccess() {
        onView(withId(R.id.editTExtName)).perform(replaceText("xxx"), closeSoftKeyboard());
        onView(withId(R.id.editTextAge)).perform(replaceText("15"), closeSoftKeyboard());
        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"))).perform(click());
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"))).perform(click());
    }
}
