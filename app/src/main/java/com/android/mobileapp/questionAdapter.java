package com.android.mobileapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zzzzddddgtuwup on 11/14/14.
 */
//adapter to show the answers
public class questionAdapter extends ArrayAdapter<Question> {

    public questionAdapter(Context context, int resource, int textViewResourceId, List<Question> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    //override the getView to show rate and content of questions
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Question ques = getItem(position);
        if(convertView == null){
            convertView =  LayoutInflater.from(this.getContext())
                    .inflate(R.layout.list_item_question, parent, false);
        }
        TextView tvContent = (TextView)convertView.findViewById(R.id.list_item_question_textview);
        tvContent.setText("rate: "+ ques.getRate() + " " +ques.getContent());
        return convertView;
    }
}
