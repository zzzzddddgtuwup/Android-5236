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
public class answerAdapter extends ArrayAdapter<Answer> {

    public answerAdapter(Context context, int resource, int textViewResourceId, List<Answer> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Answer ans = getItem(position);
        if(convertView==null){
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.list_item_answer, parent, false);
        }
        TextView tvContent = (TextView)convertView.findViewById(R.id.list_item_answer_textview);
        tvContent.setText(ans.getContent());
        return convertView;
    }
}