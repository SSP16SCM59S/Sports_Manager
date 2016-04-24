package com.cs442.shash5259.sportsmgr;

/**
 * Created by shash on 11-03-2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class PrimaryFragment extends Fragment {

    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Schedule");
        rootView = inflater.inflate(R.layout.primary_layout, container, false);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(isVisibleToUser == true)
        {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Schedule");
        }
    }

}