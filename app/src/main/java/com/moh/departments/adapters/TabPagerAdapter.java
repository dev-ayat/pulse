package com.moh.departments.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.moh.departments.fragment.GrouptestFragment;
import com.moh.departments.fragment.favtestFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    @NonNull
    int tabCount;

    public TabPagerAdapter(FragmentManager fragmentManager, int numberOfTabs) {
        super(fragmentManager);
        this.tabCount = numberOfTabs;
    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new GrouptestFragment();
            case 1:
                return new favtestFragment();
            default:
                return new GrouptestFragment();
        }
    }

}
