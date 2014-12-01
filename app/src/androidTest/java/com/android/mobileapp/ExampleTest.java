package com.android.mobileapp;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Created by harrylew on 12/1/14.
 */
public class ExampleTest extends ActivityInstrumentationTestCase2
{
    private Solo solo;

    public ExampleTest(){
        super(login.class);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        solo = new Solo(getInstrumentation(),getActivity());
    }

    @Override
    protected void tearDown() throws Exception
    {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void testListItemClickShouldDisplay() throws Exception
    {
        solo.assertCurrentActivity("wrong activity",login.class);
    }

    /**
     * Test for the "new user" button
     * @throws Exception
     */
    public void testLoginButton() throws Exception
    {
        EditText user = (EditText) solo.getView(R.id.username_text);
        EditText pass = (EditText) solo.getView(R.id.password_text);
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.enterText(user,"aaa");
        solo.enterText(pass,"123");
        solo.clickOnButton("Log In");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
    }

    /**
     * Test registering new user
     * @throws Exception
     */
    public void testLogInButton() throws  Exception
    {
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.clickOnButton("New User");
        EditText user = (EditText) solo.getView(R.id.username123);
        EditText pass = (EditText) solo.getView(R.id.password123);
        EditText c_pass=(EditText) solo.getView(R.id.password_confirm);
        solo.assertCurrentActivity("wrong activity", registerActivity.class);
        solo.enterText(user,"bqq");
        solo.enterText(pass,"123");
        solo.enterText(c_pass,"123");
        solo.clickOnButton("Done");
        //solo.assertCurrentActivity("wrong activity",login.class);
        EditText user1 = (EditText) solo.getView(R.id.username_text);
        EditText pass1 = (EditText) solo.getView(R.id.password_text);
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.enterText(user1,"bqq");
        solo.enterText(pass1,"123");
        solo.clickOnButton("Log In");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
    }



}
