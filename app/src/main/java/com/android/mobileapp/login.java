package com.android.mobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class login extends ActionBarActivity implements View.OnClickListener{
    private final String TAG = ((Object) this).getClass().getSimpleName();
    private EditText userNameEditableField;
    private EditText passwordEditableField;
    private final static String OPT_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditableField = (EditText) findViewById(R.id.username_text);
        passwordEditableField = (EditText) findViewById(R.id.password_text);
        View btnLogin = (Button) findViewById(R.id.login_button);
        btnLogin.setOnClickListener(this);
        View btnCancel = (Button) findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(this);
        View btnNewUser = (Button) findViewById(R.id.new_user_button);
        btnNewUser.setOnClickListener(this);
    }

    private void checkLogin() {
        String username = this.userNameEditableField.getText().toString();
        String password = this.passwordEditableField.getText().toString();
        new getUserTask().execute(username,password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                checkLogin();
                break;
            case R.id.cancel_button:
                finish();
                break;
            case R.id.new_user_button:
                startActivity(new Intent(this, registerActivity.class));
                break;
        }
    }

    private class getUserTask extends AsyncTask<String, Void, User> {
        private String username;

        @Override
        protected User doInBackground(String... params) {
            username = params[0];
            return UserSvc.getOrInit(getString(R.string.serverUrl)).validate(new User(params[0],params[1]));
        }

        @Override
        protected void onPostExecute(User result) {
            if (result!=null) { // Login successful
                SharedPreferences sharedPref = getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(getString(R.string.username), username);
                //editor.putInt(getString(R.string.user_score));
                editor.commit();

                startActivity(new Intent(login.this, MainActivity.class));
                finish();
            } else {
                // Try again?
                new AlertDialog.Builder(login.this)
                        .setTitle("Error")
                        .setMessage("Login failed")
                        .setNeutralButton("Try Again",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                }).show();
            }
        }
    }
}
