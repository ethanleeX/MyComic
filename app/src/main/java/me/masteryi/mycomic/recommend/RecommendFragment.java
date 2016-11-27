package me.masteryi.mycomic.recommend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.masteryi.mycomic.R;

/**
 * @author master.yi
 * @date 2016/11/20
 * @blog masteryi.me
 */
public class RecommendFragment extends Fragment {

    public RecommendFragment () {
        // Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}
