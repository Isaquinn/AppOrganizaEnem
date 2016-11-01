package com.example.isaquearaujo.apporganizaenem;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by Isaque Araujo on 24/10/2016.
 */

public class NavigateTelaPrincipal extends FragmentPagerAdapter {

    public NavigateTelaPrincipal(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return new Padrao();
            case 1: return new TelaConfiguracoes();
            case 2: return new TelaMudarAvatar();
        }
        return null;
    }
    @Override
    public int getCount() {
        return 3;
    }

}
