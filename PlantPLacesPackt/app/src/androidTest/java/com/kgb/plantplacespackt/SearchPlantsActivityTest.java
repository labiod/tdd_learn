package com.kgb.plantplacespackt;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.kgb.dto.PlantDTO;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsNot.not;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 6/6/18
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

@RunWith(AndroidJUnit4.class)
public class SearchPlantsActivityTest {
    @Rule
    public ActivityTestRule<SearchPlantsActivity> activityRule = new ActivityTestRule<>(SearchPlantsActivity.class);

    @Test
    public void searchForRedBudShouldReturnAtLeastOneResult() {
        Context context = InstrumentationRegistry.getContext();

        onView(withId(R.id.act_plant_name)).perform(typeText("Redbud"));
        onView(withId(R.id.bottom)).perform(click());

        List<PlantDTO> plants = activityRule.getActivity().getPlants();
        assertThat(plants, not(empty()));
    }
}
