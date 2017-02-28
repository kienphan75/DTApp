package com.example.nam.dtapp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

/**
 * Lớp chưa các adapter của viewpager trên activity home
 * Created by NAM on 12/11/2016.
 */


public class HomeVPAdapter {
    /**
     * Adapter đưa các fragment vào viewpager ở trang chủ ứng dụng, bao gồm newfeed, notification, v.v...
     */
    public static class HomePagerAdapter extends FragmentStatePagerAdapter {
        Fragment frag[] ;
        public HomePagerAdapter(FragmentManager fm, Fragment frag[]) {
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
//        @Override
//        public CharSequence getPageTitle(int position) {
//            String title = "";
//            switch (position){
//                case 0:
//                    title="Pokemon Gen I";
//                    break;
//                case 1:
//                    title="Pokemon Gen II";
//                    break;
//                case 2:
//                    title="TOP GEN I";
//                    break;
//                case 3:
//                    title="TOP GEN II";
//                    break;
//            }
//
//            return title;
//        }

    }


}
