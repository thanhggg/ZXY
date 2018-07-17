package com.gggstudio.chuctet.smstet.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.gggstudio.chuctet.smstet.fragment.CategoryFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
//		case 0:
//			// Top Rated fragment activity
//			return new BetterFragment();

            case 0:
                // Games fragment activity
                return new CategoryFragment();

//            case 1:
//                // Movies fragment activity
//                return new FavoritesFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 1;
    }

}
