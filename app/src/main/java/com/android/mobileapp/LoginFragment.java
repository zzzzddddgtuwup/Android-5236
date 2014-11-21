package com.android.mobileapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class LoginFragment extends DialogFragment implements View.OnClickListener {

   private EditText mPasswordField;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View v = inflater.inflate(R.layout.fragment_login, null);
        mPasswordField = (EditText) v.findViewById(R.id.password_login_text);
        mPasswordField.setTransformationMethod(new AsteriskPasswordTransformationMethod());

        //Button btnLogin=(Button)v.findViewById(R.id.login_button);
        //btnLogin.setOnClickListener(this);
        //Button btnNewUser=(Button)v.findViewById(R.id.signup_button);
        //btnNewUser.setOnClickListener(this);
        //Button btnPassReset=(Button)v.findViewById(R.id.password_reset);
        //btnPassReset.setOnClickListener(this);

        return builder
                .setView(v)
                .create();
    }

   private class AsteriskPasswordTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }
   }


    private class PasswordCharSequence implements CharSequence {
        private CharSequence mSource;
        public PasswordCharSequence(CharSequence source){
            mSource = source;
        }
        public char charAt(int index){
            return '*';
        }
        public int length(){
            return mSource.length();
        }
        public CharSequence subSequence(int start, int end){
            return mSource.subSequence(start, end);
        }
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.login_button:
                CurrentUserSession.getInstance().setLoggedIn(true);
                //((MainActivity)getActivity()).addHeaderViewIfNeeded();
                dismiss();

                break;
            case R.id.signup_button:
                Log.d("Login Page","SignUp fragment");
                (new SignUpFragment()).show(getChildFragmentManager(),"Sign Up");
                break;
            case R.id.password_reset:
                Log.d("Reset Password","Reset Fragment");
                (new ResetPasswordFragment()).show(getChildFragmentManager(),"Reset Password");
                break;
        }
    }
}