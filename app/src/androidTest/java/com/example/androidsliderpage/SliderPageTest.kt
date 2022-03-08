package com.example.androidsliderpage

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidsliderpage.util.DrawableMatcher

import org.junit.Before
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import com.example.androidsliderpage.util.ViewPager2IdlingResource
import org.hamcrest.Matcher


@RunWith(AndroidJUnit4::class)
class SliderPageTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private lateinit var viewPager2IdlingResource: ViewPager2IdlingResource

    @Before
    fun setUp() {
        activityRule.scenario.onActivity {
            viewPager2IdlingResource =
                ViewPager2IdlingResource(it.findViewById(R.id.view_pager), "viewPagerIdlingResource")
            IdlingRegistry.getInstance().register(viewPager2IdlingResource)
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(viewPager2IdlingResource)
    }

    @Test
    fun doPageSwipe() {
        checkPageUpDisplayed()
        onView(withId(R.id.view_pager)).perform(swipeLeft())
        checkPageDownDisplayed()
        onView(withId(R.id.view_pager)).perform(swipeRight())
        checkPageUpDisplayed()
    }

    private fun checkPageUpDisplayed() {
        onView(withText(UP_TEXT)).check(matches(isCompletelyDisplayed()))
        onView(withText(DOWN_TEXT)).check(ViewAssertions.doesNotExist())
        onView(withId(R.id.pageimage)).check(matches(withDrawable(R.drawable.launch_up)))
    }

    private fun checkPageDownDisplayed() {
        onView(withText(DOWN_TEXT)).check(matches(isCompletelyDisplayed()))
        onView(withText(UP_TEXT)).check(ViewAssertions.doesNotExist())
        onView(withId(R.id.pageimage)).check(matches(withDrawable(R.drawable.launch_down)))
    }

    companion object {
        private const val UP_TEXT = "Up"
        private const val DOWN_TEXT = "Down"
    }

    private fun withDrawable(resourceId: Int): Matcher<View?> {
        return DrawableMatcher(resourceId)
    }

    fun noDrawable(): Matcher<View?> {
        return DrawableMatcher(-1)
    }
}