package com.android.mobileapp;


import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ForumDetailFragment extends Fragment {

    private FragmentTabHost mTabHost;
    private ViewPager mViewPager;
    private ForumPagerAdapter mPagerAdapter;

    public ForumDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_forum_detail, container, false);
//
//        mTabHost =(FragmentTabHost) v.findViewById(R.id.tabHost);
//        mTabHost.setup(getActivity(),getChildFragmentManager(),R.id.forumContent);
//        mTabHost.addTab(mTabHost.newTabSpec("FragmentB").setIndicator("Tab 1"), MyQuestionActivity.PlaceholderFragment.class, null);
//        mTabHost.getCurrentTabView().setBackgroundColor(Color.DKGRAY);
//        mTabHost.addTab(mTabHost.newTabSpec("FragmentC").setIndicator("Tab 2"), MyAnswerActivity.PlaceholderFragment.class,null);
//        mTabHost.getTabWidget().getChildTabViewAt(1).setBackgroundColor(Color.DKGRAY);

        mViewPager =(ViewPager)v.findViewById(R.id.pager);
        mPagerAdapter = new ForumPagerAdapter(
                getChildFragmentManager());

        mViewPager =(ViewPager)v.findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
        public void onPageSelected (int position){
                getActivity().getActionBar().setSelectedNavigationItem(position);
            }
        });
        setupTabs();
        return v;
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        destroyTab();
    }

    private void destroyTab() {
        final ActionBar actionBar = getActivity().getActionBar();
        actionBar.removeAllTabs();
    }

    private void setupTabs(){
        final ActionBar actionBar = getActivity().getActionBar();

        //Specify that tabs should be displayed in the action bar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //Create a tab listener that is called when teh user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){
          @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){
              mViewPager.setCurrentItem(tab.getPosition());
          }
            @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){

            }

            @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){

            }
        };

        //add text to the tab
        actionBar.addTab(actionBar.newTab()
                 .setText(getResources().getString(R.string.title_activity_my_question))
                 .setTabListener(tabListener));

        actionBar.addTab(actionBar.newTab()
                .setText(getResources().getString(R.string.title_activity_my_answer))
                .setTabListener(tabListener));
    }


    public class ForumPagerAdapter extends FragmentStatePagerAdapter {

        public ForumPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            Fragment fragment=null;
            if(i ==0){
                fragment = new MyQuestionActivity.PlaceholderFragment();
            }else if(i==1){
                fragment = new MyAnswerActivity.PlaceholderFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){return "OBJECT"+(position+1);}
    }

}
