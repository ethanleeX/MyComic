package me.masteryi.mycomic.range;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.masteryi.mycomic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RangeFragment extends Fragment {

    public RangeFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_range, container, false);
    }
}
