package com.example.isaquearaujo.apporganizaenem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Isaque Araujo on 24/10/2016.
 */

public class NavigatePadrao extends FragmentPagerAdapter {
    public NavigatePadrao(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return new TelaMaterias();
            case 1: return new TelaDicas();
            case 2: return new TelaMedalhas();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }
}
