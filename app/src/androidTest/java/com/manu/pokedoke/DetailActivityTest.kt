package com.manu.pokedoke

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.manu.pokedoke.view.ui.detail.DetailActivity
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(DetailActivity::class.java, false, false)

    @Before
    fun setup() {
        val intent = Intent()
        intent.putExtra(DetailActivity.ARG_POKEMON_NAME, FIRST_POKEMON_NAME)
        intent.putExtra(DetailActivity.ARG_POKEMON_IMAGE_URL, FIRST_POKEMON_IMAGE_URL)
        activityRule.launchActivity(intent)
    }

    @Test
    fun pokemon_details_displayed() {
        onView(withId(R.id.pokemon_name)).check(matches(isDisplayed()))
        onView(withId(R.id.pokemon_name)).check(matches(withText(FIRST_POKEMON_NAME.toUpperCase())))
        onView(withId(R.id.pokemon_image)).check(matches(isDisplayed()))
        onView(withId(R.id.type_name_one)).check(matches(isDisplayed()))
        onView(withId(R.id.type_name_one)).check(matches(withText(FIRST_POKEMON_TYPE_NAME_ONE)))
        onView(withId(R.id.type_name_two)).check(matches(isDisplayed()))
        onView(withId(R.id.type_name_two)).check(matches(withText(containsString(FIRST_POKEMON_TYPE_NAME_TWO))))
        onView(withId(R.id.height)).check(matches(isDisplayed()))
        onView(withId(R.id.height)).check(matches(withText(FIRST_POKEMON_HEIGHT.toUpperCase())))
        onView(withId(R.id.weight)).check(matches(isDisplayed()))
        onView(withId(R.id.weight)).check(matches(withText(FIRST_POKEMON_WEIGHT.toUpperCase())))
    }
}