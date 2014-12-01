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
        solo.takeScreenshot("Before login");
        solo.clickOnButton("Log In");
        solo.takeScreenshot("After successfully login");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
    }

    /**
     * Test registering same user
     * @throws Exception
     */
    public void testReturnUserButton() throws  Exception
    {
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.clickOnButton("New User");
        EditText user = (EditText) solo.getView(R.id.username123);
        EditText pass = (EditText) solo.getView(R.id.password123);
        EditText c_pass=(EditText) solo.getView(R.id.password_confirm);
        solo.assertCurrentActivity("wrong activity", registerActivity.class);
        solo.enterText(user,"bqq"); //repeated username
        solo.enterText(pass,"123");
        solo.enterText(c_pass,"123");
        solo.clickOnButton("Done");
        solo.takeScreenshot("Repeated username");
        solo.clickOnButton("Cancel");
        solo.assertCurrentActivity("wrong activity",login.class);
        EditText user1 = (EditText) solo.getView(R.id.username_text);
        EditText pass1 = (EditText) solo.getView(R.id.password_text);
        solo.enterText(user1,"bqq");
        solo.enterText(pass1,"123");
        solo.takeScreenshot("Correct username");
        solo.clickOnButton("Log In");
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
    }

    /**
     * Test registering new user
     * @throws Exception
     */
    public void testNewUserButton() throws  Exception
    {
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.clickOnButton("New User");
        EditText user = (EditText) solo.getView(R.id.username123);
        EditText pass = (EditText) solo.getView(R.id.password123);
        EditText c_pass=(EditText) solo.getView(R.id.password_confirm);
        solo.assertCurrentActivity("wrong activity", registerActivity.class);
        solo.enterText(user,"kkk"); //new username
        solo.enterText(pass,"123");
        solo.enterText(c_pass,"123");
        solo.takeScreenshot("Adding a new user1");
        solo.clickOnButton("Done");
        solo.takeScreenshot("New entry added1");
        solo.assertCurrentActivity("wrong activity",login.class);
        EditText user1 = (EditText) solo.getView(R.id.username_text);
        EditText pass1 = (EditText) solo.getView(R.id.password_text);
        solo.enterText(user1,"kkk");
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
        solo.takeScreenshot("Main activity");
        solo.assertCurrentActivity("wrong activity", SettingActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("My Questions");
        solo.takeScreenshot("My Question Page");
        solo.assertCurrentActivity("wrong activity", MyQuestionActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("My Answers");
        solo.takeScreenshot("My answers page");
        solo.assertCurrentActivity("wrong activity", MyAnswerActivity.class);
        solo.goBack();
        solo.assertCurrentActivity("wrong activity", MainActivity.class);
        solo.clickOnButton("Profile");
        solo.takeScreenshot("My profile page");
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
        solo.takeScreenshot("testing radio button");
        solo.isRadioButtonChecked("Three hours");
        solo.clickOnRadioButton(3);
        solo.isRadioButtonChecked("Never");
        solo.clickOnRadioButton(4);
        solo.takeScreenshot("testing radio button 2");
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
        solo.takeScreenshot("Signout");
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

    /**
     * Test out empty username and password
     */
    public void testUsername() throws Exception
    {
        EditText user = (EditText) solo.getView(R.id.username_text);
        EditText pass = (EditText) solo.getView(R.id.password_text);
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.enterText(user,"");
        solo.enterText(pass,"123");
        solo.clickOnButton("Log In");
        solo.takeScreenshot("empty username");
        solo.clickOnButton("Try Again");
        solo.assertCurrentActivity("wrong activity",login.class);
        solo.clearEditText(user);
        solo.clearEditText(pass);
        solo.enterText(user,"aaa");
        solo.enterText(pass,"");
        solo.clickOnButton("Log In");
        solo.clickOnButton("Try Again");
        solo.takeScreenshot("empty password");
        solo.assertCurrentActivity("wrong activity",login.class);
    }

    /**
     * Test out empty username and password
     */
    public void testNewUsername() throws Exception
    {

        solo.assertCurrentActivity("wrong activity",login.class);
        EditText user = (EditText) solo.getView(R.id.username123);
        EditText pass = (EditText) solo.getView(R.id.password123);
        EditText c_pass=(EditText) solo.getView(R.id.password_confirm);
        solo.clickOnButton("New User");
        solo.assertCurrentActivity("wrong activity",registerActivity.class);
        solo.enterText(user,"");
        solo.clickOnButton("Done");
        solo.takeScreenshot("empty new username");
        solo.assertCurrentActivity("wrong activity",registerActivity.class);
        solo.clearEditText(user);
        solo.clearEditText(pass);
        solo.clearEditText(c_pass);
        solo.enterText(user,"aaa");
        solo.enterText(pass,"");
        solo.clickOnButton("Done");
        solo.takeScreenshot("empty new password");
        solo.assertCurrentActivity("wrong activity",login.class);
    }
}
