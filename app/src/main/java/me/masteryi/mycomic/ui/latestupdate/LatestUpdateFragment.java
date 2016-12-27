package me.masteryi.mycomic.ui.latestupdate;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.masteryi.mycomic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LatestUpdateFragment extends Fragment {

    public LatestUpdateFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_latest_update, container, false);
    }
}
