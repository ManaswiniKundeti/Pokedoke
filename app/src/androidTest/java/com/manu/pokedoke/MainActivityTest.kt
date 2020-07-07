package com.manu.pokedoke

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject
import com.manu.pokedoke.view.ui.detail.DetailActivity
import com.manu.pokedoke.view.ui.main.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val intentRule = IntentsTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup() {
        intentRule.launchActivity(null)
    }

    @Test
    fun test_pokemon_loaded() {
        onView(withId(R.id.main_progress_bar)).check(matches(not(isDisplayed())))
        onView(withId(R.id.pokemon_recycler_view)).check(RecyclerViewItemCountAssertion(20))
    }

    @Test
    fun test_pokemon_click_starts_detail_activity() {
        onView(withId(R.id.pokemon_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                    ViewActions.click()
                ))

        val receivedIntent = Iterables.getOnlyElement(Intents.getIntents())
        IntentSubject.assertThat(receivedIntent).hasComponentPackage(INTENT_COMPONENT_NAME)
        IntentSubject.assertThat(receivedIntent).hasComponentClass(DetailActivity::class.java)
        IntentSubject.assertThat(receivedIntent).extras().containsKey(DetailActivity.ARG_POKEMON_NAME)
        IntentSubject.assertThat(receivedIntent).extras().containsKey(DetailActivity.ARG_POKEMON_IMAGE_URL)
        IntentSubject.assertThat(receivedIntent).extras().string(DetailActivity.ARG_POKEMON_NAME)
            .isEqualTo(FIRST_POKEMON_NAME)
    }
}