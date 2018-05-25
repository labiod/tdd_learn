package com.kgb.news.utils;

import android.util.Log;

import com.google.firebase.crash.FirebaseCrash;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import java.text.ParseException;

/**
 * @author Krzysztof Betlej <k.betlej></k.betlej>@samsung.com>.
 * @date 5/25/18
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({FirebaseCrash.class, Log.class})
public class DateUtilsTest {
    String correctInputDate = "2018-05-25T07:01:27Z";
    String correctOutputDate = "Fri, 25 May 2018 07:01";

    String incorrectInputDate = "2018-05-25T07";

    @Test
    public void formatNewsApiDate_correctDate_outputsCorrectDate() {
        String outputDate = DateUtils.formatNewsApiDate(correctInputDate);
        assertEquals(outputDate, correctOutputDate);
    }

    @Test
    public void formatNewsApiDate_nullInput_outputsCorrectDate() {
        String outputDate = DateUtils.formatNewsApiDate(null);
        assertEquals(outputDate, null);
    }

    @Test
    public void formatNewsApiDate_incorrectInput_outputsCorrectDate() {
        PowerMockito.mockStatic(FirebaseCrash.class);
        PowerMockito.mockStatic(Log.class);
        String outputDate = DateUtils.formatNewsApiDate(incorrectInputDate);
        assertEquals(outputDate, incorrectInputDate);
        PowerMockito.verifyStatic(FirebaseCrash.class);
        FirebaseCrash.report(Matchers.isA(ParseException.class));
    }
}
