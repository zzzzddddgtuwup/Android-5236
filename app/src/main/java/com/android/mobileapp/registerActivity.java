package com.android.mobileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class registerActivity extends ActionBarActivity implements View.OnClickListener
{
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = (EditText) findViewById(R.id.username123);
        etPassword = (EditText) findViewById(R.id.password123);
        etConfirm = (EditText) findViewById(R.id.password_confirm);
        View btnAdd = (Button) findViewById(R.id.done_button);
        btnAdd.setOnClickListener(this);
        View btnCancel = (Button) findViewById(R.id.cancel_button);
        btnCancel.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.register, menu);
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
            case R.id.done_button:
                createAccount();

                break;
            case R.id.cancel_button:
                finish();
                break;
        }
    }

    private void createAccount() {
        //check network connection
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            String confirm = etConfirm.getText().toString();

            if ((password.equals(confirm)) && (!username.equals(""))
                    && (!password.equals("")) && (!confirm.equals(""))) {
                new addUserTask().execute(new User(username, password));
            } else if ((username.equals("")) || (password.equals(""))
                    || (confirm.equals(""))) {
                Toast.makeText(registerActivity.this, "Missing entry", Toast.LENGTH_SHORT)
                        .show();
            } else if (!password.equals(confirm)) {
                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("passwords do not match")
                        .setNeutralButton("Try Again",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                    }
                                })

                        .show();
                finish();
            }
        }else{
            Toast.makeText(this, "The network is not available. Please open WIFI or Mobile network",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //add user to server
    private class addUserTask extends AsyncTask<User, Void, Integer> {

        @Override
        protected Integer doInBackground(User... users) {
            return UserSvc.getOrInit(getString(R.string.serverUrl)).addUser(users[0]);
        }

        @Override
        protected void onPostExecute(Integer result){
            if((int)result == 1){
                Toast.makeText(registerActivity.this, "new record inserted",
                        Toast.LENGTH_SHORT).show();
                finish();
            }else if((int)result == -1){
                Toast.makeText(registerActivity.this, "user name is already used",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}
