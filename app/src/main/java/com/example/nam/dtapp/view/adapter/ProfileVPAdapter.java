package com.example.nam.dtapp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by NAM on 12/24/2016.
 */

public class ProfileVPAdapter {
    /**
     * Adapter đưa các fragment vào viewpager ở trang cá nhân, bao gồm v.v...
     */
    public static class ProfileAdapter extends FragmentStatePagerAdapter {
        Fragment frag[] ;
        public ProfileAdapter(FragmentManager fm, Fragment frag[]) {
            super(fm);
            this.frag = frag;
        }

        @Override
        public Fragment getItem(int position) {
            return frag[position];
        }
        @Override
        public int getCount() {
            return frag.length;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            String title = "Tab "+position;
            switch (position){
                case 0:
                    title="ACTIVITIES";
                    break;
                case 1:
                    title="FOLLOWERS";
                    break;
                case 2:
                    title="FOLLOWING";
                    break;
            }

            return title;
        }

    }
}
