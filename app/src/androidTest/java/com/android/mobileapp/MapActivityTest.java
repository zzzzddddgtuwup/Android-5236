package com.android.mobileapp;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

/**
 * Created by harrylew on 12/1/14.
 */
public class MapActivityTest extends ActivityInstrumentationTestCase2
{
    private Solo solo;

    public MapActivityTest(){
        super(SettingActivity.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
    }

    @Override
    protected void tearDown() throws  Exception
    {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void checkDisplay() throws Exception
    {
        solo.assertCurrentActivity("wrong activity",MainActivity.class);
    }


}
