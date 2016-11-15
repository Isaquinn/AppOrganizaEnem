package com.example.isaquearaujo.apporganizaenem;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

/**
 * Created by Isaque Araujo on 14/11/2016.
 */

public  class  ArrayFireBase  extends FirebaseListAdapter<String> {
    public ArrayFireBase(Activity activity, Class modelClass, int modelLayout, Firebase ref) {
        super(activity, modelClass, modelLayout, ref);
    }

    @Override
    protected void populateView(View view, String o, int i) {
        Log.d("s", o);
        TextView textView = (TextView)view.findViewById(android.R.id.text1);
        textView.setText(o);
    }
}
