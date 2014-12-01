package com.android.mobileapp;

import android.app.Activity;
import android.content.Intent;
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

    /**
     * Test out every button on the main activity
     * @throws Exception
     */
    public void testButtons() throws Exception
    {
        EditText user = (EditText) solo.getView(R.id.username_text);
        EditText pass = (EditText) solo.getView(R.id.password_text);
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.enterText(user,"aaa");
        solo.enterText(pass,"123");
        solo.clickOnButton("Log In");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("Select Forum");
        solo.assertCurrentActivity("wrong activity", SettingActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("My Questions");
        solo.assertCurrentActivity("wrong activity", MyQuestionActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("My Answers");
        solo.assertCurrentActivity("wrong activity", MyAnswerActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("Profile");
        solo.assertCurrentActivity("wrong activity", AccountActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
    }

    /**
     * Test out every type of notifications time period
     * @throws Exception
     */
    public void testNotifeq() throws Exception
    {
        EditText user = (EditText) solo.getView(R.id.username_text);
        EditText pass = (EditText) solo.getView(R.id.password_text);
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.enterText(user,"aaa");
        solo.enterText(pass,"123");
        solo.clickOnButton("Log In");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("Select Forum");
        solo.assertCurrentActivity("wrong activity", SettingActivity.class);
        solo.clickOnButton("Notification Frequency");
        solo.assertCurrentActivity("wrong activity", NotiFreqActivity.class);
        solo.isRadioButtonChecked("15 minutes");
        solo.clickOnRadioButton(1);
        solo.isRadioButtonChecked("One hour");
        solo.clickOnRadioButton(2);
        solo.isRadioButtonChecked("Three hours");
        solo.clickOnRadioButton(3);
        solo.isRadioButtonChecked("Never");
        solo.clickOnRadioButton(4);
        solo.isRadioButtonChecked("10 seconds(ONLY FOR TESTING!)");
        solo.clickOnRadioButton(0);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", SettingActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
   }

    /**
     * Test out the user logout feature
     * @throws Exception
     */
    public void testLogout() throws Exception
    {
        EditText user = (EditText) solo.getView(R.id.username_text);
        EditText pass = (EditText) solo.getView(R.id.password_text);
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.enterText(user,"aaa");
        solo.enterText(pass,"123");
        solo.clickOnButton("Log In");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnActionBarItem(R.id.action_logout);
        solo.assertCurrentActivity("wrong activity",login.class);
    }

    /**
     * Test out the map function
     * @throws Exception
     */
    public void testMap()throws Exception
    {
        EditText user = (EditText) solo.getView(R.id.username_text);
        EditText pass = (EditText) solo.getView(R.id.password_text);
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.enterText(user,"aaa");
        solo.enterText(pass,"123");
        solo.clickOnButton("Log In");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("Select Forum");
        solo.assertCurrentActivity("wrong activity", SettingActivity.class);
        solo.clickOnButton("Select Location");
        solo.assertCurrentActivity("Wrong activity", MapActivity.class);
    }
}
