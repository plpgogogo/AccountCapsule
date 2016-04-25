package com.acapsule.accountcapsule;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.acapsule.accountcapsule.R;
import com.acapsule.accountcapsule.adapters.MyFragmentPageAdapter;
import com.acapsule.accountcapsule.childfrags.AccountFragment;
import com.acapsule.accountcapsule.childfrags.AnalysisFragment;
import com.acapsule.accountcapsule.childfrags.QueryFragment;
import com.acapsule.accountcapsule.childfrags.RecordFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainContentFragment extends Fragment {

    @Bind(R.id.icon_page_record)
    ImageView iconPageRecord;
    @Bind(R.id.icon_page_analysis)
    ImageView iconPageAnalysis;
    @Bind(R.id.icon_page_query)
    ImageView iconPageQuery;
    @Bind(R.id.icon_page_account)
    ImageView iconPageAccount;
    @Bind(R.id.viewpager_content)
    ViewPager viewpagerContent;

    private View mView;

    private ColorStateList dark;
    private ColorStateList light;


    public MainContentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main_content, container, false);
        ButterKnife.bind(this, mView);


        initColorSateList();
        initViewPager();

        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.icon_page_record, R.id.icon_page_analysis, R.id.icon_page_query, R.id.icon_page_account})
    public void onClick(View view) {
        int exPosition = viewpagerContent.getCurrentItem();
        switch (view.getId()) {
            case R.id.icon_page_record:
                viewpagerContent.setCurrentItem(0);
                break;
            case R.id.icon_page_analysis:
                viewpagerContent.setCurrentItem(1);
                break;
            case R.id.icon_page_query:
                viewpagerContent.setCurrentItem(2);
                break;
            case R.id.icon_page_account:
                viewpagerContent.setCurrentItem(3);
                break;
        }
    }


    private void initViewPager() {
        //add views
        RecordFragment pageRecord = new RecordFragment();
        AnalysisFragment pageAnalysis = new AnalysisFragment();
        QueryFragment pageQuery = new QueryFragment();
        AccountFragment pageAccount = new AccountFragment();
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(pageRecord);
        fragments.add(pageAnalysis);
        fragments.add(pageQuery);
        fragments.add(pageAccount);
        //set Adapter
        viewpagerContent.setAdapter(new MyFragmentPageAdapter(getActivity().getSupportFragmentManager(), fragments));
        viewpagerContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lightenIcon(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewpagerContent.setCurrentItem(0);
        lightenIcon(0);
    }

    private void initColorSateList(){
        dark = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimaryLight));
        light = ColorStateList.valueOf(getResources().getColor(R.color.colorIconAndText));
    }

    private void lightenIcon(int toPosition){
        iconPageRecord.setImageTintList(dark);
        iconPageAnalysis.setImageTintList(dark);
        iconPageQuery.setImageTintList(dark);
        iconPageAccount.setImageTintList(dark);
        switch (toPosition){
            case 0:
                iconPageRecord.setImageTintList(light);
                iconPageRecord.setElevation(getResources().getDimension(R.dimen.elevation_unit)*10);
                break;
            case 1:
                iconPageAnalysis.setImageTintList(light);
                iconPageAnalysis.setElevation(getResources().getDimension(R.dimen.elevation_unit)*10);
                break;
            case 2:
                iconPageQuery.setImageTintList(light);
                iconPageQuery.setElevation(getResources().getDimension(R.dimen.elevation_unit)*10);
                break;
            case 3:
                iconPageAccount.setImageTintList(light);
                iconPageAccount.setElevation(getResources().getDimension(R.dimen.elevation_unit)*10);
                break;
        }
    }
}
