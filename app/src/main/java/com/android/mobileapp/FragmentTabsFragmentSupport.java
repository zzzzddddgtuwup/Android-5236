package com.android.mobileapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zzzzddddgtuwup on 11/27/14.
 */
public class FragmentTabsFragmentSupport extends Fragment {
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fragmentTabHost);

        mTabHost.addTab(mTabHost.newTabSpec("allQuestions").setIndicator("all"),
                Tab1Fragment.class, getArguments());
        mTabHost.addTab(mTabHost.newTabSpec("topFive").setIndicator("popular"),
                Tab2Fragment.class, getArguments());
        mTabHost.addTab(mTabHost.newTabSpec("askAndSearch").setIndicator("ask Search"),
                Tab3Fragment.class, getArguments());

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}
