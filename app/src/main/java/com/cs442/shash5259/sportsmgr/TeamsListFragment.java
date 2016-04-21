package com.cs442.shash5259.sportsmgr;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 20-04-2016.
 */
public class TeamsListFragment extends Fragment {

    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    SharedPreferences sharedpreferences;
    String MyPREFERENCES = "Login_Credentials1";
    String title=null;
    View rootView;
    ListView lv;
    String t_name=null;
    private List<TeamNames> myTeams = new ArrayList<TeamNames>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mFragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();

        rootView = inflater.inflate(R.layout.teams_display, container, false);
        lv = (ListView)rootView.findViewById(R.id.teamslist);

        sharedpreferences = this.getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        title= sharedpreferences.getString("sport", null);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);


        populateHotelList();
        populateListView();
        //registerClickCallBack();


        return rootView;
    }

    private void populateHotelList()
    {
        Cursor cs=null;
        DataHandler db = new DataHandler((AppCompatActivity)getActivity());
        db.open();
        cs = db.returnTeamData(title);
        cs.moveToFirst();
        while (!cs.isAfterLast()) {
            t_name = cs.getString(0);
            myTeams.add(new TeamNames(t_name));
            cs.moveToNext();
        }
    }

    private void populateListView()
    {
        final ArrayAdapter<TeamNames> adapter = new MyListAdapter();
        final ListView lv = (ListView)rootView.findViewById(R.id.teamslist);

        //list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, strArr));
        lv.setAdapter(adapter);
        //registerClickCallBack();
    }

    public class MyListAdapter extends ArrayAdapter<TeamNames>
    {
        public MyListAdapter() {
            super((AppCompatActivity)getActivity(), R.layout.custom_listview, myTeams);
        }

        @Override
        public View getView(int position, final View convertView, ViewGroup parent)
        {
            View itemView = convertView;

            if (itemView == null) {
                itemView = ((AppCompatActivity)getActivity()).getLayoutInflater().inflate(R.layout.custom_listview, parent, false);
            }
            //find item to work with
            TeamNames currentTeam = myTeams.get(position);

            TextView t1 = (TextView) itemView.findViewById(R.id.list_text);
            t1.setText(currentTeam.getTeam_name());

            return itemView;
            //return super.getView(position,convertView,parent);
        }
    }


}
